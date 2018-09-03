package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

/**
 * 계좌 관련 정보를 확인하는 화면을 만드는 클래스
 * 
 * @author 정지원
 */
//Serial 경고 메시지를 없애기 위해 추가한 어노테이션
@SuppressWarnings("serial")
public class MainFrame extends Panel {
	// 계좌관리매니저와 프레임
	AccountManager manager;
	Frame frame;
	
	// 컴포넌트들과 출력할 값을 저장할 변수들
	Label accountTypeL, accountNumL, accountOwnerL, passwdL, depositMoneyL, borrowMoneyL, accountListL, moneyRateL;
	Choice accountTypeC;
	TextField accountNumTF, accountOwnerTF, passwdTF, depositMoneyTF, borrowMoneyTF;
	TextArea accountListTA;
	Button helpB, searchAccountNumB, deleteAccountB, searchAccountOwnerB, registNewAccountB, showAccountListB;
	GridBagLayout bagLayout;
	GridBagConstraints bagConstraints;
	Label[] blankL;	
	String block1, block2, specifications, help;

	// 생성자
	public MainFrame(Frame frame) {
		bagLayout = new GridBagLayout();
		accountTypeL = new Label("계좌종류");
		accountNumL = new Label("계좌번호");
		accountOwnerL = new Label("예금주명");
		passwdL = new Label("비밀번호");
		depositMoneyL = new Label("입금금액");
		borrowMoneyL = new Label("대출금액");
		accountListL = new Label("계좌목록");
		moneyRateL = new Label("(단위 : 원)");
		accountTypeC = new Choice();
		accountTypeC.add("전체");
		accountTypeC.add("입출금계좌");
		accountTypeC.add("마이너스계좌");
		accountNumTF = new TextField();
		accountOwnerTF = new TextField();
		passwdTF = new TextField();
		depositMoneyTF = new TextField();
		borrowMoneyTF = new TextField();
		borrowMoneyTF.setEnabled(false);
		accountListTA = new TextArea();
		accountListTA.setEditable(false);
		helpB = new Button("도움말");
		searchAccountNumB = new Button("조 회");
		deleteAccountB = new Button("삭 제");
		searchAccountOwnerB = new Button("검 색");
		registNewAccountB = new Button("신규등록");
		showAccountListB = new Button("전체조회");
		blankL = new Label[14];
		for (int i = 0; i < blankL.length; i++) {
			blankL[i] = new Label();
		}
		this.frame = frame;
		block1 = "---------------------------------------------------------------------------------------";
		block2 = "=======================================================================================";
		specifications = String.format("%1$8s%2$20s%3$10s%4$15s%5$15s","계좌종류","계좌번호     ","예금주명","현재잔액","대출금액");
		help =    "    1. 계좌종류\n"
				+ "계좌종류는 입출금계좌와 마이너스계좌 두가지가 존재합니다.\n\n" 
				+ "    2. 계좌번호\n"
				+ "계좌번호란은 은행에 계좌를 개설되어 있다면 자신의 계좌번호를 입력해 조회, 삭제가 가능합니다.\n"
				+ "주의사항으로 계좌번호의 길이는 8글자 이상이어야 하며 가장 앞과 끝의 띄어쓰기는 공백으로 간주됩니다.\n\n" 
				+ "    3. 예금주명\n"
				+ "예금주명을 입력해 해당 이름으로 만들어진 계좌를 검색할 수 있습니다.\n\n" 
				+ "    4. 비밀번호\n" 
				+ "비밀번호는 신규등록시 필요한 번호입니다.\n"
				+ "주의사항으로 비밀번호는 1~0까지의 정수만 가능합니다.\n\n" 
				+ "    5. 입금금액\n" 
				+ "입금금액은 신규등록시에 입금하는 금액을 입력하는 공간입니다.\n"
				+ "주의사항으로 금액은 정수단위만 입력 가능하며 소수나 분수, 문자는 입력이 불가능합니다.\n\n" 
				+ "    6. 대출금액\n"
				+ "대출금액은 마이너스계좌를 개설시 입력하는 란으로 얼만큼의 돈을 대출받을지 입력하면 됩니다.\n" 
				+ "주의사항은 입금금액과 동일합니다.\n\n"
				+ "    7. 조회, 삭제 버튼\n" 
				+ "조회 버튼은 사용자가 입력한 계좌번호와 동일한 계좌번호로 만들어진 계좌를 검색해줍니다.\n"
				+ "만약 해당 계좌가 존재하지 않는 경우 해당 메시지를 출력해줍니다.\n" 
				+ "삭제 버튼은 사용자가 입력한 계좌번호와 동일한 계좌번호로 만들어진 계좌를 삭제해줍니다.\n"
				+ "만약 해당 계좌가 존재하지 않는 경우 해당 메시지를 출력해줍니다.\n\n" 
				+ "    8. 검색 버튼\n"
				+ "검색 버튼은 사용자가 입력한 예금주명으로 만들어진 계좌를 출력해줍니다.\n\n" 
				+ "    9. 신규등록 버튼\n"
				+ "신규등록 버튼은 사용자가 입력한 정보로 은행에 계좌를 개설해줍니다. 이때 계좌종류도 설정해야합니다.\n\n" 
				+ "    10. 전체조회 버튼\n"
				+ "전체조회 버튼은 은행에 존재하는 모든 계좌 정보를 출력해줍니다.";
	}

