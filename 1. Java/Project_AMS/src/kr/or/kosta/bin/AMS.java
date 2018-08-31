package kr.or.kosta.bin;

import java.awt.Frame;
import java.util.List;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

/**
 * 은행 계좌 관리 어플리케이션
 * 
 * @author 정지원
 *
 */
public class AMS {

	public static void main(String[] args) {
		Frame frame = new Frame();
//		AccountManager accountManager = new AccountManager();
		
		MainFrame mainFrame = new MainFrame(frame);
//		mainFrame.setManager(accountManager);
		mainFrame.setContents();
		mainFrame.eventRegist();
		
		frame.add(mainFrame);
		frame.setSize(500, 800);
		frame.setVisible(true);
		
	}

}
