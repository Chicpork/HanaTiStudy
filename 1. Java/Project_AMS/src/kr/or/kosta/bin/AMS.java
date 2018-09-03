package kr.or.kosta.bin;

import java.awt.Frame;
import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

/**
 * 은행 계좌 관리 어플리케이션<br>
 * AccountManager를 통해 계좌와 관련된 기능들을 실행하고<br>
 * MainFrame을 통해 계좌 관련 화면 처리를 실행한다.
 * 
 * @author 정지원
 *
 */
public class AMS {

	public static void main(String[] args) {
		// 계좌를 관리할 매니저 만들기
		AccountManager accountManager = new AccountManager();
		
		// 예시로 넣어놓은 계좌들
		try {
			accountManager.open(new Account("1111-2222-3333", "정지원", 1234, 100000));
			accountManager.open(new Account("1111-2222-4444", "김기정", 1234, 100000));
			accountManager.open(new Account("1111-3333-3333", "정지원", 1234, 5000000));
			accountManager.open(new MinusAccount("2222-2222-3333", "김기정", 1234, 10000, 200000));
			accountManager.open(new MinusAccount("3333-2222-3333", "정지원", 1234, 100000, 200000));
			accountManager.open(new MinusAccount("4444-2222-3333", "홍길동", 1234, 200000, 100000));
		} catch (AccountException e) {
		}

		// MainFrame을 띄워줄 프레임 지정
		Frame frame = new Frame();
		frame.setSize(600, 600);

		// 은행 관련 프레임 만들기
		MainFrame mainFrame = new MainFrame(frame);
		mainFrame.setManager(accountManager);
		mainFrame.setContents();
		mainFrame.eventRegist();
		mainFrame.setCenter();

		frame.add(mainFrame);
		frame.setVisible(true);
	}

}
