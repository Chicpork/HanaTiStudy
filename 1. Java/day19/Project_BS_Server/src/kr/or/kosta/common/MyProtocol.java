package kr.or.kosta.common;

public interface MyProtocol {
	public static final String DELEMETER = "☆★";

	
	// 채팅방 관련 프로토콜
	public static final int WAITING_ROOM = 100;
	public static final int CHAT_ROOM = 200;
	public static final int NEW_ROOM = 201;	
	public static final int EXIST_ROOM = 202;	
	
	public static final int CONNECT = 1000;
	public static final int CONNECT_RESULT = 1001;
	
	public static final int MULTI_CHAT_MESSAGE = 2000;
	
	public static final int DISCONNECT = 4000;
	public static final int USER_DISCONNECT = 4001;
	
	public static final int USER_LOGIN_FAIL = 5000;
	
	public static final int USER_LIST = 6000;
	
	
}