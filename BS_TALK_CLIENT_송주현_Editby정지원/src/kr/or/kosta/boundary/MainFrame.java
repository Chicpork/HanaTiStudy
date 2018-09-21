package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import kr.or.kosta.common.Protocol;
import kr.or.kosta.entity.ChatClient;

@SuppressWarnings("serial")
public class MainFrame extends Frame {
	public static final int WIDTH=700;
	public static final int HEIGHT=500;
	
	public int CURRENTROOM=0; //user가 현재 어느 panel에 있는지 저장하는 변수
	public static final int LOGINROOM=1; 
	public static final int WAITINGROOM=2;
	public static final int CHATTINGROOM=3;
	
	
	ChatClient chatClient; //서버와 통신을 위해
	
	LoginPanel loginPanel; //로그인
	WaitingRoomPanel waitingRoomPanel; //대기실
	ChatRoomPanel chatRoomPanel; //채팅방

	CardLayout cardLayout; 
	Panel cardPanel; //cardLayout 관리
	Font titleF;	
	
	public MainFrame() {
		this("BS TALK");
	}
	
	public MainFrame(String title) {
		super(title);
		chatClient = new ChatClient(this);
		titleF= new Font("Serif",Font.BOLD,30);
		
		loginPanel= new LoginPanel(this);
		waitingRoomPanel= new WaitingRoomPanel(this);
		chatRoomPanel = new ChatRoomPanel(this);
		
		cardPanel = new Panel();
		cardLayout = new CardLayout();
		
		CURRENTROOM=LOGINROOM;
	}
	
	public ChatClient getChatClient() {
		return chatClient;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void setLoginPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	public void setChatClient(ChatClient chatClient) {
		this.chatClient = chatClient;
	}
	
	public WaitingRoomPanel getWaitingRoomPanel() {
		return waitingRoomPanel;
	}
	
	public void setCURRENTROOM(int cURRENTROOM) {
		CURRENTROOM = cURRENTROOM;
	}

	public int getCURRENTROOM() {
		return CURRENTROOM;
	}
	
	public ChatRoomPanel getChatRoomPanel() {
		return chatRoomPanel;
	}

	public void setChatRoomPanel(ChatRoomPanel chatRoomPanel) {
		this.chatRoomPanel = chatRoomPanel;
	}

	public void setContents() {
		cardPanel.setLayout(cardLayout);
		
		loginPanel.setContents();
		loginPanel.eventRegist();
		cardPanel.add("LOGIN", loginPanel);
		
		waitingRoomPanel.setContents();
		waitingRoomPanel.eventRegist();
		cardPanel.add("waitingRoom", waitingRoomPanel);
		
		chatRoomPanel.setContents();
		chatRoomPanel.eventRegist();
		cardPanel.add("chatRoom", chatRoomPanel);
		
		add(cardPanel,BorderLayout.CENTER);
	}
	
	public void changeCard(String name) {
		switch(name) {
		case "LOGIN":
			setCURRENTROOM(LOGINROOM); //현재위치 변경
			break;
		case "waitingRoom":
			setCURRENTROOM(WAITINGROOM); //현재위치 변경
			break;
		case "chatRoom":
			setCURRENTROOM(CHATTINGROOM); //현재위치 변경
			break;
		}
		cardLayout.show(cardPanel, name);
	}
	
	/**
	 * 서버에 연결하기
	 */
	public void connect() {
		try {
			//소켓 열고 서버에 연결
			chatClient.connectServer();
			
			// 서버로 닉네임 검사해달라고 요청하기
			String nickName= loginPanel.nickNameTF.getText().trim();
			if(nickName.length()==0) {
				throw new Exception("nickname is empty..");
			}
			chatClient.setMyName(nickName); //자신의 닉네임 설정
			chatClient.sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.CHECK + Protocol.DELEMETER + nickName);
			chatClient.receiveMessage(); //쓰레드 실행
		} catch (Exception e) {
			//conenctServer() 에러
			JOptionPane.showMessageDialog(null, e.getMessage(), "연결 실패", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * 서버와 연결 끊기 요청하기
	 */
	public void askDisconnect() {
		//이 user의 현재 위치가 어디인지 서버에게 알려주면서 연결 끊기 요청
		if(CURRENTROOM==LOGINROOM) {
			chatClient.sendMessage(Protocol.LOGIN+Protocol.DELEMETER+Protocol.CS_DISCONNECT);
			
		}else if(CURRENTROOM==WAITINGROOM) {
			chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_DISCONNECT);
			
		}else if(CURRENTROOM==CHATTINGROOM) {
			chatClient.sendMessage(Protocol.CHATTING+Protocol.DELEMETER+Protocol.CS_DISCONNECT);
			
		}
	}
	
	/**
	 * 방 생성 요청하기
	 * @param title 만들 방의 제목
	 * @param maxNum 만들 방의 최대 인원
	 */
	public void askCreateRoom(String title, String maxNum) {
		chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_NEW_ROOM+Protocol.DELEMETER+title+Protocol.DELEMETER+maxNum);
	}
	
	/**
	 * 방 정보 요청하기
	 * @param roomNum 정보 조회할 방의 번호
	 */
	public void askShowRoom(int roomNum) {
		chatClient.setSelectedRoomNum(roomNum);
		chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_ROOM_INFO+Protocol.DELEMETER+roomNum);
	}
	
	/**
	 * 방 입장 요청하기
	 */
	public void askEnterRoom() {
		chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_ENTER_ROOM);
	}
	
	/**
	 * 방 나가기 요청하기
	 */
	public void askExitRoom() {
		chatClient.sendMessage(Protocol.CHATTING+Protocol.DELEMETER+Protocol.EXIT_ROOM);
	}
	
	/**
	 * 초대 보내기 요청하기
	 */
	public void askSendInvitation() {
		chatClient.sendMessage(Protocol.CHATTING+Protocol.DELEMETER+Protocol.CS_INVITE+Protocol.DELEMETER+Protocol.USER_LIST);
	}
	
	/**
	 * 초대 수락 요청하기
	 * @param name 나를 초대한 사람 이름
	 */
	public void askAcceptInvitation(String name) {
		chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_INVITE+Protocol.DELEMETER+Protocol.SUCCESS+Protocol.DELEMETER+name);
		waitingRoomPanel.getReceiveInvitaionD().setVisible(false);
	}
	
	/**
	 * 초대 거절 요청하기
	 * @param name 나를 초대한 사람 이름
	 */
	public void askDenyInvitation(String name) {
		chatClient.sendMessage(Protocol.WAITING+Protocol.DELEMETER+Protocol.CS_INVITE+Protocol.DELEMETER+Protocol.FAIL+Protocol.DELEMETER+name);
		waitingRoomPanel.getReceiveInvitaionD().setVisible(false);
	}
	
	public void finish() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	public Point getCenterPoint() {
		Point centerP=new Point();
		centerP.x = this.getLocation().x+(WIDTH/3);
		centerP.y = this.getLocation().y+(HEIGHT/3);
		return centerP;
	}
	
	public void eventRegist() {
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				askDisconnect();
				finish();
			}
		});
	}
	
}
