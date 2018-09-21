package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import kr.or.kosta.entity.ChatRoomInfo;

@SuppressWarnings("serial")
public class WaitingRoomPanel extends Panel {

	MainFrame frame; // CardLayout으로 MainFrame을 제어하기 위해 선언

	Label titleL, roomListL, waitUserListL, roomUserListL, roomListInfoL;
	Label myNameL;
	List roomList, waitUserList;
	TextArea roomInfoTA;
	TextField searchRoomTF;
	Button enterRoomB, createRoomB, exitB, searchRoomB;

	CreateChatRoomDialog createChatRoomD; //방 생성할때 뜨는 대화상자
	ReceiveInvitaionDialog receiveInvitaionD; //초대받을 때 뜨는 대화상자

	GridBagLayout gridBagLayout; // List부분에 사용
	GridBagConstraints gridBagConstraints;


	public static final int titleIndex = 5; //방 list의 각 String에서 방 제목이 시작하는 index
	int selectedRoomNum; //방 list에서 선택된 방의 번호

	public WaitingRoomPanel(MainFrame frame) {
		this.frame = frame;

		titleL = new Label("Waiting Room", Label.CENTER);
		titleL.setFont(new Font("Arial", Font.BOLD, 40));
		roomListL = new Label("Chat Rooms..");
		roomListL.setFont(new Font("Arial", Font.PLAIN, 25));
		roomListInfoL = new Label("num \t title \t\t\t\t num");
		waitUserListL = new Label("Waiting Users..");
		waitUserListL.setFont(new Font("Arial", Font.PLAIN, 25));
		roomUserListL = new Label("[Selected Room] 0 users");
		roomUserListL.setFont(new Font("Arial", Font.PLAIN, 25));
		myNameL = new Label();
		myNameL.setFont(new Font("Arial", Font.PLAIN, 25));

		roomList = new List();
		waitUserList = new List();
		roomInfoTA = new TextArea(5,5);
		
		searchRoomTF = new TextField();

		enterRoomB = new Button("enter");
		enterRoomB.setFont(new Font("", Font.BOLD, 12));
		createRoomB = new Button("create");
		createRoomB.setFont(new Font("", Font.BOLD, 12));
		exitB = new Button("exit");
		exitB.setFont(new Font("", Font.BOLD, 12));
		searchRoomB = new Button("검색");
		searchRoomB.setFont(new Font("", Font.BOLD, 12));
		
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
	}

	public MainFrame getFrame() {
		return frame;
	}

	public CreateChatRoomDialog getCreateChatRoomD() {
		return createChatRoomD;
	}

	public void setCreateChatRoomD(CreateChatRoomDialog createChatRoomD) {
		this.createChatRoomD = createChatRoomD;
	}

	public ReceiveInvitaionDialog getReceiveInvitaionD() {
		return receiveInvitaionD;
	}

	public void setWaitTitle(String name) {
//		titleL.setText(name + " in WaitingRoom");
		myNameL.setText("NickName  [ "+name+" ]");
	}

	public void setRoomMemNum(int memNum) {
		roomUserListL.setText("[Selected Room] "+memNum + " users");
	}

	public void setReceiveInvitaionD(ReceiveInvitaionDialog receiveInvitaionD) {
		this.receiveInvitaionD = receiveInvitaionD;
	}

	public void setContents() {
		// setBackground(Color.ORANGE); //패널 돌아가는거 구분용

		setLayout(new BorderLayout());
		
		Panel northP = new Panel(gridBagLayout);
		setCons(titleL, 0, 0, 1, 1, 5, 1);
		northP.add(titleL);
		setCons(myNameL, 1, 0, 1, 1, 1, 1,new Insets(15, 0, 0, 0));
		northP.add(myNameL);
		add(northP, BorderLayout.NORTH);

		Panel listP = new Panel();
		listP.setLayout(gridBagLayout);
		setCons(roomListL, 0, 0, 1, 1, 2, 0,new Insets(0, 50, -10, 0));
		listP.add(roomListL);
		setCons(roomList, 0, 1, 1, 3, 2, 1);
		listP.add(roomList);

		setCons(waitUserListL, 1, 0, 1, 1, 1, 0,new Insets(0, 50, -10, 0));
		listP.add(waitUserListL);
		setCons(waitUserList, 1, 1, 1, 1, 1, 1);
		listP.add(waitUserList);

		setCons(roomUserListL, 1, 2, 1, 1, 1, 0,new Insets(-10, 50, -5, 0));
		listP.add(roomUserListL);
		setCons(roomInfoTA, 1, 3, 1, 1, 1, 1);
		listP.add(roomInfoTA);
		add(listP, BorderLayout.CENTER);

		Panel buttonP = new Panel();
		buttonP.add(enterRoomB);
		buttonP.add(createRoomB);
		buttonP.add(exitB);
		add(buttonP, BorderLayout.SOUTH);

		setBackground(new Color(214, 229, 255));
		roomList.setBackground(new Color(255, 242, 253));
		waitUserList.setBackground(new Color(255, 242, 253));
		roomInfoTA.setBackground(new Color(255, 242, 253));
	}

