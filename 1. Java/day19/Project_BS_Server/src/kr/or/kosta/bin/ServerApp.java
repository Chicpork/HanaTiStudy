package kr.or.kosta.bin;

import java.io.IOException;

import kr.or.kosta.entity.ChatServer;

public class ServerApp {

	public static void main(String[] args) {
		ChatServer chatServer = new ChatServer();
		try {
			chatServer.startUp();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
