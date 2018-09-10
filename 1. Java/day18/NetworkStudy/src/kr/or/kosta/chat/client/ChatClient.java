package kr.or.kosta.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import kr.or.kosta.chat.common.Protocol;

/**
 * 서버와의 통신 대행자
 * 
 * @author 정지원
 *
 */
public class ChatClient {

	private ChatUI chatUI;

	public static final String SERVER = "127.0.0.1";
	public static final int PORT = 7777;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;

	public void setChatUI(ChatUI chatUI) {
		this.chatUI = chatUI;
	}

	public void connectServer() throws Exception {
		try {
			socket = new Socket(SERVER, PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			running = true;
		} catch (Exception e) {
			throw new Exception("서버를 찾을 수 없습니다..");
		}

	}

	public void stopClient() {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
		}
	}

	public void sendMessage(String message) {
		if (out != null) {
			out.println(message);
		}
	}

	public void receiveMessage() {
		new Thread(() -> {
			String serverMessage = null;
			while (running) {
				try {
					serverMessage = in.readLine();
					System.out.println("[Debug] : 서버 수신 데이터:" + serverMessage);
					process(serverMessage);
				} catch (IOException e) {
					System.out.println("네트워크가 단절되었습니다..");
					break;
				}
			}

			stopClient();

		}).start();

	}

	public void process(String message) {
		String[] tokens = message.split(Protocol.DELEMETER);
		int messageType = Integer.parseInt(tokens[0]);
		switch (messageType) {
		
		case Protocol.CONNECT:
			chatUI.messageTA.append("****  " + tokens[2] + " ****\n");
			chatUI.addUserList(tokens[1]);
			break;

		case Protocol.MULTI_CHAT_MESSAGE:
			chatUI.messageTA.append("[" + tokens[1] + "] : " + tokens[2] + "\n");
			break;

		case Protocol.DISCONNECT:
			running = false;
			break;

		case Protocol.USER_DISCONNECT:
			chatUI.removeUserList(tokens[1]);
			chatUI.messageTA.append("****  " + tokens[2] + " ****\n");
			break;

		case Protocol.CONNECT_RESULT:
			if (tokens[2].equals("FAIL")) {
				JOptionPane.showMessageDialog(null, "이미 사용중인 대화명입니다.\n다른 대화명을 사용해주세요.");
				sendMessage(Protocol.USER_LOGIN_FAIL + Protocol.DELEMETER);
				running = false;
			} else {
				chatUI.connectEnable(false);
				for (int i = 3; i < tokens.length; i++) {
					chatUI.addUserList(tokens[i]);
				}
			}
			break;

		default:
			System.out.println("잘못된 프로토콜 타입입니다.");
		}
	}

}
