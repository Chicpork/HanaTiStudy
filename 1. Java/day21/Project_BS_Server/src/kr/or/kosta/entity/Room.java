package kr.or.kosta.entity;

import java.util.Set;

/**
 * 방 정보를 담을 추상 클래스
 * 
 * @author 정지원
 *
 */
public abstract class Room {
	/**
	 * 방에 클라이언트를 추가하는 기능
	 * @param client 추가할 클라이언트
	 * @throws Exception
	 */
	public abstract void addClient(Client client) throws Exception;
	/**
	 * 방에서 클라이언트를 제거하는 기능
	 * @param nickName 제거할 클라이언트 닉네임
	 */
	public abstract void removeClient(String nickName);
	/**
	 * 방에 해당 닉네임을 가지는 클라이언트가 존재하는지 확인하는 기능
	 * @param nickName 확인할 닉네임
	 * @return
	 */
	public abstract boolean isExistClient(String nickName);
	/**
	 * 방에 존재하는 전체 인원 닉네임을 Protocol.DELEMETER 구분자를 이용해 String으로 반환
	 * @return
	 */
	public abstract String getClientsList();
	/**
	 * 방에 들어와 있는 모든 사람에게 메시지를 보내는 기능
	 * @param message 보낼 메시지
	 */
	public abstract void sendMessageToAll(String message);
	/**
	 * 방에 존재하는 전체 인원 닉네임을 Set으로 받아오는 기능
	 * @return
	 */
	public abstract Set<String> getAllNickNameList();
}
