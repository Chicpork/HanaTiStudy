package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.common.Protocol;

public class WaitingRoomServer implements RoomServer {

	private Hashtable<String, Client> clients;
	private String roomName;
	
	public WaitingRoomServer() {
		clients = new Hashtable<>();
		roomName = "WaitingRoom";
	}
	
	public void shutDown() {
		
	}
	
	public String getRoomName() {
		return roomName;
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
	
	public void sendMessageToAll(String message) {
		Enumeration<Client> iter = clients.elements();
		while(iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}
	
}
