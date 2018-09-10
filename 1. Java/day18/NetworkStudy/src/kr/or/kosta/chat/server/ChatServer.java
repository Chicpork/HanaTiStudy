package kr.or.kosta.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import kr.or.kosta.chat.common.Protocol;
import kr.or.kosta.chat.common.TimeCompare;

public class ChatServer {

	public static final int PORT = 7777;
	private boolean running;
	private ServerSocket serverSocket;
	private Hashtable<String, Client> clients;

	public boolean isRunning() {
		return running;
	}
	
	public int getClientSize() {
		return clients.size();
	}

	public Hashtable<String, Client> getClients() {
		return clients;
	}

	public void startUp() throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (Exception e) {
			throw new IOException("[" + PORT + "] 포트 충돌로 ChatServer 구동이 불가능합니다.");
		}
		clients = new Hashtable<>();
		running = true;
		System.out.println("[" + PORT + "] 포트에서 ChatServer Start...");
		while (running) {
			try {
				Socket socket = serverSocket.accept();
				Client client = new Client(socket);

				client.setChatServer(this);
				client.start();
				
				System.out.println("#### [" + socket.getInetAddress().getHostAddress() + "] 님께서 서버에 연결하였습니다. ####");
			} catch (IOException e) {
				System.out.println("accept() 메소드 예외 처리 해야됨.");
			}
		}
	}

	public void shutDown() {
		try {
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExistClient(String nickName) {
		if(clients.containsKey(nickName)) {
			return true;
		}
		return false;
	}
	
	public String getNickNameList_SAVE() {
		String nickNameList = "";
		
		Enumeration<String> iter = clients.keys();
		while (iter.hasMoreElements()) {
			nickNameList += Protocol.DELEMETER + iter.nextElement();
		}
		return nickNameList;
	}
	
	public String getNickNameList() {
		String nickNameList = "";
		List<Client> lists = new ArrayList<>();
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			lists.add(iter.nextElement());
		}
		Collections.sort(lists, new TimeCompare());
		for (Client client : lists) {
			nickNameList += Protocol.DELEMETER + client.getNickName();
		}
		return nickNameList;
	}

	public void removeClient(String nickName) {
		clients.remove(nickName);
	}

	public void sendAllMessage(String message) {
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}
}
