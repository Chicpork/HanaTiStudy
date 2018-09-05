package kr.or.kosta.entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 현재 폴더에 Account.dat라는 파일에 계좌 관련 정보를 저장하는 클래스
 * 
 * @author 정지원
 */
public class AccountDao {
	private static final String FILE_PATH = "./Account.dat";

	private static final int RECORD_COUNT_LENGTH = 4; // 계좌 개수 저장 크기 (4바이트)

	private static final int ACCOUNT_TYPE_LENGTH = 1; // 계좌 타입 저장 크기 (1바이트)
	private static final int ACCOUNT_NUM_LENGTH = 30; // 계좌 최대 15자 (30바이트)
	private static final int ACCOUNT_OWNER_LENGTH = 10; // 이름 최대 5글자 (10바이트)
	private static final int PASSWD_LENGTH = 4; // 비밀번호 (4바이트)
	private static final int RESTMONEY_LENGTH = 8; // 현재 잔액 (8바이트)
	private static final int BORROWMONEY_LENGTH = 8; // 대출금 (8바이트)

	// 전체 정보 저장 레코드 사이즈 : 61바이트
	public static final int RECORD_LENGTH = ACCOUNT_TYPE_LENGTH + ACCOUNT_NUM_LENGTH + ACCOUNT_OWNER_LENGTH
			+ PASSWD_LENGTH + RESTMONEY_LENGTH + BORROWMONEY_LENGTH;

	private RandomAccessFile file; // 파일에 접근하기 위한 클래스

	private int recordCount = 0; // 저장되어 있는 계좌 개수

	// 생성자
	public AccountDao() throws IOException {
		file = new RandomAccessFile(FILE_PATH, "rw");

		if (file.length() != 0) {
			recordCount = file.readInt();
		}
	}

	/**
	 * 저장된 계좌 중 계좌 번호가 동일한 계좌를 반환하는 기능
	 * 
	 * @param accountNumSRC 사용자가 입력한 계좌 번호
	 * @return
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 * @throws IOException      파일 입출력 예외 처리 클래스
	 */
	public Account getAccount(String accountNumSRC) throws AccountException, IOException {
		if (recordCount == 0) {
			throw new AccountException("은행에 만들어진 계좌가 존재하지 않습니다.", -11);
		}

		for (int j = 0; j < recordCount; j++) {
			Account account = readAccount(j);
			if (account.getAccountNum().equals(accountNumSRC)) {
				return account;
			}
		}

		throw new AccountException("해당하는 계좌가 존재하지 않습니다.", -12);
	}

	/**
	 * 저장된 데이터에서 계좌명이 일치하는 계좌들을 반환하는 기능
	 * 
	 * @param accountOwnerSRC 사용자가 입력한 계좌명
	 * @return
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 * @throws IOException      파일 입출력 예외 처리 클래스
	 */
	public List<Account> searchAccount(String accountOwnerSRC) throws AccountException, IOException {
		if (recordCount == 0) {
			throw new AccountException("은행에 만들어진 계좌가 존재하지 않습니다.", -11);
		}

		List<Account> lists = new ArrayList<Account>();

		for (int i = 0; i < recordCount; i++) {
			Account account = readAccount(i);
			if (account.getAccountOwner().equals(accountOwnerSRC)) {
				lists.add(account);
			}
		}

		if (lists.isEmpty()) {
			throw new AccountException("해당하는 이름으로 만들어진 계좌가 존재하지 않습니다.", -13);
		}
		Collections.sort(lists, new NumberCompare());
		return lists;
	}

	/**
	 * 새로운 계좌를 개설하는 기능.
	 * 계좌 번호가 중복되면 개설하지 않는다.
	 * 
	 * @param accountSRC 새로운 계좌 정보
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 */
	public void openAccount(Account accountSRC) throws AccountException, IOException {
		String accountNum = accountSRC.getAccountNum();

		if (accountSRC.getAccountOwner().trim().length() > (ACCOUNT_OWNER_LENGTH / 2)) {
			throw new AccountException("계좌명은 5글자 이하여야 합니다.", -17);
		}
		if (accountNum.trim().length() > (ACCOUNT_NUM_LENGTH / 2)) {
			throw new AccountException("계좌 번호는 8글자 이상 15글자 이하여야 합니다.", -18);
		}

		if (recordCount != 0) {
			for (int i = 0; i < recordCount; i++) {
				Account account = readAccount(i);
				if (account.getAccountNum().equals(accountNum)) {
					file.seek(0);
					throw new AccountException("이미 등록되어 있는 계좌 번호입니다.", -10);
				}
			}
		}

		writeAccount(accountSRC, recordCount);

		file.seek(0);
		file.writeInt(++recordCount);
	}

