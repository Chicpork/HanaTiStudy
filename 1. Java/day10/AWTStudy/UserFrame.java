package day10;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class UserFrame extends Frame {
	Button eButton, wButton, nButton, sButton, cButton;

	public UserFrame() {
		this("이름없음");
	}

	public UserFrame(String title) {
		super(title);
		this.eButton = new Button("EAST");
		this.wButton = new Button("WEST");
		this.nButton = new Button("NORTH");
		this.sButton = new Button("SOUTH");
		this.cButton = new Button("CENTER");
	}

	// 화면 배치 기능
	public void setContents() {
		// 레이아웃매니저 교체
		setLayout(new FlowLayout());
		add(eButton, BorderLayout.EAST);
		add(wButton, BorderLayout.WEST);
		add(nButton, BorderLayout.NORTH);
		add(sButton, BorderLayout.SOUTH);
		add(cButton, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		UserFrame frame = new UserFrame("윈도우 타이틀");
		frame.setContents();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

}
