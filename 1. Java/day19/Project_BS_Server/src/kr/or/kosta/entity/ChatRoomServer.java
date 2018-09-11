package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.common.Protocol;

public class ChatRoomServer implements RoomServer {
	
	private Hashtable<String, Client> clients;
	private String chatServerRoomName;
	private String roomName;
	
	public ChatRoomServer() {
		clients = new Hashtable<>();
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public void sendMessageToAll(String message) {
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}
	
	public String getClientsList() {
		String nickNameList = "";
		
		Enumeration<String> iter = clients.keys();
		while (iter.hasMoreElements()) {
			nickNameList += Protocol.DELEMETER + iter.nextElement();
		}
		return nickNameList;
	}
	
	public void addClient(Client client) {
		clients.put(client.getNickName(), client);
	}
	
	public void removeClient(String nickName) {
		clients.remove(nickName);
	}

}
