package kr.or.kosta.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Enumeration;

import kr.or.kosta.common.Protocol;

/**
 * 클라이언트의 데이터 수신 및 처리
 * 
 * @author 정지원
 *
 */
public class Client extends Thread {
	private ChatServer chatServer; // 클라이언트를 관리할 채팅 서버
	private WaitingRoom waitingRoom; // 클라이언트가 들어간 방 정보를 저장하기 위한 변수
	private ChatRoom chatRoom; // 클라이언트가 들어간 방 정보를 저장하기 위한 변수

	private Socket socket; // 클라이언트와 통신을 위한 소켓
	private BufferedReader in; // 통신에 사용할 리더
	private PrintWriter out; // 통신에 사용할 라이터
	private boolean running; // 클라이언트를 지속적으로 구동하기 위한 변수

	/** 클라이언트 식별자 */
	private String nickName;

	/**
	 * 클라이언트 생성자<br>
	 * 클라이언트를 만든 채팅 서버와 통신에 사용할 소켓을 받아와 저장함
	 * 
	 * @param chatServer 채팅 서버
	 * @param socket     통신
	 * @throws IOException
	 */
	public Client(ChatServer chatServer, Socket socket) throws IOException {
		this.chatServer = chatServer;
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	// getter-setter
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setChatServer(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Socket getSocket() {
		return socket;
	}

	// Thread를 사용하기 위해 오버라이딩한 메소드
	@Override
	public void run() {
		receiveMessage();
	}

	/**
	 * 클라이언트에게서 오는 메시지를 받기 위한 기능 지속적인 통신을 위해 while문 사용
	 */
	public void receiveMessage() {
		while (running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();

				process(clientMessage);
			} catch (IOException e) {
			}
		}

		close();
	}

	/**
	 * 클라이언트의 메시지를 파싱하여 서비스 제공 각각 login 영역, waiting 영역, chatting 영역이 존재
	 * 
	 * @param message 클라이언트가 보낸 메시지
	 */
	public void process(String message) {
		if (message == null) {
			sendMessage(Protocol.SC_DISCONNECT + "");
			running = false;
		} else {
			String[] tokens = message.split(Protocol.DELEMETER);
			int protocol = Integer.parseInt(tokens[0]);
			switch (protocol) {
			case Protocol.LOGIN:
				loginProcess(tokens);
				break;
			case Protocol.WAITING:
				waitingProcess(tokens);
				break;
			case Protocol.CHATTING:
				chattingProcess(tokens);
				break;
			}
		}
	}

	/**
	 * 로그인 영역 파싱 처리 서비스
	 * 
	 * @param tokens 클라이언트가 보낸 메시지
	 */
	public void loginProcess(String[] tokens) {
		int protocol = Integer.parseInt(tokens[1]);
		switch (protocol) {
		case Protocol.CHECK:
			if (chatServer.isExistClient(tokens[2])) {
				sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.FAIL);
			} else {
				sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.SUCCESS);
				nickName = tokens[2];
				chatServer.addClient(this);
			}
			break;
		case Protocol.SUCCESS:
			waitingRoom = getWaitingRoom();
			if (waitingRoom == null) {
//				throw new Exception("웨이팅룸서버 문제");
			}

			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.USER_LIST + waitingRoom.getClientsList());
			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.ROOM_LIST + getChatRoomList());

			try {
				waitingRoom.addClient(this);
				waitingRoom.sendMessageToAll(
						Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);

			} catch (Exception e) {
			}

			break;
		case Protocol.FAIL:
			running = false;
			break;
		case Protocol.CS_DISCONNECT:
			sendMessage(Protocol.SC_DISCONNECT + "");
			running = false;
			break;
		}
	}

	/**
	 * 대기실 영역 파싱 처리 서비스
	 * 
	 * @param tokens 클라이언트가 보낸 메시지
	 */
	public void waitingProcess(String[] tokens) {
		int protocol = Integer.parseInt(tokens[1]);
		switch (protocol) {

		case Protocol.CS_ROOM_INFO:
			// 여기 수정
			chatRoom = ((ChatRoom) chatServer.getRoom().get(Integer.parseInt(tokens[2])));

			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ROOM_INFO + Protocol.DELEMETER
					+ chatRoom.getUserNum() + chatRoom.getClientsList());
			break;

		case Protocol.CS_NEW_ROOM:

			chatRoom = chatServer.makeChatRoom(tokens[2], Integer.parseInt(tokens[3]));
			chatRoom.setRoomMaster(nickName);
			try {

				chatRoom.addClient(this);
				waitingRoom.removeClient(nickName);
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_ROOM_TO_WAIT
						+ chatRoom.getChatRoomInfo());
				sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_ROOM + Protocol.DELEMETER
						+ Protocol.SUCCESS + Protocol.DELEMETER + chatRoom.getRoomName());
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER
						+ Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(
						Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
						+ Protocol.DELEMETER + ("**** " + nickName + "님이 입장하셨습니다 ****"));
			} catch (Exception e1) {
			}

			break;

		case Protocol.CS_ENTER_ROOM:

			try {
				sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.USER_LIST + chatRoom.getClientsList());
				chatRoom.addClient(this);
				sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
						+ Protocol.SUCCESS);
				waitingRoom.removeClient(nickName);
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER
						+ Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(
						Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
						+ Protocol.DELEMETER + ("**** " + nickName + "님이 입장하셨습니다 ****"));

			} catch (Exception e) {
				sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
						+ Protocol.FAIL);
			}
			break;

		case Protocol.CS_INVITE:
			int protocol2 = Integer.parseInt(tokens[2]);
			switch (protocol2) {
			case Protocol.SUCCESS:
				for (Room room : chatServer.getRoom().values()) {
					if (room.isExistClient(tokens[3])) {
						chatRoom = (ChatRoom) room;
						break;
					}
				}

				try {
					sendMessage(
							Protocol.CHATTING + Protocol.DELEMETER + Protocol.USER_LIST + chatRoom.getClientsList());
					chatRoom.addClient(this);
					sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
							+ Protocol.SUCCESS);
					waitingRoom.removeClient(nickName);
					waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER
							+ Protocol.DELEMETER + nickName);
					chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_NEW_USER
							+ Protocol.DELEMETER + nickName);
					chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
							+ Protocol.DELEMETER + ("**** " + nickName + "님이  " + tokens[3] + "님의 초대 받아 입장하셨습니다 ****"));

				} catch (Exception e) {
					sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
							+ Protocol.FAIL);
				}
				break;
			case Protocol.FAIL:
				for (Room room : chatServer.getRoom().values()) {
					if (room.isExistClient(tokens[3])) {
						((ChatRoom) room).findClient(tokens[3]).sendMessage(Protocol.CHATTING + Protocol.DELEMETER
								+ Protocol.SC_INVITE + Protocol.DELEMETER + Protocol.FAIL);
						;
						break;
					}
				}
				break;
			}
			break;

		case Protocol.CS_DISCONNECT:

			waitingRoom.sendMessageToAll(
					Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER + Protocol.DELEMETER + nickName);
			sendMessage(Protocol.SC_DISCONNECT + "");

			waitingRoom.removeClient(nickName);
			chatServer.removeClient(nickName);

			running = false;
			break;

		}

	}

	/**
	 * 채팅방 영역 파싱 처리 서비스
	 * 
	 * @param tokens 클라이언트가 보낸 메시지
	 */
	public void chattingProcess(String[] tokens) {
		int protocol = Integer.parseInt(tokens[1]);
		switch (protocol) {
		case Protocol.CS_MESSAGE:
			chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE + Protocol.DELEMETER
					+ (nickName + " : " + tokens[2]));
			break;
		case Protocol.CS_INVITE:
			int protocol2 = Integer.parseInt(tokens[2]);
			switch (protocol2) {
			case Protocol.USER_LIST:
				sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_INVITE + Protocol.DELEMETER
						+ Protocol.USER_LIST + waitingRoom.getClientsList());
				break;
			case Protocol.USER:
				if (waitingRoom.isExistClient(tokens[3])) {
					chatServer.getClients().get(tokens[3]).sendMessage(Protocol.WAITING + Protocol.DELEMETER
							+ Protocol.SC_INVITE + Protocol.DELEMETER + nickName + chatRoom.getChatRoomInfo());
					sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_INVITE + Protocol.DELEMETER
							+ Protocol.SUCCESS);
				} else {
					sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_INVITE + Protocol.DELEMETER
							+ Protocol.FAIL);
				}
				break;
			}
			break;
		case Protocol.WHISPER:
			chatServer.getClients().get(tokens[2]).sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.WHISPER
					+ Protocol.DELEMETER + ("[Whisper] " + nickName + " : " + tokens[3]));
			break;
		case Protocol.EXIT_ROOM:
			try {
				waitingRoom.addClient(this);
				chatRoom.removeClient(nickName);
				waitingRoom.sendMessageToAll(
						Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
						+ Protocol.DELEMETER + ("@@@@ " + nickName + "님이 퇴장하셨습니다 @@@@"));
				chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_DELETE_USER
						+ Protocol.DELEMETER + nickName);
				sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.EXIT_ROOM);
				sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.USER_LIST + waitingRoom.getClientsList());
				sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.ROOM_LIST + getChatRoomList());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (chatRoom.getUserNum() <= 0) {
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_ROOM
						+ Protocol.DELEMETER + chatRoom.getRoomNum());
				chatServer.removeChatRoom(chatRoom.getRoomNum());
			}

			break;
		case Protocol.CS_DISCONNECT:

			chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE + Protocol.DELEMETER
					+ ("@@@@ " + nickName + "님이 퇴장하셨습니다 @@@@"));
			chatRoom.sendMessageToAll(
					Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_DELETE_USER + Protocol.DELEMETER + nickName);
			sendMessage(Protocol.SC_DISCONNECT + "");

			chatRoom.removeClient(nickName);
			chatServer.removeClient(nickName);

			if (chatRoom.getUserNum() <= 0) {
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_ROOM
						+ Protocol.DELEMETER + chatRoom.getRoomNum());
				chatServer.removeChatRoom(chatRoom.getRoomNum());
			}

			running = false;
			break;
		}
	}

	/**
	 * 클라이언트에게 메시지 보내는 기능
	 * 
	 * @param message 전달할 메시지
	 */
	public void sendMessage(String message) {
		out.println(message);
	}

	/**
	 * 채팅 서버에 만들어진 방들 중 대기 방 얻어오는 기능
	 * 
	 * @return
	 */
	public WaitingRoom getWaitingRoom() {
		Enumeration<Room> iter = chatServer.getRoom().elements();
		while (iter.hasMoreElements()) {
			Room roomServerTemp = iter.nextElement();
			if (roomServerTemp instanceof WaitingRoom) {
				return (WaitingRoom) roomServerTemp;
			}
		}
		return null;
	}

	/**
	 * 채팅 서버에 만들어진 전체 채팅방 정보를 Protocol.DELEMETER를 기준으로 구분하여 보내는 기능
	 * 
	 * @return
	 */
	public String getChatRoomList() {
		String chatRoomInfo = "";

		Enumeration<Room> iter = chatServer.getRoom().elements();
		while (iter.hasMoreElements()) {
			Room room = iter.nextElement();
			if (room instanceof ChatRoom) {
				ChatRoom chatRoom = (ChatRoom) room;
				chatRoomInfo += chatRoom.getChatRoomInfo();
			}
		}

		return chatRoomInfo;
	}

	/**
	 * 현재 클라이언트와 통신하는 소켓이 존재하면 종료하는 기능
	 */
	public void close() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 이 클라이언트를 종료하는 기능
	 */
	public void closeClient() {
		running = false;
	}
}
