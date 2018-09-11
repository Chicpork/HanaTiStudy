package kr.or.kosta.entity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.common.Protocol;

public class ChatServer {

	public static final int PORT = 7777;
	private String chatRoomDefaultName = "ChatRoom";
	private int chatRoomNum = 0;

	private boolean running;
	private ServerSocket serverSocket;
	private Hashtable<String, Client> clients;
	private Hashtable<String, RoomServer> roomServer;

	public Hashtable<String, RoomServer> getRoomServer() {
		return roomServer;
	}

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer 구동이 불가능합니다.");
		}
		clients = new Hashtable<>();
		roomServer = new Hashtable<>();

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

	private void makeWaitingRoom() {
		WaitingRoomServer waitingRoomServer = new WaitingRoomServer();
		roomServer.put(waitingRoomServer.getRoomName(), waitingRoomServer);
	}

	private void makeChatRoom() {
		ChatRoomServer chatRoomServer = new ChatRoomServer();
		chatRoomServer.setRoomName(chatRoomDefaultName + (++chatRoomNum));
		roomServer.put(chatRoomServer.getRoomName(), chatRoomServer);
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

	public WaitingRoomServer getWaitingRoomServer() {
		Enumeration<RoomServer> iter = roomServer.elements();
		while (iter.hasMoreElements()) {
			RoomServer roomServerTemp = iter.nextElement();
			if (roomServerTemp instanceof WaitingRoomServer) {
				return (WaitingRoomServer) roomServerTemp;
			}
		}
		return null;
	}

	public String getChatRoomList() {
		Enumeration<RoomServer> iter = roomServer.elements();
		String chatRoomList = "";
		while (iter.hasMoreElements()) {
			RoomServer roomServerTemp = iter.nextElement();
			if (roomServerTemp instanceof ChatRoomServer) {
				chatRoomList += Protocol.DELEMETER + roomServerTemp.getRoomName();
			}
		}
		return chatRoomList;
	}

}
