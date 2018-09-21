package kr.or.kosta.bin;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.ChatClient;

public class BS_Client_APP {

	public static void main(String[] args) {

		MainFrame frame = new MainFrame();
		
		frame.setContents();
		frame.eventRegist();
		frame.setVisible(true);
		
	}
}
