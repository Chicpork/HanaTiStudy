package day10;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

public class ChatFrame extends Frame {
	Label serverLabel;
	TextField serverTF, inputTF;
	Button connectB, sendB;
	TextArea messageTA;
	List userList;

	public ChatFrame() {
		this("이름없음");
	}

	public ChatFrame(String title) {
		super(title);
		serverLabel = new Label("SERVER");
		serverTF = new TextField();
		inputTF = new TextField();
		connectB = new Button("CONNECT");
		sendB = new Button("SEND");
		messageTA = new TextArea();
		userList = new List();

	}

	// 화면 배치
	public void setContents() {
		Panel northP = new Panel();
		northP.setLayout(new BorderLayout());
		northP.add(serverLabel, BorderLayout.WEST);
		northP.add(serverTF, BorderLayout.CENTER);
		northP.add(connectB, BorderLayout.EAST);

		Panel southP = new Panel(new BorderLayout());
		southP.add(inputTF, BorderLayout.CENTER);
		southP.add(sendB, BorderLayout.EAST);

		add(northP, BorderLayout.NORTH);
		add(messageTA, BorderLayout.CENTER);
		add(userList, BorderLayout.EAST);
		add(southP, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("Kotalk");
		frame.setContents();
		frame.setSize(400, 500);
		frame.setVisible(true);
	}

}
