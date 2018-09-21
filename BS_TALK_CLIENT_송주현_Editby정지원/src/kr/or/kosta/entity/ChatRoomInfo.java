package kr.or.kosta.entity;
/**
 * 대화방 정보 저장용 클래스
 * ChatClient의 HashMap에 저장됨
 * @author 송주현
 *
 */
public class ChatRoomInfo {

	int roomNum; //방 번호 (HashMap Key로 사용)
	String title; //방 제목
	int currentMemNum; //방 접속한 현재 인원
	int maxMemNum; //방 최대 인원
	String roomMaster; //방장이름
	
	public ChatRoomInfo(int roomNum, String title, int currentMemNum, int maxMemNum, String roomMaster) {
		super();
		this.roomNum = roomNum;
		this.title = title;
		this.currentMemNum = currentMemNum;
		this.maxMemNum = maxMemNum;
		this.roomMaster = roomMaster;
	}
	
	

	public int getRoomNum() {
		return roomNum;
	}



	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public int getCurrentMemNum() {
		return currentMemNum;
	}



	public void setCurrentMemNum(int currentMemNum) {
		this.currentMemNum = currentMemNum;
	}



	public int getMaxMemNum() {
		return maxMemNum;
	}



	public void setMaxMemNum(int maxMemNum) {
		this.maxMemNum = maxMemNum;
	}



	public String getRoomMaster() {
		return roomMaster;
	}



	public void setRoomMaster(String roomMaster) {
		this.roomMaster = roomMaster;
	}



	public String toString() {

		return "room no."+roomNum+"  "+title+"  ["+currentMemNum+"  /  "+maxMemNum+"]";
	}
	
	public String toStringWithoutMem() {

		return "room no."+roomNum+"  "+title;
	}
	
	
}