	// 계좌 관리 매니저를 지정하기 위한 기능
	public void setManager(AccountManager manager) {
		this.manager = manager;
	}

	/**
	 * 화면 구성을 위한 기능<br>
	 * 전체 화면을 BorderLayout으로 구성하고 그 안에 패널들을 구성해 짜임새 있게 배치<br>
	 */
	public void setContents() {
		// 화면에 구성할 패널들 생성하고 각각 적당한 레이아웃 구성
		Panel northP = new Panel(bagLayout);
		Panel typeP = new Panel(bagLayout);
		Panel numP = new Panel(bagLayout);
		Panel ownerP = new Panel(bagLayout);
		Panel borrowP = new Panel(bagLayout);
		Panel listP = new Panel(bagLayout);
		Panel northB = new Panel(new BorderLayout(10, 0));

		// 각각의 세부 패널들에 원하는 컴포넌트들 삽입
		setCons(accountTypeL, 0, 0, 1, 1, 0, 0, false, false, new Insets(3, 5, 0, 0));
		typeP.add(accountTypeL);
		setCons(accountTypeC, 1, 0, 1, 1, 0, 0, false, false, new Insets(3, 0, 3, 0));
		typeP.add(accountTypeC);
		setCons(blankL[0], 2, 0, 1, 1, 1, 0, true, false, null);
		typeP.add(blankL[0]);
		setCons(helpB, 3, 0, 1, 1, 0, 0, false, false, new Insets(3, 0, 3, 15));
		typeP.add(helpB);

		setCons(searchAccountNumB, 0, 0, 1, 1, 0, 0, false, false, new Insets(3, 12, 3, 3));
		numP.add(searchAccountNumB);
		setCons(deleteAccountB, 1, 0, 1, 1, 0, 0, false, false, new Insets(3, 3, 3, 3));
		numP.add(deleteAccountB);
		setCons(blankL[1], 2, 0, 1, 1, 1, 0, true, false, null);
		numP.add(blankL[1]);

		setCons(searchAccountOwnerB, 0, 0, 1, 1, 0, 0, false, false, new Insets(3, 12, 3, 3));
		ownerP.add(searchAccountOwnerB);
		setCons(blankL[2], 1, 0, 1, 1, 1, 0, true, false, null);
		ownerP.add(blankL[2]);

		setCons(registNewAccountB, 0, 0, 1, 1, 0, 0, false, false, new Insets(3, 12, 3, 3));
		borrowP.add(registNewAccountB);
		setCons(showAccountListB, 1, 0, 1, 1, 0, 0, false, false, new Insets(3, 3, 3, 3));
		borrowP.add(showAccountListB);
		setCons(blankL[3], 2, 0, 1, 1, 1, 0, true, false, null);
		borrowP.add(blankL[3]);

		setCons(blankL[4], 0, 0, 1, 1, 1, 0, true, false, null);
		listP.add(blankL[4]);
		setCons(moneyRateL, 1, 0, 1, 1, 0, 0, false, false, null);
		listP.add(moneyRateL);

		// 북쪽 레이아웃 구성을 위해 레이아웃을 하나의 패널로 생각하고 패널에 컴포넌트들 추가
		setCons(typeP, 0, 0, 4, 1, 1, 0, true, false, null);
		northP.add(typeP);

		setCons(accountNumL, 0, 1, 1, 1, 0, 0, false, false, new Insets(3, 5, 3, 0));
		northP.add(accountNumL);
		setCons(accountNumTF, 1, 1, 1, 1, 1, 0, true, false, new Insets(3, 0, 3, -50));
		northP.add(accountNumTF);
		setCons(numP, 2, 1, 2, 1, 0, 0, true, false, new Insets(0, 50, 0, 0));
		northP.add(numP);

		setCons(accountOwnerL, 0, 2, 1, 1, 0, 0, false, false, new Insets(3, 5, 3, 0));
		northP.add(accountOwnerL);
		setCons(accountOwnerTF, 1, 2, 1, 1, 1, 0, true, false, new Insets(3, 0, 3, -50));
		northP.add(accountOwnerTF);
		setCons(ownerP, 2, 2, 2, 1, 0, 0, true, false, new Insets(0, 50, 0, 0));
		northP.add(ownerP);

		setCons(passwdL, 0, 3, 1, 1, 0, 0, false, false, new Insets(3, 5, 3, 0));
		northP.add(passwdL);
		setCons(passwdTF, 1, 3, 1, 1, 1, 0, true, false, new Insets(3, 0, 3, -50));
		northP.add(passwdTF);
		setCons(depositMoneyL, 2, 3, 1, 1, 0, 0, false, false, new Insets(3, 63, 3, 0));
		northP.add(depositMoneyL);
		setCons(depositMoneyTF, 3, 3, 1, 1, 1, 0, true, false, new Insets(3, 0, 3, 20));
		northP.add(depositMoneyTF);

		setCons(borrowMoneyL, 0, 4, 1, 1, 0, 0, false, false, new Insets(3, 5, 3, 0));
		northP.add(borrowMoneyL);
		setCons(borrowMoneyTF, 1, 4, 1, 1, 1, 0, true, false, new Insets(3, 0, 3, -50));
		northP.add(borrowMoneyTF);
		setCons(borrowP, 2, 4, 2, 1, 0, 0, true, false, new Insets(0, 50, 0, 0));
		northP.add(borrowP);

		setCons(accountListL, 0, 5, 1, 1, 0, 0, false, false, new Insets(3, 5, 3, 0));
		northP.add(accountListL);
		setCons(blankL[5], 1, 5, 2, 1, 0, 0, false, false, null);
		northP.add(blankL[5]);
		setCons(listP, 3, 5, 1, 1, 0, 0, true, false, null);
		northP.add(listP);

		// 전체 레이아웃 구성을 보더레이아웃을 통해 구현하고 그 안에 위에서 만든 패널들 삽입
		setLayout(new BorderLayout(15, 0));
		northB.add(northP, BorderLayout.CENTER);
		northB.add(blankL[6], BorderLayout.WEST);
		northB.add(blankL[7], BorderLayout.EAST);
		northB.add(blankL[8], BorderLayout.NORTH);
		add(northB, BorderLayout.NORTH);
		add(blankL[9], BorderLayout.WEST);
		add(blankL[10], BorderLayout.EAST);
		add(blankL[11], BorderLayout.SOUTH);
		add(accountListTA, BorderLayout.CENTER);
	}

