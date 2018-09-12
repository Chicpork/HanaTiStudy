package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;

import kr.or.kosta.common.Protocol;

public class WaitingRoom implements Room {
 //요기 스트링값 뭐죠?
	private Hashtable<String, Client> clients;
	private int userLimit;
	private int userNum = 0;

	public WaitingRoom(int userLimit) {
		clients = new Hashtable<>();
		this.userLimit = userLimit;
	}

	public void shutDown() {

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
			throw new Exception("대기실 인원이 가득 찼습니다.");
		}
		clients.put(client.getNickName(), client);
		userNum++;
	}

	@Override
	public void removeClient(String nickName) {
		clients.remove(nickName);
		userNum--;
	}

	public void sendMessageToAll(String message) {
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}

}
