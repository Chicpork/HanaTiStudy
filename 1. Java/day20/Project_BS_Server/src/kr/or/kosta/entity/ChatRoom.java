package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.common.Protocol;

public class ChatRoom implements Room {

	private Hashtable<String, Client> clients;
	private String roomName;
	private int roomNum;
	private int userNum = 0;
	private int userLimit;

	public ChatRoom(String roomName, int userLimit) {
		clients = new Hashtable<>();
		this.roomName = roomName;
		this.userLimit = userLimit;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public int getUserLimit() {
		return userLimit;
	}

	public void sendMessageToAll(String message) {
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}

	public String getChatRoomInfo() {
		return Protocol.DELEMETER + getRoomNum() + Protocol.DELEMETER + getRoomName() + Protocol.DELEMETER
				+ getUserNum() + Protocol.DELEMETER + getUserLimit();
	}

	@Override
	public String getClientsList() {
		String nickNameList = "";

		Enumeration<String> iter = clients.keys();
		while (iter.hasMoreElements()) {
			nickNameList += Protocol.DELEMETER + iter.nextElement();
		}
		return nickNameList;
	}

	@Override
	public void addClient(Client client) throws Exception {
		if (userNum >= userLimit) {
			throw new Exception("채팅방이 가득 찼습니다.");
		}
		clients.put(client.getNickName(), client);
		userNum++;
	}

	@Override
	public void removeClient(String nickName) {
		clients.remove(nickName);
		userNum--;
	}

}