	/**
	 * GridBagLayout에서 필요한 GridBagConstraints 의 값을 지정하는 기능
	 * 
	 * @param component  GridBagConstraints을 적용할 컴포넌트
	 * @param gridx      x 좌표값
	 * @param gridy      y 좌표값
	 * @param gridwidth  x 방향으로 차지할 크기
	 * @param gridheight y 방향으로 차지할 크기
	 * @param weightx    x 방향으로의 비중
	 * @param weighty    y 방향으로의 비중
	 * @param hori       수평방향으로 가득 채우는지 여부를 boolean 값으로 받음
	 * @param verti      수직방향으로 가득 채우는지 여부를 boolean 값으로 받음
	 * @param insets     내부 여백을 지정하는 값
	 */
	private void setCons(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty, boolean hori, boolean verti, Insets insets) {
		bagConstraints = new GridBagConstraints();
		bagConstraints.gridx = gridx;
		bagConstraints.gridy = gridy;
		bagConstraints.gridwidth = gridwidth;
		bagConstraints.gridheight = gridheight;
		bagConstraints.weightx = weightx;
		bagConstraints.weighty = weighty;
		if (hori && verti) {
			bagConstraints.fill = GridBagConstraints.BOTH;
		} else if (hori) {
			bagConstraints.fill = GridBagConstraints.HORIZONTAL;
		} else if (verti) {
			bagConstraints.fill = GridBagConstraints.VERTICAL;
		}
		if (insets != null) {
			bagConstraints.insets = insets;
		}
		bagLayout.setConstraints(component, bagConstraints);
	}

