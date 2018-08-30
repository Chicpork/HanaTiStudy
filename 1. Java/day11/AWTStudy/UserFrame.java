package day10_11;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UserFrame extends Frame implements MouseListener, WindowListener {
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

	public void eventRegist() {
		// 이벤트 소스에 이벤트리스너 연결
		eButton.addMouseListener(this);
		wButton.addMouseListener(this);
		nButton.addMouseListener(this);
		sButton.addMouseListener(this);
		cButton.addMouseListener(this);
		addWindowListener(this);
	}

	public static void main(String[] args) {
		UserFrame frame = new UserFrame("윈도우 타이틀");
		frame.setContents();
		frame.eventRegist();
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 마우스를 클릭할 때 실행되는 메소드
		System.out.println("mouseClicked() Called...");
		Object eventSource = e.getSource();
		Button button = (Button) eventSource;
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		button.setBackground(new Color(r, g, b));

		if (eventSource == eButton) {
			System.out.println("East Clicked...");
		} else if (eventSource == wButton) {
			System.out.println("West Clicked...");

		} else if (eventSource == sButton) {
			System.out.println("South Clicked...");

		} else if (eventSource == nButton) {
			System.out.println("North Clicked...");

		} else if (eventSource == cButton) {
			System.out.println("Center Clicked...");
		} else {
			System.out.println("???");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 마우스를 누르고 있을 때
		System.out.println("mousePressed() Called...");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 마우스를 누르고 있다 땔 때
		System.out.println("mouseReleased() Called...");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 마우스가 영역에 들어오면 실행되는 메소드
		System.out.println("mouseEntered() Called...");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 마우스가 영역에서 빠져나갈 때 실행
		System.out.println("mouseExited() Called...");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// 창이 열릴 때 하고 싶은 작업들이 있을 때.
		System.out.println("창이 열렸습니다.");

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// 윈도우 창 종료 버튼 입력할 때
		setVisible(false);
		dispose(); // AWT는 OS로부터 그래픽 리소스를 얻어와 사용하는데 이를 반납해주는 메소드
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// 다시 최소화를 풀 때

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// 최소화 시켜 아이콘화 시킬 때

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// 윈도우 창이 다른 것에 가려져 있다가 다시 활성화 될때 할 것들

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// 창이 비활성화 될 때

	}
}
