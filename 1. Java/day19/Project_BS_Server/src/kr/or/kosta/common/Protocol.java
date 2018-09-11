package kr.or.kosta.common;

public interface Protocol {
	public static final String DELEMETER = ",,,,,,";

	public static final int LOGIN = 1000;

	public static final int WAITING = 2000;

	public static final int SC_NEW_USER = 2010;
	public static final int SC_NEW_ROOM = 2020;
	public static final int SC_DELETE_USER = 2030;
	public static final int SC_DELETE_ROOM = 2040;
	public static final int CS_NEW_USER = 2150;
	public static final int CS_NEW_ROOM = 2160;
	public static final int CS_DELETE_USER = 2170;
	public static final int CS_DELETE_ROOM = 2180;

	public static final int CHATTING = 3000;

	public static final int SC_DISCONNECT = 9000;
	public static final int CS_DISCONNECT = 9010;
	public static final int SUCCESS = 9100;
	public static final int FAIL = 9110;
	public static final int CHECK = 9120;
	public static final int USER_LIST = 9200;
	public static final int ROOM_LIST = 9210;
}