	/**
	 * 존재하는 계좌를 제거하는 기능
	 * 
	 * @param accountNumSRC 삭제할 계좌 번호
	 * @return
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 */
	public boolean removeAccount(String accountNumSRC) throws IOException, AccountException {
		if (recordCount == 0) {
			throw new AccountException("은행에 만들어진 계좌가 존재하지 않습니다.", -11);
		}

		for (int i = 0; i < recordCount; i++) {
			Account account = readAccount(i);
			if (account.getAccountNum().equals(accountNumSRC)) {
				writeAccount(readAccount(recordCount - 1), i);
				file.seek(0);
				file.writeInt(--recordCount);
				return true;
			}
		}

		return false;
	}

	/**
	 * 전체 계좌 정보를 List로 반환하는 기능
	 * 
	 * @return
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 */
	public List<Account> listAccount() throws AccountException, IOException {
		if (recordCount == 0) {
			throw new AccountException("은행에 만들어진 계좌가 존재하지 않습니다.", -11);
		}

		List<Account> lists = new ArrayList<Account>();

		for (int j = 0; j < recordCount; j++) {
			lists.add(readAccount(j));
		}

		if (lists.isEmpty()) {
			throw new AccountException("은행에 만들어진 계좌가 존재하지 않습니다.", -11);
		}

		Collections.sort(lists, new NumberCompare());
		return lists;
	}

	/**
	 * 계좌 정보를 데이터로부터 읽어오는 기능
	 * 
	 * @param accountIndex 몇 번째 계좌 정보를 가져올지 정해주는 인덱스
	 * @return
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 * @throws AccountException 계좌 관련 예외 처리 클래스
	 */
	public Account readAccount(int accountIndex) throws IOException, AccountException {
		file.seek((accountIndex * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		Account account = null;
		String accountNum = "";
		String accountOwner = "";
		int passwd = 0;
		long restMoney = 0;
		long borrowMoney = 0;
		byte accountType = file.readByte();
		if (accountType == 1) {
			for (int i = 0; i < ACCOUNT_NUM_LENGTH / 2; i++) {
				accountNum += file.readChar();
			}
			accountNum = accountNum.trim();
			for (int i = 0; i < ACCOUNT_OWNER_LENGTH / 2; i++) {
				accountOwner += file.readChar();
			}
			accountOwner = accountOwner.trim();
			passwd = file.readInt();
			restMoney = file.readLong();
			borrowMoney = file.readLong();
			account = new Account(accountNum, accountOwner, passwd, restMoney);
		} else if (accountType == 2) {
			for (int i = 0; i < ACCOUNT_NUM_LENGTH / 2; i++) {
				accountNum += file.readChar();
			}
			accountNum = accountNum.trim();
			for (int i = 0; i < ACCOUNT_OWNER_LENGTH / 2; i++) {
				accountOwner += file.readChar();
			}
			accountOwner = accountOwner.trim();
			passwd = file.readInt();
			restMoney = file.readLong();
			borrowMoney = file.readLong();
			account = new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney);
		}
		return account;
	}

	/**
	 * 입력받은 계좌 정보를 데이터베이스에 입력하는 기능
	 * 원하는 데이터베이스 위치에 저장한다.
	 * 
	 * @param accountSRC 새롭게 추가할 계좌 정보
	 * @param accountIndex 데이터베이스에서 추가할 위치 인덱스
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 */
	public void writeAccount(Account accountSRC, int accountIndex) throws IOException {
		file.seek((accountIndex * RECORD_LENGTH) + RECORD_COUNT_LENGTH);

		String accountNum = accountSRC.getAccountNum();
		String accountOwner = accountSRC.getAccountOwner();
		int passwd = accountSRC.getPasswd();
		long restMoney;
		long borrowMoney;
		if (accountSRC instanceof MinusAccount) {
			restMoney = ((MinusAccount) accountSRC).getRestMoney();
			borrowMoney = ((MinusAccount) accountSRC).getBorrowMoney();
			file.writeByte(2);
		} else {
			restMoney = accountSRC.getRestMoney();
			borrowMoney = 0L;
			file.writeByte(1);
		}

		for (int i = 0; i < ACCOUNT_NUM_LENGTH / 2; i++) {
			file.writeChar((i < accountNum.length()) ? accountNum.charAt(i) : ' ');
		}
		for (int i = 0; i < ACCOUNT_OWNER_LENGTH / 2; i++) {
			file.writeChar((i < accountOwner.length()) ? accountOwner.charAt(i) : ' ');
		}
		file.writeInt(passwd);
		file.writeLong(restMoney);
		file.writeLong(borrowMoney);
		file.seek(0);
	}

	/**
	 * 사용했던 RandomAccessFile 클래스를 종료하기 위한 기능
	 * 
	 * @throws IOException 파일 입출력 예외 처리 클래스
	 */
	public void closeAccountDao() throws IOException {
		if (file != null)
			file.close();
	}

}