	private void setCons(Component component, int gridX, int gridY, int gridWidth, int gridHeight, double weightX,
			double weightY) {
		gridBagConstraints.gridx = gridX; // x축
		gridBagConstraints.gridy = gridY; // y축
		gridBagConstraints.gridwidth = gridWidth;// 몇개의 격자를 합칠 것인가
		gridBagConstraints.gridheight = gridHeight; // 몇개의 격자를 합칠 것인가

		gridBagConstraints.weightx = weightX;
		gridBagConstraints.weighty = weightY;
		gridBagConstraints.fill = GridBagConstraints.BOTH;

		gridBagConstraints.insets = new Insets(10, 10, 10, 10);

		gridBagLayout.setConstraints(component, gridBagConstraints);
		// add(component); //컴포넌트 고유의 크기를 유지하면서 add
	}
	private void setCons(Component component, int gridX, int gridY, int gridWidth, int gridHeight, double weightX,
			double weightY, Insets inset) {
		gridBagConstraints.gridx = gridX; // x축
		gridBagConstraints.gridy = gridY; // y축
		gridBagConstraints.gridwidth = gridWidth;// 몇개의 격자를 합칠 것인가
		gridBagConstraints.gridheight = gridHeight; // 몇개의 격자를 합칠 것인가

		gridBagConstraints.weightx = weightX;
		gridBagConstraints.weighty = weightY;

		gridBagConstraints.insets = inset;

		gridBagLayout.setConstraints(component, gridBagConstraints);
		// add(component); //컴포넌트 고유의 크기를 유지하면서 add
	}

	public void addWaitUserListByString(String str) {
		waitUserList.add(str);
	}

	public void removeWaitUserListByString(String name) {
		waitUserList.remove(name);
	}

	public void setRoomInfoTA(int memNum, String userList) {
		roomInfoTA.setText("");
		roomInfoTA.append("No. " + selectedRoomNum + " ");
		roomInfoTA.append("<title : " + frame.chatClient.chatRooms.get(selectedRoomNum).getTitle() + "> \n");
		roomInfoTA.append("RoomMaster: "+frame.chatClient.chatRooms.get(selectedRoomNum).getRoomMaster()+" \n");
		roomInfoTA.append("memNum : " + memNum + " &  maxMemNum : "
				+ frame.chatClient.chatRooms.get(selectedRoomNum).getMaxMemNum() + "\n");
		roomInfoTA.append(userList);
	}

	public void addRoomList(ChatRoomInfo roomInfo) {
		String res = String.format("%-5s%s", roomInfo.getRoomNum(), roomInfo.getTitle());
		roomList.add(res);
	}

	public void setRoomList(HashMap<Integer, ChatRoomInfo> roomInfos) {
		clearRoomList();
		for (ChatRoomInfo info : roomInfos.values()) {
			addRoomList(info);
		}
	}

	public void removeRoomByNum(int roomNum) {
		ChatRoomInfo ch = frame.chatClient.chatRooms.get(roomNum);
		String title = String.format("%-5s%s", ch.getRoomNum(), ch.getTitle());
		roomList.remove(title);
	}

	public void clearWaitUserList() {
		waitUserList.removeAll();
	}

	public void clearRoomList() {
		roomList.removeAll();
	}

	public void clearRoomInfoTA() {
		roomInfoTA.setText("");
	}
	
	public void clearAllInfo() {
		clearWaitUserList();
		clearRoomList();
		clearRoomInfoTA();
	}

	/**
	 * 초대 받을때 뜨는 Dialog 보이기
	 * @param name 초대한 사람 이름
	 * @param roomNum 초대받은 방 번호
	 * @param title 초대받은 방 제목
	 * @param currentNum 초대받은 방 현재 인원
	 * @param maxNum 초대받은 방 최대 인원
	 */
	public void showInvitationDialog(String name, String roomNum, String title, String currentNum, String maxNum) {
		receiveInvitaionD = new ReceiveInvitaionDialog(frame, "초대받기", true);
		receiveInvitaionD.setWaitingRoomPanel(this);
		this.setReceiveInvitaionD(receiveInvitaionD);
		receiveInvitaionD.setInviteTA(name, roomNum, title, currentNum, maxNum);

		receiveInvitaionD.callThisDialog(); //이 Dialog 부르기
	}

	public void eventRegist() {

		//방 입장 요청하기
		enterRoomB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (roomList.getSelectedItem() == null || roomList.getSelectedItem().trim().length() == 0) {
					return; //선택되지 않았을 때 예외처리
				}
				frame.getChatRoomPanel().clearAllInfo();
				frame.askEnterRoom();
			}
		});
		
		// 대화방 생성 요청하기
		createRoomB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getChatRoomPanel().clearAllInfo();
				createChatRoomD = new CreateChatRoomDialog(frame, "방만들기", true); //Dialog 생성
				createChatRoomD.setWaitingPanel(frame.getWaitingRoomPanel());
				frame.getWaitingRoomPanel().setCreateChatRoomD(createChatRoomD);
				createChatRoomD.callThisDialog(); //이 Dialog 부르기
				
			}
		});

		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 나가기
				frame.askDisconnect();
				frame.finish();
			}
		});

		// 방 클릭했을 때 정보 띄우기
		roomList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = roomList.getSelectedItem();
				selectedRoomNum = Integer.parseInt(str.substring(0, titleIndex - 1).trim());
				String title = str.substring(titleIndex);
				System.out.println("[Num:" + selectedRoomNum + "]  title: " + title);
				frame.askShowRoom(selectedRoomNum); //방 정보 조회 요청하기
			}
		});

	}

}
