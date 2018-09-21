package kr.or.kosta.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JOptionPane;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.common.Protocol;

/**
 * 서버와의 통신 대행자
 * 
 * @author 송주현
 *
 */

public class ChatClient {

	public static final String SERVER = "192.168.0.8";
	public static final int PORT = 7777;

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	private MainFrame frame;

	private boolean running;
	private String myName; // 이 client가 login에서 설정한 닉네임
	private int selectedRoomNum; // 방 리스트에서 선택된 방의 방 번호
	public HashMap<Integer, ChatRoomInfo> chatRooms; // 현재 생성된 전체 방들의 정보 저장

	public ChatClient(MainFrame frame) {
		this.frame = frame;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getSelectedRoomNum() {
		return selectedRoomNum;
	}

	public void setSelectedRoomNum(int selectedRoomNum) {
		this.selectedRoomNum = selectedRoomNum;
	}

	/**
	 * 서버와 연결하기
	 * 
	 * @throws Exception
	 */
	public void connectServer() throws Exception {
		try {
			socket = new Socket(SERVER, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			chatRooms = new HashMap<Integer, ChatRoomInfo>(50);
			running = true;
		} catch (Exception e) {
			throw new Exception("cannot find server..");
		}
	}

	/**
	 * 접속 종료
	 */
	public void stopClient() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}

	}

	/**
	 * 서버에 메세지 보내기
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		if (out != null) {
			out.println(message);
		}
	}

	/**
	 * 서버로부터 오는 메세지 받기 : 쓰레드로 구현
	 */
	public void receiveMessage() {
		new Thread() {
			@Override
			public void run() {
				while (running) {
					String serverMessage = null;
					try {
						serverMessage = in.readLine();
						process(serverMessage);
					} catch (IOException e) {
						break;
					}
				} // while(running)
				stopClient();
			} // run
		}.start();
	}

	/**
	 * 서버로부터 온 메세지를 파싱해서 처리하기
	 * 
	 * @param message
	 */
	public void process(String message) {
		System.out.println(message);
		String[] tokens = message.split(Protocol.DELEMETER);
		int protocol = Integer.parseInt(tokens[0]);

		switch (protocol) {

		case Protocol.LOGIN: // 로그인 결과 받아서 처리
			loginProcess(Integer.parseInt(tokens[1]));// success/fail 받음
			break;

		case Protocol.WAITING: // user가 대기방에 있을 때 처리
			waitingProcess(tokens);
			break; // waiting

		case Protocol.CHATTING: // user가 대화방에 있을 때 처리
			chattingProcess(tokens);
			break;

		case Protocol.SC_DISCONNECT: // user가 접속종료할 때 처리
			stopClient();
			break;

		default:
			break;
		}
	} // process

