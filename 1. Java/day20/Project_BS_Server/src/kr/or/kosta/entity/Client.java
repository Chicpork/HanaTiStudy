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
	private ChatServer chatServer;
	private WaitingRoom waitingRoom;
	private ChatRoom chatRoom;

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;

	/** 클라이언트 식별자 */
	private String nickName;

	public Client(ChatServer chatServer, Socket socket) throws IOException {
		this.chatServer = chatServer;
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setChatServer(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		receiveMessage();
	}

	public void receiveMessage() {
		while (running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();

				process(clientMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		close();
	}

	/**
	 * 클라이언트의 메시지를 파싱하여 서비스 제공
	 * 
	 * @param message
	 */
	public void process(String message) {
		System.out.println(message);
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
				e.printStackTrace();
			}

			break;
		case Protocol.FAIL:
			System.out.println("client 종료");
			running = false;

			break;
		case Protocol.CS_DISCONNECT:
			sendMessage(Protocol.SC_DISCONNECT + "");

			System.out.println("client 종료");
			running = false;
			break;
		}
	}

	public void waitingProcess(String[] tokens) {
		int protocol = Integer.parseInt(tokens[1]);
		switch (protocol) {
		case Protocol.CS_NEW_USER:
			break;

		case Protocol.CS_ROOM_INFO:
			// 여기 수정
			chatRoom = ((ChatRoom) chatServer.getRoom().get(Integer.parseInt(tokens[2])));

			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ROOM_INFO + Protocol.DELEMETER
					+ chatRoom.getUserNum() + chatRoom.getClientsList());
			break;

		case Protocol.CS_NEW_ROOM:
			System.out.println("새방 만들기");

			chatRoom = chatServer.makeChatRoom(tokens[2], Integer.parseInt(tokens[3]));
			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_ROOM + Protocol.DELEMETER
					+ Protocol.SUCCESS);

			try {
				chatRoom.addClient(this);
				waitingRoom.removeClient(nickName);
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_ROOM_TO_WAIT
						+ chatRoom.getChatRoomInfo());
				waitingRoom.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER
						+ Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(
						Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
				chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
						+ Protocol.DELEMETER + ("**** " + nickName + "님이 입장하셨습니다 ****"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			System.out.println("새방 만들기 완료");
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

		case Protocol.CS_DELETE_USER:
			break;

		case Protocol.CS_DELETE_ROOM:
			break;

		case Protocol.INVITE:
			int protocol2 = Integer.parseInt(tokens[2]);
			switch (protocol2) {
			case Protocol.SUCCESS:
				try {
					sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
							+ Protocol.SUCCESS);
					chatRoom.addClient(this);
					sendMessage(
							Protocol.CHATTING + Protocol.DELEMETER + Protocol.USER_LIST + chatRoom.getClientsList());
					waitingRoom.removeClient(nickName);
					chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_NEW_USER
							+ Protocol.DELEMETER + nickName);
					chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE
							+ Protocol.DELEMETER + ("**** " + nickName + "님이 입장하셨습니다 ****"));

				} catch (Exception e) {
					sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_ENTER_ROOM + Protocol.DELEMETER
							+ Protocol.FAIL);
				}
				break;
			case Protocol.FAIL:

				break;
			}
			break;

		case Protocol.CS_DISCONNECT:

			waitingRoom.sendMessageToAll(
					Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_DELETE_USER + Protocol.DELEMETER + nickName);
			sendMessage(Protocol.SC_DISCONNECT + "");

			waitingRoom.removeClient(nickName);
			chatServer.removeClient(nickName);

			System.out.println("client 종료");
			running = false;
			break;

		}

	}

	public void chattingProcess(String[] tokens) {
		int protocol = Integer.parseInt(tokens[1]);
		switch (protocol) {
		case Protocol.CS_MESSAGE:
			chatRoom.sendMessageToAll(Protocol.CHATTING + Protocol.DELEMETER + Protocol.SC_MESSAGE + Protocol.DELEMETER
					+ (nickName + " : " + tokens[2]));
			break;
		case Protocol.INVITE:
			int protocol2 = Integer.parseInt(tokens[2]);
			switch (protocol2) {
			case Protocol.USER_LIST:
				sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.USER_LIST + chatRoom.getClientsList());
				break;
			case Protocol.USER:
				chatServer.getClients().get(tokens[3]).sendMessage(Protocol.WAITING + Protocol.DELEMETER
						+ Protocol.INVITE + Protocol.DELEMETER + chatRoom.getChatRoomInfo());
				break;
			}

			break;
		case Protocol.WHISPER:
			chatServer.getClients().get(tokens[2]).sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.WHISPER
					+ Protocol.DELEMETER + ("[Whisper] " + nickName + " : " + tokens[3]));
			break;
		case Protocol.EXIT_ROOM:
			chatRoom.removeClient(nickName);

			try {
				waitingRoom.sendMessageToAll(
						Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
				waitingRoom.addClient(this);
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
		case Protocol.CS_DELETE_USER:
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

			System.out.println("client 종료");
			running = false;
			break;
		}
	}

	public void sendMessage(String message) {
		out.println(message);
	}

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

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
