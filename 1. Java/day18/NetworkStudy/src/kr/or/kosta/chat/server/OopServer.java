package kr.or.kosta.chat.server;

import java.io.IOException;

public class OopServer {

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		try {
			chatServer.startUp();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		chatServer.shutDown();

	}

}