	/**
	 * 로그인 결과 처리하기
	 * 
	 * @param token
	 */
	public void loginProcess(int token) {
		// 로그인 성공인지 실패인지 확인받기
		if (token == Protocol.SUCCESS) {
			// 로그인 성공했을 때 : 패널 바꾸기
			myName = frame.getLoginPanel().getNickNameTF();

			frame.changeCard("waitingRoom");
			frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
			sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.SUCCESS);
		} else {
			// 로그인 실패했을 때 : 소켓 닫기
			sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.FAIL);
			JOptionPane.showMessageDialog(frame, "used nickname");
			running = false;
		}
	}

	/**
	 * user가 대기방에 있을 때 경우의 수 처리하기
	 * 
	 * @param tokens 서버로부터 받은 메세지를 파싱해서 저장한 배열
	 */
	public void waitingProcess(String[] tokens) {
		int token = Integer.parseInt(tokens[1]);
		int token2 = 0;
		switch (token) {
		case Protocol.USER_LIST: // 대기 user 목록 받기
			// 최초로 waiting에 들어갔을 때 1번 실행
			frame.getWaitingRoomPanel().setWaitTitle(myName);
			int memNum = 0;
			for (int i = 2; i < tokens.length; i++) {
				frame.getWaitingRoomPanel().addWaitUserListByString(tokens[i]);
				memNum++;
			}
			break;
		case Protocol.ROOM_LIST: // 생성된 방들의 목록 받기
			// 최초로 waiting에 들어갔을 때 1번 실행
			chatRooms.clear(); // 초기화
			for (int i = 2; i < tokens.length; i = i + 5) {
				String roomNum = tokens[i];
				String title = tokens[i + 1];
				String currentNum = tokens[i + 2];
				String maxNum = tokens[i + 3];
				String roomMaster = tokens[i + 4];

				// hashMap에 추가
				ChatRoomInfo roomInfo = new ChatRoomInfo(Integer.parseInt(roomNum), title, Integer.parseInt(currentNum),
						Integer.parseInt(maxNum), roomMaster);
				chatRooms.put(roomInfo.getRoomNum(), roomInfo);

				// frame에 반영
				frame.getWaitingRoomPanel().addRoomList(roomInfo);
			} // for
			break;
		case Protocol.SC_ENTER_ROOM: // waiting에서 대화방으로 입장할때
			token2 = Integer.parseInt(tokens[2]);
			switch (token2) {
			case Protocol.SUCCESS: // 입장 성공
				System.out.println("selectedRoomNum:" + selectedRoomNum);
				frame.changeCard("chatRoom");
				frame.setSize(MainFrame.WIDTH, MainFrame.HEIGHT);
				if (tokens.length > 3) {
					frame.getChatRoomPanel().setRoomTitle(tokens[3]);
				} else {
					frame.getChatRoomPanel().setRoomTitleByRoomNum(selectedRoomNum);
				}
				break;
			case Protocol.FAIL: // 입장 실패
				System.out.println("fail &selectedRoomNum:" + selectedRoomNum);
				JOptionPane.showMessageDialog(frame, "enter room failed");
				break;
			}

			break;
		case Protocol.SC_NEW_USER: // 대기방에 새 user 들어왔을 때
			frame.getWaitingRoomPanel().addWaitUserListByString(tokens[2]);
			break;

		case Protocol.SC_NEW_ROOM: // 새 대화방이 생겼을 때
			System.out.println("selectedRoomNum:" + selectedRoomNum);
			token2 = Integer.parseInt(tokens[2]);
			switch (token2) {
			case Protocol.SUCCESS: // 방 생성 성공
				// 방 생성됨 - dialog 닫고 client 패널 변경
				frame.getWaitingRoomPanel().getCreateChatRoomD().setVisible(false);
				frame.changeCard("chatRoom");
				frame.getChatRoomPanel().setRoomTitle(tokens[3]);
				break;
			case Protocol.FAIL: // 방 생성 실패
				JOptionPane.showMessageDialog(frame, "creating failed");
				break;
			}// switch(success/fail) new room
			break;

		case Protocol.SC_NEW_ROOM_TO_WAIT: // 새로 생긴 방 1개의 정보만 받기
			// chatRooms.clear(); //초기화
			for (int i = 2; i < tokens.length; i = i + 5) {
				String roomNum = tokens[i];
				String title = tokens[i + 1];
				String currentNum = tokens[i + 2];
				String maxNum = tokens[i + 3];
				String roomMaster = tokens[i + 4];

				ChatRoomInfo roomInfo = new ChatRoomInfo(Integer.parseInt(roomNum), title, Integer.parseInt(currentNum),
						Integer.parseInt(maxNum), roomMaster);
				// hashMap에 반영
				chatRooms.put(roomInfo.getRoomNum(), roomInfo);

				// frame에 반영
				frame.getWaitingRoomPanel().setRoomList(chatRooms);
			} // for
			System.out.println("num of rooms: " + chatRooms.size());
			break;

		case Protocol.SC_DELETE_USER: // waiting에서 누군가 나갔을 때 userList에 반영
			// token[2]=나간 user
			frame.getWaitingRoomPanel().removeWaitUserListByString(tokens[2]);
			break;
		case Protocol.SC_DELETE_ROOM: // waiting에서 어떤 방이 삭제되었을 때 RoomList에 반영
			// token[2]=방번호
			frame.getWaitingRoomPanel().removeRoomByNum(Integer.parseInt(tokens[2]));
			chatRooms.remove(Integer.parseInt(tokens[2])); // 맵에서 삭제
			break;
		case Protocol.SC_ROOM_INFO: // 방 클릭했을 때 그 방의 정보 가져오기
			memNum = 0;
			String userList = "";
			memNum = Integer.parseInt(tokens[2]); // 현재 방에 참여중인 user수
			for (int i = 3; i < tokens.length; i++) {
				userList += tokens[i] + "\n";// 그 방에 참여중인 user목록
			}
			frame.getWaitingRoomPanel().setRoomMemNum(memNum);
			frame.getWaitingRoomPanel().setRoomInfoTA(memNum, userList);
			break; // roomInfo

		case Protocol.SC_INVITE: // 대기방에 있으면서 초대를 당했을 때
			String name = tokens[2]; // 초대한 사람 이름

			int roomInfoIndex = 3;
			String roomNum = tokens[roomInfoIndex];
			String title = tokens[roomInfoIndex + 1];
			String currentNum = tokens[roomInfoIndex + 2];
			String maxNum = tokens[roomInfoIndex + 3];
			frame.getWaitingRoomPanel().showInvitationDialog(name, roomNum, title, currentNum, maxNum); // 초대창 띄우기
			break;
		}// switch(token) in waiting
	}// waitingProcess

	/**
	 * user가 대화방에 있을 때 경우의 수 처리하기
	 * 
	 * @param tokens 서버로부터 받은 메세지를 파싱해서 저장한 배열
	 */
	public void chattingProcess(String[] tokens) {
		int token = Integer.parseInt(tokens[1]);
		switch (token) {
		case Protocol.SC_MESSAGE: // 채팅 메세지 보내기
			String message = tokens[2];
			frame.getChatRoomPanel().appendMessage(message);
			break;

		case Protocol.WHISPER: // 특정 user에게 귓속말 보내기
			frame.getChatRoomPanel().appendMessage(tokens[2]);
			break;

		case Protocol.USER_LIST: // 처음 방 들어왔을 때 기존 user들 목록 추가하기
			for (int i = 2; i < tokens.length; i++) {
				frame.getChatRoomPanel().addUserListByString(tokens[i]);
				frame.getChatRoomPanel().addWhisperChoice(tokens[i]); // 귓속말에도 추가
			}
			break;

		case Protocol.SC_NEW_USER: // 새로 들어온 user 1명만 기존 리스트에 추가하기
			frame.getChatRoomPanel().addUserListByString(tokens[2]);
			frame.getChatRoomPanel().addWhisperChoice(tokens[2]);
			break;

		case Protocol.SC_DELETE_USER: // 방에서 user 1명 나갔을 때 list에서 삭제하기
			frame.getChatRoomPanel().deleteUserListByString(tokens[2]);
			frame.getChatRoomPanel().deleteWhisperChoice(tokens[2]);
			break;

		case Protocol.SC_INVITE: // 대화방에서 대기방의 user를 초대하기
			switch (Integer.parseInt(tokens[2])) {
			case Protocol.USER_LIST: // 초대할 사람 설정(현재 대기방 user 목록 받아오기)
				frame.getChatRoomPanel().getSendInvitationD().clearUserList(); // 초기화
				for (int i = 3; i < tokens.length; i++) {
					frame.getChatRoomPanel().getSendInvitationD().addUserList(tokens[i]);
				}
				break;
			case Protocol.SUCCESS: // 초대 보내기 성공
				JOptionPane.showMessageDialog(frame, "초대 보내기 성공");
				break;

			case Protocol.FAIL: // 초대 보내기 실패
				JOptionPane.showMessageDialog(frame, "waiting상태가 아니거나 초대를 거절했습니다");
				break;
			}
			break; // case :sc_invite

		case Protocol.EXIT_ROOM: // 대화방에서 대기방으로 나갈 때
			frame.getWaitingRoomPanel().setRoomMemNum(0);
			frame.changeCard("waitingRoom");
			break;
		}
	}
}
