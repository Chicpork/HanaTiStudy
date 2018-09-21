package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
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

import kr.or.kosta.common.Protocol;

@SuppressWarnings("serial")
public class ChatRoomPanel extends Panel {

	MainFrame frame; // CardLayout으로 MainFrame을 제어하기 위해 선언
	Label titleL, userListL;
	Label blankL1, blankL2;
	TextArea chatTA;
	List userList;
	TextField inputTF;
	Button sendB, inviteB, exitB;
	Choice whisperC;

	SendInvitationDialog sendInvitationD; //초대할 때 뜨는 대화상자

	GridBagLayout gridBagLayout; // List부분에 사용
	GridBagConstraints gridBagConstraints;

	String whisperSelected; //선택된 user 이름
	String roomTitle; //이 방의 제목

	public ChatRoomPanel(MainFrame frame) {
		this.frame = frame;

		titleL = new Label(" title");
		titleL.setFont(getFrame().titleF);
		userListL = new Label("users in here");
		userListL.setFont(getFrame().titleF);
		blankL1 = new Label();
		blankL2 = new Label();
		chatTA = new TextArea();
		chatTA.setEditable(false);
		userList = new List();
		inputTF = new TextField(50);
		sendB = new Button("send");
		sendB.setFont(new Font("", Font.BOLD, 12));
		inviteB = new Button("invite");
		inviteB.setFont(new Font("", Font.BOLD, 12));
		exitB = new Button("exit");
		exitB.setFont(new Font("", Font.BOLD, 12));
		whisperC = new Choice();
		whisperC.add("To All");
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String title) {
		this.roomTitle = title;
		titleL.setText("      " + roomTitle);
	}

	public void setRoomTitleByRoomNum(int roomNum) {
		this.roomTitle = frame.chatClient.chatRooms.get(roomNum).getTitle();
		titleL.setText("      " + roomTitle);
	}

	public MainFrame getFrame() {
		return frame;
	}

	public void setSendInvitationD(SendInvitationDialog sendInvitationD) {
		this.sendInvitationD = sendInvitationD;
	}

	public SendInvitationDialog getSendInvitationD() {
		return sendInvitationD;
	}

	public void setContents() {
		// setBackground(Color.MAGENTA); //패널 돌아가는거 구분용

		setLayout(new BorderLayout(10, 0));

		Panel leftP = new Panel();
		leftP.setLayout(new BorderLayout(5, 0));
		leftP.add(titleL, BorderLayout.NORTH);
		leftP.add(chatTA, BorderLayout.CENTER);
		leftP.add(blankL1, BorderLayout.WEST);

		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();
		Panel inputP = new Panel(gridBagLayout);

		setCons(whisperC, 0, 0, 1, 1, 1, 1, new Insets(5, 20, 5, 0));
		inputP.add(whisperC);
		setCons(inputTF, 1, 0, 1, 1, 3, 1, new Insets(5, 10, 5, 10));
		inputP.add(inputTF);
		setCons(sendB, 2, 0, 1, 1, 0, 1, new Insets(5, 0, 5, 0));
		inputP.add(sendB);
		leftP.add(inputP, BorderLayout.SOUTH);

		Panel rightP = new Panel();
		rightP.setLayout(new BorderLayout(5, 0));
		rightP.add(userListL, BorderLayout.NORTH);
		rightP.add(userList, BorderLayout.CENTER);
		rightP.add(blankL2, BorderLayout.EAST);
		Panel buttonP = new Panel();
		buttonP.add(inviteB);
		buttonP.add(exitB);
		rightP.add(buttonP, BorderLayout.SOUTH);

		add(leftP, BorderLayout.CENTER);
		add(rightP, BorderLayout.EAST);

		chatTA.setBackground(new Color(225, 216, 255));
		inputTF.setBackground(new Color(225, 216, 255));
		whisperC.setBackground(new Color(225, 216, 255));
		userList.setBackground(new Color(225, 216, 255));

		whisperSelected = "To All";
	}

	private void setCons(Component component, int gridX, int gridY, int gridWidth, int gridHeight, double weightX,
			double weightY, Insets inset) {
		gridBagConstraints.gridx = gridX; // x축
		gridBagConstraints.gridy = gridY; // y축
		gridBagConstraints.gridwidth = gridWidth;// 몇개의 격자를 합칠 것인가
		gridBagConstraints.gridheight = gridHeight; // 몇개의 격자를 합칠 것인가

		gridBagConstraints.weightx = weightX;
		gridBagConstraints.weighty = weightY;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		gridBagConstraints.insets = inset;

		gridBagLayout.setConstraints(component, gridBagConstraints);
		// add(component); //컴포넌트 고유의 크기를 유지하면서 add
	}

	public void appendMessage(String message) {
		chatTA.append(message + "\n");
	}

	/**
	 * 귓속말 또는 전체메세지로 채팅 보내기
	 */
	public void sendMessage() {
		String message = inputTF.getText();
		if (message == null || message.trim().length() == 0) {
			return;
		}
		inputTF.setText("");
		if (whisperSelected.equals("To All")) { // 전체메세지
			frame.chatClient.sendMessage(
					Protocol.CHATTING + Protocol.DELEMETER + Protocol.CS_MESSAGE + Protocol.DELEMETER + message);
		} else {
			System.out.println("whisper");
			frame.chatClient.sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.WHISPER + Protocol.DELEMETER
					+ whisperSelected + Protocol.DELEMETER + message);
		}
	}

	/**
	 * 초대 메세지 보내기
	 * @param name 초대할 사람 이름
	 */
	public void sendInvitation(String name) {
		// 실제로 초대 메세지 보내기
		frame.chatClient.sendMessage(Protocol.CHATTING + Protocol.DELEMETER + Protocol.CS_INVITE + Protocol.DELEMETER
				+ Protocol.USER + Protocol.DELEMETER + name);
	}

	public void clearUserList() {
		userList.removeAll();
	}

	public void addUserListByString(String name) {
		userList.add(name);
	}

	public void deleteUserListByString(String name) {
		userList.remove(name);
	}

	public void addWhisperChoice(String name) {
		whisperC.add(name);
	}

	public void deleteWhisperChoice(String name) {
		whisperC.remove(name);
	}

	public void clearWhisperChoice() {
		whisperC.removeAll();
		whisperC.add("To All");
	}

	public void clearTA() {
		chatTA.setText("");
	}
	
	public void clearAllInfo() {
		clearTA();
		clearWhisperChoice();
		clearUserList();
	}

	public void eventRegist() {

		sendB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		inputTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

		whisperC.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				whisperSelected = whisperC.getSelectedItem();
			}
		});

		inviteB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendInvitationD = new SendInvitationDialog(frame, "초대하기", true);
				sendInvitationD.setChatPanel(frame.getChatRoomPanel());
				frame.getChatRoomPanel().setSendInvitationD(sendInvitationD);

				frame.askSendInvitation(); // 초대할거라고 알리기 (실제 초대 보내기는 dialog에서)
				sendInvitationD.callThisDialog(); //이 dialog 부르기
			}
		});

		exitB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 현재 채팅방 나가기
				// 대기방으로 들어가기
				frame.getWaitingRoomPanel().clearAllInfo();
				frame.askExitRoom();
			}
		});
	}
}
