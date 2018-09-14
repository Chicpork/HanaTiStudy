package kr.or.kosta.entity;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import kr.or.kosta.common.Protocol;

public class ChatRoom extends Room {

	private Hashtable<String, Client> clients; // 채팅방에 접속한 인원을 저장할 변수
	private String roomName; // 채팅방 생성시 지정되는 이름
	private String roomMaster; // 채팅방 방장 이름
	private int roomNum; // 채팅방 번호
	private int userNum = 0; // 채팅방 현재 접속 인원
	private int userLimit; // 채팅방 최대 접속 가능 인원

	/**
	 * 채팅방 이름을 설정하고 채팅방 최대 인원을 설정
	 * 
	 * @param roomName  채팅방 이름
	 * @param userLimit 채팅방 최대 인원
	 */
	public ChatRoom(String roomName, int userLimit) {
		clients = new Hashtable<>();
		this.roomName = roomName;
		this.userLimit = userLimit;
	}

	// getter-setter
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(String roomMaster) {
		this.roomMaster = roomMaster;
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

	/**
	 * 채팅방의 정보를 Protocol.DELEMETER 구분자를 통해 String으로 반환<br>
	 * "방 번호 / 방 이름 / 현재 인원 / 최대 인원" 순서로 보냄
	 * 
	 * @return
	 */
	public String getChatRoomInfo() {
		return Protocol.DELEMETER + getRoomNum() + Protocol.DELEMETER + getRoomName() + Protocol.DELEMETER
				+ getUserNum() + Protocol.DELEMETER + getUserLimit() + Protocol.DELEMETER + getRoomMaster();
	}

	/**
	 * 채팅방에 해당 닉네임을 가지는 유저가 존재하는 지 확인
	 * 
	 * @param nickName 확인할 닉네임
	 * @return
	 */
	public Client findClient(String nickName) {
		return clients.get(nickName);
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