	/**
	 * 버튼을 클릭하는 등의 이벤트 발생시 처리해주는 기능을 모아 놓은 메소드
	 */
	public void eventRegist() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});

		accountTypeC.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				selectType();
			}
		});

		searchAccountNumB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getAccount();
			}
		});

		registNewAccountB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addAccount();
			}
		});

		deleteAccountB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeAccount();
			}
		});

		searchAccountOwnerB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAccount();
			}
		});

		showAccountListB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listAll();
			}
		});

		helpB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				printHelp();
			}
		});
	}

	/**
	 * 계좌의 타입을 지정하는 기능
	 */
	public void selectType() {
		if (accountTypeC.getSelectedItem().equals("마이너스계좌")) {
			borrowMoneyTF.setEnabled(true);
		} else {
			borrowMoneyTF.setEnabled(false);
		}
	}

	/**
	 * 계좌 번호를 통해 계좌 검색하는 기능
	 */
	public void getAccount() {
		String accountNum = accountNumTF.getText().trim();
		try {
			isEmptyAccountNum();
			Account account = manager.get(accountNum);
			String output;
			output = block1 + "\n" + specifications + "\n" + block2 + "\n";
			if (account instanceof MinusAccount) {
				output += String.format("%1$8s%2$s\n","마이너스",account.toString());
			} else {
				output += String.format("%1$8s\t%2$s\n","입출금",account.toString());
			}
			output += block1;
			accountListTA.setText(output);
		} catch (AccountException error) {
			accountListTA.setText(error.toString());
		}
	}

	/**
	 * 계좌번호를 통해 계좌 삭제하는 기능
	 */
	public void removeAccount() {
		String accountNum = accountNumTF.getText().trim();
		try {
			isEmptyAccountNum();
			if (!manager.remove(accountNum)) {
				accountListTA.setText("해당하는 계좌가 없어 삭제를 진행하지 못했습니다.");
			} else {
				accountListTA.setText(accountNum + " 계좌를 삭제 했습니다.");
			}
		} catch (AccountException error) {
			accountListTA.setText(error.toString());
		}

	}

	/**
	 * 계좌 이름을 통해 계좌 검색 기능
	 */
	public void searchAccount() {
		String accountOwner = accountOwnerTF.getText().trim();
		try {
			isEmptyAccountOwner();
			List<Account> lists = manager.search(accountOwner);
			printList(lists);
		} catch (AccountException error) {
			accountListTA.setText(error.toString());
		}
	}

	/**
	 * 계좌 정보를 받아 계좌를 추가하는 기능
	 */
	public void addAccount() {
		try {
			isEmptyAccountNum();
			isEmptyAccountOwner();
			isEmptyPasswd();
			isEmptyDepositMoney();
			isAccountType();
			String selectedAccountType = accountTypeC.getSelectedItem();
			if (accountNumTF.getText().length() >= 8) {
				if (selectedAccountType.equals("입출금계좌")) {
					int passwd = Integer.parseInt(passwdTF.getText().trim());
					long depositMoney = Long.parseLong(depositMoneyTF.getText().trim());
					Account account = new Account(accountNumTF.getText().trim(), accountOwnerTF.getText().trim(),
							passwd, depositMoney);
					manager.open(account);
					accountListTA.setText("계좌 등록이 완료되었습니다.");
				} else if (selectedAccountType.equals("마이너스계좌")) {
					isEmptyBorrowMoney();
					int passwd = Integer.parseInt(passwdTF.getText().trim());
					long depositMoney = Long.parseLong(depositMoneyTF.getText().trim());
					long borrowMoney = Long.parseLong(borrowMoneyTF.getText().trim());
					Account account = new MinusAccount(accountNumTF.getText().trim(), accountOwnerTF.getText().trim(),
							passwd, depositMoney, borrowMoney);
					manager.open(account);
					accountListTA.setText("계좌 등록이 완료되었습니다.");
				}
			} else {
				accountListTA.setText("계좌 번호 길이는 8글자 이상이어야 합니다.");
			}
		} catch (AccountException error) {
			accountListTA.setText(error.toString());
		} catch (NumberFormatException error) {
			accountListTA.setText("비밀번호나 입금금액, 대출금액은 정수형이어야 합니다.");
		}
	}

	/**
	 * 은행에 존재하는 모든 계좌 정보를 가져오는 기능
	 */
	public void listAll() {
		try {
			List<Account> lists = manager.listAll();
			printList(lists);
		} catch (AccountException error) {
			accountListTA.setText(error.toString());
		}
	}

	/**
	 * 종료 버튼 클릭시 종료하는 기능
	 */
	public void finish() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	/**
	 * 계좌 정보 리스트를 받아와 출력하는 기능
	 * 
	 * @param lists 계좌 정보가 담겨 있는 리스트
	 */
	public void printList(List<Account> lists) {
		String output;
		output = block1 + "\n" + specifications + "\n" + block2 + "\n";
		for (Account account : lists) {
			if (account instanceof MinusAccount) {
				output += String.format("%1$8s%2$s\n","마이너스",account.toString());
			} else {
				output += String.format("%1$8s\t%2$s\n","입출금",account.toString());
			}
		}
		output += block1;
		accountListTA.setText(output);
	}

	/**
	 * 화면에 도움말 출력하는 기능.
	 */
	public void printHelp() {
		accountListTA.setText(help);
	}

	/**
	 * 화면을 정중앙에 위치시키는 기능
	 */
	public void setCenter() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) ((dim.getWidth() - frame.getWidth()) / 2);
		int height = (int) ((dim.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(width, height);
	}

	/**
	 * 입력창이 비어있는지 확인하는 기능들
	 * 
	 * @throws AccountException 예외 발생시 처리할 클래스
	 */
	public void isEmptyAccountNum() throws AccountException {
		if (accountNumTF.getText().trim().isEmpty()) {
			throw new AccountException("계좌번호 입력 칸이 비어있습니다.", -20);
		}
	}

	public void isEmptyAccountOwner() throws AccountException {
		if (accountOwnerTF.getText().trim().isEmpty()) {
			throw new AccountException("예금주명 입력 칸이 비어있습니다.", -21);
		}
	}

	public void isEmptyPasswd() throws AccountException {
		if (passwdTF.getText().trim().isEmpty()) {
			throw new AccountException("비밀번호 입력 칸이 비어있습니다.", -22);
		}
	}

	public void isEmptyDepositMoney() throws AccountException {
		if (depositMoneyTF.getText().trim().isEmpty()) {
			throw new AccountException("입금금액 입력 칸이 비어있습니다.", -23);
		}
	}

	public void isEmptyBorrowMoney() throws AccountException {
		if (borrowMoneyTF.getText().trim().isEmpty()) {
			throw new AccountException("대출금액 입력 칸이 비어있습니다.", -24);
		}
	}

	public void isAccountType() throws AccountException {
		if (accountTypeC.getSelectedItem().trim().equals("전체")) {
			throw new AccountException("계좌종류가 전체로 되어 있을 땐 신규등록이 불가능합니다.", -25);
		}
	}
}
