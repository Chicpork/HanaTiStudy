package kr.or.kosta.boundary;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.or.kosta.entity.ChatServer;
import kr.or.kosta.entity.Room;

/**
 * 서버 관련 정보를 얻어오기 위한 화면 클래스
 * 
 * @author 정지원
 *
 */
@SuppressWarnings("serial")
public class ServerFrame extends Frame {
	private ChatServer chatServer; // 서버 구동을 위해 만들어진 변수

	// 프레임 관련 컴포넌트
	Label serverInfoL, serverIpPortL, serverRunningL, roomInfoL, allUserInfoL, selectedUserInfoL;
	Label blankL;
	JButton serverOnB, serverOffB;
	TextField serverInfoTF;
	List roomInfoList, allUserList, seletedUserList;
	GridBagLayout bagLayout = new GridBagLayout();
	GridBagConstraints bagConstraints = new GridBagConstraints();

	// 생성자
	public ServerFrame(String title) {
		setTitle(title);
	}

	/**
	 * 프레임에서 사용될 컴포넌트들 생성
	 * 
	 * @throws Exception
	 */
	public void initComponent() throws Exception {
		serverInfoL = new Label("CHATTING SERVER", Label.CENTER);
		serverInfoL.setFont(new Font("batang", Font.BOLD, 35));
		serverIpPortL = new Label("IP/Port : " + InetAddress.getLocalHost().getHostAddress() + " / " + ChatServer.PORT,
				Label.CENTER);
		serverIpPortL.setFont(new Font("batang", Font.BOLD, 15));
		serverRunningL = new Label("Running", Label.CENTER);
		serverRunningL.setFont(new Font("batang", Font.BOLD, 15));
		serverRunningL.setBackground(Color.RED);
		roomInfoL = new Label("Exist Room Infomation", Label.CENTER);
		allUserInfoL = new Label("All of Connected user List", Label.CENTER);
		selectedUserInfoL = new Label("Seleted Room user List", Label.CENTER);
		blankL = new Label();
		serverOnB = new JButton("ON");
		serverOffB = new JButton("OFF");
		serverInfoTF = new TextField();
		serverInfoTF.setEditable(false);
		roomInfoList = new List();
		allUserList = new List();
		seletedUserList = new List();
	}

	/**
	 * 생성된 컴포넌트들을 배치하는 기능
	 */
	public void setContents() {
		setLayout(bagLayout);
		Panel serverOnOffP = new Panel(bagLayout);
		setCons(serverInfoL, 0, 0, 2, 1, 1, 0, true, false, null);
		add(serverInfoL);
		setCons(serverIpPortL, 0, 0, 1, 1, 10, 0, true, false, null);
		serverOnOffP.add(serverIpPortL);
		setCons(serverRunningL, 1, 0, 1, 1, 1, 0, true, false, null);
		serverOnOffP.add(serverRunningL);
		setCons(serverOnB, 2, 0, 1, 1, 1, 0, false, false, null);
		serverOnOffP.add(serverOnB);
		setCons(serverOffB, 3, 0, 1, 1, 1, 0, false, false, null);
		serverOnOffP.add(serverOffB);
		setCons(blankL, 4, 0, 1, 1, 3, 0, false, false, null);
		serverOnOffP.add(blankL);
		setCons(serverOnOffP, 0, 1, 2, 1, 1, 0, true, false, null);
		add(serverOnOffP);
		setCons(serverInfoTF, 0, 2, 2, 1, 1, 0, true, false, new Insets(10, 50, 10, 50));
		add(serverInfoTF);
		setCons(roomInfoL, 0, 3, 1, 1, 1, 0, true, false, null);
		add(roomInfoL);
		setCons(allUserInfoL, 1, 3, 1, 1, 1, 0, true, false, null);
		add(allUserInfoL);
		setCons(roomInfoList, 0, 4, 1, 3, 1, 1, true, true, null);
		add(roomInfoList);
		setCons(allUserList, 1, 4, 1, 1, 1, 1, true, true, null);
		add(allUserList);
		setCons(selectedUserInfoL, 1, 5, 1, 1, 1, 0, true, false, null);
		add(selectedUserInfoL);
		setCons(seletedUserList, 1, 6, 1, 1, 1, 1, true, true, null);
		add(seletedUserList);
	}

