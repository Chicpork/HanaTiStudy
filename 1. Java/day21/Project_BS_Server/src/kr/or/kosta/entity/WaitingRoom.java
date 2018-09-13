package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import kr.or.kosta.common.Protocol;

/**
 * Room 추상 클래스를 상속하는 대기방 클래스
 * 
 * @author 정지원
 *
 */
public class WaitingRoom extends Room {
	
	private Hashtable<String, Client> clients; // 대기방에 접속한 인원을 저장할 변수
	private int userLimit; // 대기방 최대 접속 가능 인원
	private int userNum = 0; // 대기방 현재 접속 인원

	/**
	 * 생성자
	 * 대기실 최대 접속 가능 인원을 받아 저장하고 clients를 생성
	 * @param userLimit 대기실 최대 인원
	 */
	public WaitingRoom(int userLimit) {
		clients = new Hashtable<>();
		this.userLimit = userLimit;
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

	@Override
	public boolean isExistClient(String nickName) {
		if (clients.containsKey(nickName)) {
			return true;
		}
		return false;
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
	public void sendMessageToAll(String message) {
		Enumeration<Client> iter = clients.elements();
		while (iter.hasMoreElements()) {
			Client client = iter.nextElement();
			client.sendMessage(message);
		}
	}

	@Override
	public Set<String> getAllNickNameList() {
		return clients.keySet();
	}
}
