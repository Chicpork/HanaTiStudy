package kr.or.kosta.entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import kr.or.kosta.boundary.ServerFrame;
import kr.or.kosta.common.Protocol;

/**
 * 채팅 서버를 관리할 클래스
 * 
 * @author 정지원
 *
 */
public class ChatServer extends Thread {

	public static final int PORT = 7777; // 사용할 포트 번호
	public final int waitingRoomUserLimit = 100; // 대기실 최대 인원 

	// 클라이언트의 신호를 지속적으로 받기 위해 while문을 돌리기 위한 변수
	private boolean running; 
	private ServerSocket serverSocket; // 클라이언트와의 통신을 위해 만든 서버 소켓
	private Hashtable<String, Client> clients; // 채팅 서버에 접속한 모든 인원을 저장하는 변수
	private Hashtable<Integer, Room> room; // 채팅 방 관리를 위한 변수
	private int roomNum = 0; // 만들어진 채팅방 개수

	private ServerFrame serverFrame; // 서버 프레임을 이용하기 위해 만든 변수

	/**
	 * 채팅 서버가 만들어질때 서버 프레임을 가져와 사용함
	 * @param serverFrame 사용할 서버 화면
	 */
	public ChatServer(ServerFrame serverFrame) {
		this.serverFrame = serverFrame;
	}

	// getter-setter
	public Hashtable<String, Client> getClients() {
		return clients;
	}

	public Hashtable<Integer, Room> getRoom() {
		return room;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	// Thread 구동을 위한 run()
	@Override
	public void run() {
		try {
			startUp();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "에러", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 서버 구동 시작
	 * @throws Exception
	 */
	public void startUp() throws Exception {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			throw new Exception("[" + PORT + "] 포트 충돌로 ChatServer 구동이 불가능합니다.");
		}
		clients = new Hashtable<>();
		room = new Hashtable<>();

		makeWaitingRoom();

		running = true;
		serverFrame.sendMessageToServerInfoTF("[" + PORT + "] 포트에서 ChatServer Start...");
		Socket socket = null;
		Client client = null;
		while (running) {
			try {
				socket = serverSocket.accept();
				client = new Client(this, socket);

				client.start();

				serverFrame.sendMessageToServerInfoTF("#### [" + socket.getInetAddress().getHostAddress() + "] 님께서 서버에 연결하였습니다. ####");
			} catch (Exception e) {
				running = false;
			}
		}

	}

	/**
	 * 대기실 만드는 기능
	 */
	public void makeWaitingRoom() {
		WaitingRoom waitingRoom = new WaitingRoom(waitingRoomUserLimit);
		serverFrame.addRoomInfoList(roomNum + ". WaitingRoom");
		room.put(roomNum++, waitingRoom);
	}

	/**
	 * 채팅방 만드는 기능
	 * 
	 * @param roomName 만들어질 채팅 방 이름
	 * @param userLimit 만들어질 채방 방 최대 인원
	 * @return
	 */
	public ChatRoom makeChatRoom(String roomName, int userLimit) {
		ChatRoom chatRoom = new ChatRoom(roomName, userLimit);
		int roomNumTemp;
		for (roomNumTemp = 1; roomNumTemp <= roomNum; roomNumTemp++) {
			if (!room.containsKey(roomNumTemp)) {
				break;
			}
		}
		chatRoom.setRoomNum(roomNumTemp);
		room.put(roomNumTemp, chatRoom);
		roomNum++;
		serverFrame.addRoomInfoList(roomNumTemp + ". " + chatRoom.getRoomName());
		return chatRoom;
	}

	/**
	 * 만들어진 채팅 방 제거
	 * @param roomNum 제거할 채팅 방 번호
	 */
	public void removeChatRoom(int roomNum) {
		serverFrame.removeRoomInfoList(roomNum + ". " + ((ChatRoom) room.get(roomNum)).getRoomName());
		room.remove(roomNum);
		this.roomNum--;
	}

	/**
	 * 서버에 들어오는 클라이언트 추가 기능
	 * @param client 새로 들어온 클라이언트
	 */
	public void addClient(Client client) {
		serverFrame.addAllUserList(client.getNickName());
		clients.put(client.getNickName(), client);
	}

	/**
	 * 서버에 들어왔던 클라이언트 제거 기능
	 * @param nickName 제거할 닉네임
	 */
	public void removeClient(String nickName) {
		clients.remove(nickName);
		serverFrame.removeAllUserList(nickName);
	}

	/**
	 * 서버 안에 해당 닉네임을 가진 클라이언트가 존재하는지 확인해 true/false로 반환
	 * @param nickName 확인할 닉네임
	 * @return
	 */
	public boolean isExistClient(String nickName) {
		if (clients.containsKey(nickName)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 현재 서버에 접속한 모든 클라이언트에게 메시지 전송
	 * @param message 전송할 메시지
	 */
	public void sendMessageToAll(String message) {
		for (Client client : clients.values()) {
			client.sendMessage(message);
		}
	}

	/**
	 * 방 번호를 받아 해당하는 room 클래스를 반환하는 기능
	 * @param roomNumber 찾을 방 번호
	 * @return
	 */
	public Room findRoom(int roomNumber) {
		return room.get(roomNumber);
	}

	/**
	 * 모든 클라이언트에게 종료 메시지를 보내는 기능
	 * 서버를 강제로 종료할 때 사용한다.
	 */
	public void closeAllClients() {
		for (Client client : clients.values()) {
			client.closeClient();
			client.interrupt();
		}
	}

	/**
	 * 채팅 서버를 종료하는 기능
	 */
	public void closeChatServer() {
		try {
			if (serverSocket != null) {
				sendMessageToAll(Protocol.SC_DISCONNECT + "");
				closeAllClients();
				serverSocket.close();
				serverFrame.sendMessageToServerInfoTF("#### 서버 종료 ####");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
