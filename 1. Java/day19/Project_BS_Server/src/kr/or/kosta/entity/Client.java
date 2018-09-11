package kr.or.kosta.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import kr.or.kosta.common.Protocol;

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

	/** 클라이언트 식별자 */
	private String nickName;
	/** 클라이언트 방 식별자 */
	private String roomName;

	public Client(ChatServer chatServer, Socket socket) throws IOException {
		this.chatServer = chatServer;
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

	@Override
	public void run() {
		receiveMessage();
	}

	public void receiveMessage() {
		while (running) {
			String clientMessage = null;
			try {
				clientMessage = in.readLine();

				process(clientMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		close();
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
		case Protocol.LOGIN:
			loginProcess(tokens);
			break;
		case Protocol.WAITING:

			break;
		case Protocol.CHATTING:

			break;
		}

	}

	public void loginProcess(String[] tokens) {
		int protocol2 = Integer.parseInt(tokens[1]);
		switch (protocol2) {
		case Protocol.CHECK:
			if (chatServer.isExistClient(tokens[2])) {
				sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.FAIL);
			} else {
				sendMessage(Protocol.LOGIN + Protocol.DELEMETER + Protocol.SUCCESS);
				nickName = tokens[2];
			}
		case Protocol.SUCCESS:
			WaitingRoomServer waitingRoomServer = chatServer.getWaitingRoomServer();
			if(waitingRoomServer == null) {
//				throw new Exception("웨이팅룸서버 문제");
			}
			
			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.USER_LIST + waitingRoomServer.getClientsList());
			sendMessage(Protocol.WAITING + Protocol.DELEMETER + Protocol.ROOM_LIST + chatServer.getChatRoomList());

			waitingRoomServer.addClient(this);
			waitingRoomServer.sendMessageToAll(Protocol.WAITING + Protocol.DELEMETER + Protocol.SC_NEW_USER + Protocol.DELEMETER + nickName);
			
		case Protocol.FAIL:
			running = false;
		}
	}

	public void sendMessage(String message) {
		out.println(message);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
