package kr.or.kosta.boundary;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
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

public class MainFrame extends Panel {
	Label accountTypeL, accountNumL, accountOwnerL, passwdL, depositMoneyL, borrowMoneyL, accountListL, moneyRateL;
	Choice accountTypeC;
	TextField accountNumTF, accountOwnerTF, passwdTF, depositMoneyTF, borrowMoneyTF;
	TextArea accountListTA;
	Button searchAccountNumB, deleteAccountB, searchAccountOwnerB, registNewAccountB, showAccountListB;
	GridBagLayout bagLayout;
	GridBagConstraints bagConstraints;
	Label[] blankL;
	Frame frame;
	AccountManager manager;
	String block1, block2, specifications;

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
		accountListTA = new TextArea();
		accountListTA.setEditable(false);
		searchAccountNumB = new Button("조 회");
		deleteAccountB = new Button("삭 제");
		searchAccountOwnerB = new Button("검 색");
		registNewAccountB = new Button("신규등록");
		showAccountListB = new Button("전체조회");
		blankL = new Label[10];
		for (int i = 0; i < blankL.length; i++) {
			blankL[i] = new Label();
		}
		this.frame = frame;
		manager = new AccountManager();
		try {
			manager.add(new Account("1111-2222-3333", "정지원", 1234, 100000));
			manager.add(new Account("1111-2222-4444", "정지원", 1234, 100000));
			manager.add(new MinusAccount("1111-2222-5555", "정지원", 1234, 100000,1000000));
		} catch (AccountException e) {
			System.out.println("안녕");
		}
		block1 = "-----------------------------------------------------------------------------";
		block2 = "=============================================================================";
		specifications = "계좌종류\t계좌번호\t\t예금주명\t현재잔액\t대출금액";
	}

	public void setManager(AccountManager manager) {
		this.manager = manager;
	}

	public void setContents() {
		setLayout(new BorderLayout());
		Panel northP = new Panel(bagLayout);
		Panel typeP = new Panel(bagLayout);
		Panel numP = new Panel(bagLayout);
		Panel ownerP = new Panel(bagLayout);
		Panel passwdP = new Panel(bagLayout);
		Panel borrowP = new Panel(bagLayout);
		Panel listsP = new Panel(bagLayout);

		setCons(accountTypeL, 0, 0, 1, 1, 0, 0, false, false, null);
		typeP.add(accountTypeL);
		setCons(accountTypeC, 1, 0, 1, 1, 0, 0, false, false, null);
		typeP.add(accountTypeC);
		setCons(blankL[0], 2, 0, 1, 1, 1, 0, false, false, null);
		typeP.add(blankL[0]);

		setCons(accountNumL, 0, 0, 1, 1, 0, 0, false, false, null);
		numP.add(accountNumL);
		setCons(accountNumTF, 1, 0, 1, 1, 1, 0, true, false, null);
		numP.add(accountNumTF);
		setCons(searchAccountNumB, 2, 0, 1, 1, 0, 0, false, false, null);
		numP.add(searchAccountNumB);
		setCons(deleteAccountB, 3, 0, 1, 1, 0, 0, false, false, null);
		numP.add(deleteAccountB);
		setCons(blankL[1], 4, 0, 1, 1, 1, 0, false, false, null);
		numP.add(blankL[1]);

		setCons(accountOwnerL, 0, 0, 1, 1, 0, 0, false, false, null);
		ownerP.add(accountOwnerL);
		setCons(accountOwnerTF, 1, 0, 1, 1, 1, 0, true, false, null);
		ownerP.add(accountOwnerTF);
		setCons(searchAccountOwnerB, 2, 0, 1, 1, 0, 0, false, false, null);
		ownerP.add(searchAccountOwnerB);
		setCons(blankL[2], 3, 0, 1, 1, 1, 0, false, false, null);
		ownerP.add(blankL[2]);

		setCons(passwdL, 0, 0, 1, 1, 0, 0, false, false, null);
		passwdP.add(passwdL);
		setCons(passwdTF, 1, 0, 1, 1, 1, 0, true, false, null);
		passwdP.add(passwdTF);
		setCons(depositMoneyL, 2, 0, 1, 1, 0, 0, false, false, null);
		passwdP.add(depositMoneyL);
		setCons(depositMoneyTF, 3, 0, 1, 1, 1, 0, true, false, null);
		passwdP.add(depositMoneyTF);

		setCons(borrowMoneyL, 0, 0, 1, 1, 0, 0, false, false, null);
		borrowP.add(borrowMoneyL);
		setCons(borrowMoneyTF, 1, 0, 1, 1, 1, 0, true, false, null);
		borrowP.add(borrowMoneyTF);
		setCons(registNewAccountB, 2, 0, 1, 1, 0, 0, false, false, null);
		borrowP.add(registNewAccountB);
		setCons(showAccountListB, 3, 0, 1, 1, 0, 0, false, false, null);
		borrowP.add(showAccountListB);
		setCons(blankL[3], 4, 0, 1, 1, 1, 0, false, false, null);
		borrowP.add(blankL[3]);

		setCons(accountListL, 0, 0, 1, 1, 0, 0, false, false, null);
		listsP.add(accountListL);
		setCons(blankL[4], 1, 0, 1, 1, 1, 0, true, false, null);
		listsP.add(blankL[4]);
		setCons(moneyRateL, 2, 0, 1, 1, 0, 0, false, false, null);
		listsP.add(moneyRateL);

		// north 패널에 원하는 내용 추가
		setCons(typeP, 0, 0, 1, 1, 1, 1, true, false, null);
		northP.add(typeP);
		setCons(numP, 0, 1, 1, 1, 1, 0, true, false, null);
		northP.add(numP);
		setCons(ownerP, 0, 2, 1, 1, 1, 0, true, false, null);
		northP.add(ownerP);
		setCons(passwdP, 0, 3, 1, 1, 1, 0, true, false, null);
		northP.add(passwdP);
		setCons(borrowP, 0, 4, 1, 1, 1, 0, true, false, null);
		northP.add(borrowP);
		setCons(listsP, 0, 5, 1, 1, 1, 0, true, false, null);
		northP.add(listsP);
		add(northP, BorderLayout.NORTH);
		add(accountListTA, BorderLayout.CENTER);
	}

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

	public void exit() {
		frame.setVisible(false);
		frame.dispose();
		System.exit(0);
	}

	public void eventRegist() {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
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
	}

	public void selectType() {
		if (accountTypeC.getSelectedItem().equals("마이너스계좌")) {
			borrowMoneyTF.setEnabled(true);
		} else {
			borrowMoneyTF.setEnabled(false);
		}
	}

	public void getAccount() {
		String accountNum = accountNumTF.getText();
		try {
			isEmptyAccountNum();
			Account account = manager.get(accountNum);
			String output;
			output = block1+"\n"+specifications+"\n"+block2+"\n";
			if (account instanceof MinusAccount) {
				output += "마이너스\t"+account.toString();
			} else {
				output += "입출금\t"+account.toString();
			}
			output += "\n"+block1;
			accountListTA.setText(output);
		} catch (AccountException error) {
			accountListTA.setText(error.getMessage());
		}
	}

	public void removeAccount() {
		String accountNum = accountNumTF.getText();
		try {
			isEmptyAccountNum();
			if (!manager.remove(accountNum)) {
				accountListTA.setText("해당하는 계좌가 없어 삭제를 진행하지 못했습니다.");
			} else {
				accountListTA.setText(accountNum + " 계좌를 삭제 했습니다.");
			}
		} catch (AccountException error) {
			accountListTA.setText(error.getMessage());
		}

	}

	public void searchAccount() {
		String accountOwner = accountOwnerTF.getText();
		try {
			isEmptyAccountOwner();
			List<Account> lists = manager.search(accountOwner);
			String output;
			output = block1+"\n"+specifications+"\n"+block2+"\n";
			for (Account account : lists) {
				if (account instanceof MinusAccount) {
					output += "마이너스\t"+account.toString()+"\n";
				} else {
					output += "입출금\t"+account.toString()+"\n";
				}
			}
			output += block1;
			accountListTA.setText(output);
		} catch (AccountException error) {
			accountListTA.setText(error.getMessage());
		}
	}

	public void addAccount() {
		try {
			isEmptyAccountNum();
			isEmptyAccountOwner();
			isEmptyPasswd();
			isEmptyDepositMoney();
			isAccountType();
			String selectedAccountType = accountTypeC.getSelectedItem();
			if (selectedAccountType.equals("입출금계좌")) {
				int passwd = Integer.parseInt(passwdTF.getText());
				long depositMoney = Integer.parseInt(depositMoneyTF.getText());
				Account account = new Account(accountNumTF.getText(), accountOwnerTF.getText(), passwd, depositMoney);
				manager.add(account);
				accountListTA.setText("계좌 등록이 완료되었습니다.");
			} else if (selectedAccountType.equals("마이너스계좌")) {
				isEmptyBorrowMoney();
				int passwd = Integer.parseInt(passwdTF.getText());
				long depositMoney = Integer.parseInt(depositMoneyTF.getText());
				long borrowMoney = Integer.parseInt(borrowMoneyTF.getText());
				Account account = new MinusAccount(accountNumTF.getText(), accountOwnerTF.getText(), passwd,
						depositMoney, borrowMoney);
				manager.add(account);
				accountListTA.setText("계좌 등록이 완료되었습니다.");
			}
		} catch (AccountException error) {
			accountListTA.setText(error.getMessage());
		}
	}

	public void listAll() {
		try {
			List<Account> lists = manager.list();
			String output;
			output = block1+"\n"+specifications+"\n"+block2+"\n";
			for (Account account : lists) {
				if (account instanceof MinusAccount) {
					output += "마이너스\t"+account.toString()+"\n";
				} else {
					output += "입출금\t"+account.toString()+"\n";
				}
			}
			output += block1;
			accountListTA.setText(output);
		} catch (AccountException error) {
			accountListTA.setText(error.getMessage());
		}
	}

	// 입력창이 비어있는지 확인하는 기능들
	public void isEmptyAccountNum() throws AccountException {
		if (accountNumTF.getText().isEmpty()) {
			throw new AccountException("계좌번호 입력 칸이 비어있습니다.", -5);
		}
	}

	public void isEmptyAccountOwner() throws AccountException {
		if (accountOwnerTF.getText().isEmpty()) {
			throw new AccountException("예금주명 입력 칸이 비어있습니다.", -6);
		}
	}

	public void isEmptyPasswd() throws AccountException {
		if (passwdTF.getText().isEmpty()) {
			throw new AccountException("비밀번호 입력 칸이 비어있습니다.", -7);
		}
	}

	public void isEmptyDepositMoney() throws AccountException {
		if (depositMoneyTF.getText().isEmpty()) {
			throw new AccountException("입금금액 입력 칸이 비어있습니다.", -8);
		}
	}

	public void isEmptyBorrowMoney() throws AccountException {
		if (borrowMoneyTF.getText().isEmpty()) {
			throw new AccountException("대출금액 입력 칸이 비어있습니다.", -9);
		}
	}

	public void isAccountType() throws AccountException {
		if (accountTypeC.getSelectedItem().equals("전체")) {
			throw new AccountException("계좌종류가 전체로 되어 있을 땐 신규등록이 불가능합니다.", -10);
		}
	}
}
