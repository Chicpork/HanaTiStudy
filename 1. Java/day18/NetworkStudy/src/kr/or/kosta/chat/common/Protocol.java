package kr.or.kosta.chat.common;

public interface Protocol {
	public static final String DELEMETER = "☆★";
	
	public static final int CONNECT = 1000;
	public static final int MULTI_CHAT_MESSAGE = 2000;
	public static final int DISCONNECT = 3000;
	public static final int USER_DISCONNECT = 4000;
	public static final int USER_LOGIN_FAIL = 4001;
	public static final int USER_LIST = 5000;
	
	
	public static final int CONNECT_RESULT = 1001;
}