	/**
	 * GridBagLayout에서 필요한 GridBagConstraints 의 값을 지정하는 기능
	 * 
	 * @param component  GridBagConstraints을 적용할 컴포넌트
	 * @param gridx      x 좌표값
	 * @param gridy      y 좌표값
	 * @param gridwidth  x 방향으로 차지할 크기
	 * @param gridheight y 방향으로 차지할 크기
	 * @param weightx    x 방향으로의 비중
	 * @param weighty    y 방향으로의 비중
	 * @param hori       수평방향으로 가득 채우는지 여부를 boolean 값으로 받음
	 * @param verti      수직방향으로 가득 채우는지 여부를 boolean 값으로 받음
	 * @param insets     내부 여백을 지정하는 값
	 */
	private void setCons(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty, boolean hori, boolean verti, Insets insets) {
		bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = gridx;
		bagConstraints.gridy = gridy;
		bagConstraints.gridwidth = gridwidth;
		bagConstraints.gridheight = gridheight;
		bagConstraints.weightx = weightx;
		bagConstraints.weighty = weighty;
		if (hori && verti) {
			bagConstraints.fill = GridBagConstraints.BOTH;
		} else if (hori) {
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		} else if (verti) {
			bagConstraints.fill = GridBagConstraints.VERTICAL;
		}
		if (insets != null) {
			bagConstraints.insets = insets;
		}
		bagLayout.setConstraints(component, bagConstraints);
	}

	/**
	 * 컴포넌트 이벤트 관련하여 실행하는 기능
	 */
	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		serverOnB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chatServer != null) {
					JOptionPane.showMessageDialog(null, "서버가 이미 구동중입니다.", "서버 오류", JOptionPane.INFORMATION_MESSAGE);
				} else {
					startChatServer();
					serverRunningL.setBackground(Color.GREEN);
				}
			}
		});

		serverOffB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeChatServer();
				serverRunningL.setBackground(Color.RED);
			}
		});

		roomInfoList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String[] tokens = roomInfoList.getSelectedItem().split("\\.");
				addSelectedUserList(Integer.parseInt(tokens[0].trim()));
			}
		});
	}

	/**
	 * 채팅서버 구동
	 */
	public void startChatServer() {
		chatServer = new ChatServer(this);
		chatServer.start();
	}

	/**
	 * 채팅서버 구동 종료
	 */
	public void closeChatServer() {
		if (chatServer != null) {
			chatServer.closeChatServer();
			chatServer.interrupt();
			roomInfoList.removeAll();
			allUserList.removeAll();
			seletedUserList.removeAll();
			chatServer = null;
		}
	}

	/**
	 * 방 정보 리스트에 새로운 방 추가
	 * 
	 * @param info 방 이름
	 */
	public void addRoomInfoList(String info) {
		roomInfoList.add(info);
	}

	/**
	 * 방 정보 리스트에서 방 제거
	 * 
	 * @param info 제거할 방 이름
	 */
	public void removeRoomInfoList(String info) {
		roomInfoList.remove(info);
	}

	/**
	 * 서버에 접속한 인원을 전체 유저 리스트에 추가하는 기능
	 * 
	 * @param nickName 추가할 닉네임
	 */
	public void addAllUserList(String nickName) {
		allUserList.add(nickName);
	}

	/**
	 * 전체 유저 리스트에서 제거하는 기능
	 * 
	 * @param nickName 제거할 닉네임
	 */
	public void removeAllUserList(String nickName) {
		allUserList.remove(nickName);
	}

	/**
	 * 선택된 방에 접속한 유저 리스트를 추가하는 기능
	 * 
	 * @param roomNumber 선택된 방 정보
	 */
	public void addSelectedUserList(int roomNumber) {
		seletedUserList.removeAll();
		Room room = chatServer.findRoom(roomNumber);
		for (String nickName : room.getAllNickNameList()) {
			seletedUserList.add(nickName);
		}
	}

	/**
	 * 서버에서 보내주는 메시지를 TextField로 전달하는 기능
	 * 
	 * @param message 서버가 보낸 메시지
	 */
	public void sendMessageToServerInfoTF(String message) {
		serverInfoTF.setText(message);
	}

	/**
	 * 서버를 종료하고 화면을 끄는 기능
	 */
	public void finish() {
		closeChatServer();
		setVisible(false);
		dispose();
		System.exit(0);
	}

}
