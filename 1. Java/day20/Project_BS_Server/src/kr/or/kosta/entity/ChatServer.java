package kr.or.kosta.entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class ChatServer {

	public static final int PORT = 7777;
	public final int waitingRoomUserLimit = 100;

	private boolean running;
	private ServerSocket serverSocket;
	private Hashtable<String, Client> clients;
	private Hashtable<Integer, Room> room;
	private int roomNum = 0;

	public Hashtable<Integer, Room> getRoom() {
		return room;
	}

	public Hashtable<String, Client> getClients() {
		return clients;
	}
	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer 구동이 불가능합니다.");
		}
		clients = new Hashtable<>();
		room = new Hashtable<>();

		makeWaitingRoom();

		running = true;
		System.out.println("[" + PORT + "] 포트에서 ChatServer Start...");
		while (running) {
			try {
				Socket socket = serverSocket.accept();
				Client client = new Client(this, socket);

				client.start();

				System.out.println("#### [" + socket.getInetAddress().getHostAddress() + "] 님께서 서버에 연결하였습니다. ####");
			} catch (IOException e) {
				System.out.println("accept 오류");
			}

		}

	}

	public void makeWaitingRoom() {
		WaitingRoom waitingRoomServer = new WaitingRoom(waitingRoomUserLimit);
		room.put(roomNum++, waitingRoomServer);
	}

	public ChatRoom makeChatRoom(String roomName, int userLimit) {
		ChatRoom chatRoom = new ChatRoom(roomName, userLimit);
		int roomNumTemp;
		System.out.println(roomNum);
		for (roomNumTemp = 1; roomNumTemp <= roomNum; roomNumTemp++) {
			if (!room.containsKey(roomNumTemp)) {
				break;
			}
		}
		chatRoom.setRoomNum(roomNumTemp);
		room.put(roomNumTemp, chatRoom);
		roomNum++;
		return chatRoom;
	}
	
	public void removeChatRoom(int roomNum) {
		room.remove(roomNum);
		roomNum--;
	}

	public void addClient(Client client) {
		clients.put(client.getNickName(), client);
	}

	public void removeClient(String nickName) {
		clients.remove(nickName);
	}

	public boolean isExistClient(String nickName) {
		if (clients.containsKey(nickName)) {
			return true;
		} else {
			return false;
		}
	}

}
