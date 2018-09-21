package kr.or.kosta.common;

public interface Protocol {
   public static final String DELEMETER = ",J,H,J,W,J,J,";

   public static final int LOGIN = 1000;

   public static final int WAITING = 2000;

   public static final int SC_NEW_USER = 2010;
   public static final int SC_NEW_ROOM = 2020;
   public static final int SC_NEW_ROOM_TO_WAIT = 2021;
   
   public static final int SC_DELETE_USER = 2030;
   public static final int SC_DELETE_ROOM = 2040;
   public static final int SC_ROOM_INFO = 2050;
   public static final int SC_ENTER_ROOM = 2060;
   public static final int CS_NEW_USER = 2110;
   public static final int CS_NEW_ROOM = 2120;
   public static final int CS_DELETE_USER = 2130;
   public static final int CS_DELETE_ROOM = 2140;
   public static final int CS_ROOM_INFO = 2150;
   public static final int CS_ENTER_ROOM = 2160;
   

   public static final int CHATTING = 3000;

   public static final int EXIT_ROOM = 3010;
   public static final int SC_MESSAGE = 3020;
   public static final int CS_MESSAGE = 3120;
   public static final int SC_INVITE = 3030;
   public static final int CS_INVITE = 3130;
   public static final int WHISPER = 3040;
   
   
   public static final int SC_DISCONNECT = 9000;
   public static final int CS_DISCONNECT = 9010;
   public static final int SUCCESS = 9100;
   public static final int FAIL = 9110;
   public static final int CHECK = 9120;
   public static final int USER_LIST = 9200;
   public static final int ROOM_LIST = 9210;
   public static final int USER_NUM = 9300;
   public static final int USER = 9400;
   
   
}