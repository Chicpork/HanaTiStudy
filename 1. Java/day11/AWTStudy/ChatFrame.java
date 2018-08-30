package day10_11;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

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
		serverLabel = new Label("서버");
		serverTF = new TextField();
		inputTF = new TextField();
		connectB = new Button("연결");
		sendB = new Button("전송");
		messageTA = new TextArea();
		userList = new List();
	}

	// 화면 배치
	public void setContents() {
		
		connectB.setFont(new Font("궁서", Font.BOLD, 20));
		
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

		// 버튼 활성화/비활성화 버튼
//		connectB.setEnabled(false);
		
		// 배경화면 색 지정
//		connectB.setBackground(new Color(255, 0, 255));
//		connectB.setBackground(Color.BLUE);
//		connectB.setForeground(Color.WHITE);
		
//		setColorAll(Color.RED);
		
	}
	
	public void setCenter() {
//		Runtime.getRuntime().exec(command);
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = (int) ((dim.getWidth()-this.getWidth())/2);
		int height = (int) ((dim.getHeight() - getSize().getHeight())/2);
		setLocation(width, height);
	}
	
	private void setColorAll(Color bg) {
		Component[] components = getComponents();
		for (Component component : components) {
			if(component instanceof Panel) {
				Component[] cs = ((Panel) component).getComponents();
				for (Component component2 : cs) {
					component2.setBackground(bg);
					component.setForeground(Color.WHITE);
				}
			}
			component.setBackground(bg);
			component.setForeground(Color.WHITE);
		}
	}

	public static void main(String[] args) {
		ChatFrame frame = new ChatFrame("Kotalk");
		frame.setContents();
		frame.setSize(400, 500);
		frame.setCenter();
		frame.setVisible(true);
	}

}
