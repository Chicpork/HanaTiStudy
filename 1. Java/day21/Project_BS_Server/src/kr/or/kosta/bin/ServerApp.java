package kr.or.kosta.bin;

import javax.swing.JOptionPane;

import kr.or.kosta.boundary.ServerFrame;

public class ServerApp {

	public static void main(String[] args) {
		
		// 서버 관련 프레임 생성
		ServerFrame frame = new ServerFrame("SERVER");
		
		try {
			frame.setSize(600, 700);
			frame.initComponent();
			frame.setContents();
			frame.eventRegist();
			frame.setVisible(true);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "서버 IP 주소 받기 실패로 실행 오류 발생", "실행 실패!", JOptionPane.INFORMATION_MESSAGE);
		}
		

	}

}
