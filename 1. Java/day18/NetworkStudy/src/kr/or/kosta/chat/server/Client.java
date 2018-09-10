package kr.or.kosta.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import kr.or.kosta.chat.common.Protocol;

/**
 * 클라이언트의 데이터 수신 및 처리
 * 
 * @author 정지원
 *
 */
public class Client extends Thread {
	private ChatServer chatServer;

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private boolean running;
	private long time;

	/** 클라이언트 식별자 */
	private String nickName;

	public Client(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		running = true;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setChatServer(ChatServer chatServer) {
		this.chatServer = chatServer;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Socket getSocket() {
		return socket;
	}
	
	public long getTime() {
		return time;
	}

	@Override
	public void run() {
		receiveMessage();
	}

	public void receiveMessage() {
		while (running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();
				System.out.println("[Debug] : 클라이언트 수신 데이터:" + clientMessage);

				process(clientMessage);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (socket != null) {
			try {
				
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 클라이언트의 메시지를 파싱하여 서비스 제공
	 * 
	 * @param message
	 */
	public void process(String message) {
		String[] tokens = message.split(Protocol.DELEMETER);
		int protocol = Integer.parseInt(tokens[0]);
		switch (protocol) {

		case Protocol.CONNECT:
			nickName = tokens[1];
			if (chatServer.isExistClient(nickName)) {
				sendMessage(Protocol.CONNECT_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + "FAIL");
			} else {
				
				String nickNameList = chatServer.getNickNameList();

				sendMessage(Protocol.CONNECT_RESULT + Protocol.DELEMETER + nickName + Protocol.DELEMETER + "SUCCESS"
						+ nickNameList);

				time = Long.parseLong(tokens[2]);
				System.out.println(time);
				chatServer.getClients().put(nickName, this);
				System.out.println("현재 인원 "+chatServer.getClientSize());
				
				chatServer.sendAllMessage(Protocol.CONNECT + Protocol.DELEMETER + nickName + Protocol.DELEMETER
						+ (nickName + "님이 입장하셨습니다."));
				
			}
			break;

		case Protocol.MULTI_CHAT_MESSAGE:
			chatServer.sendAllMessage(message);
			break;

		case Protocol.DISCONNECT:
			chatServer.sendAllMessage(Protocol.USER_DISCONNECT + Protocol.DELEMETER + nickName + Protocol.DELEMETER
					+ (nickName + "님이 퇴장하셨습니다."));
			sendMessage(Protocol.DISCONNECT + Protocol.DELEMETER + nickName);
			chatServer.removeClient(nickName);
			running = false;
			break;
			
		case Protocol.USER_LOGIN_FAIL:
			running = false;
			break;

		default:
			System.out.println("잘못된 프로토콜 타입입니다.");
		}
	}

	public void sendMessage(String message) {
		out.println(message);
	}

	public void close() {
		try {
			chatServer.removeClient(nickName);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
