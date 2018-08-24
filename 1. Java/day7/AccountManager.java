package day7;
/**
 * 배열을 이용한 은행 계좌 관리
 * 
 * @author 정지원
 *
 */
public class AccountManager {
	private Account[] accounts; // 계좌를 저장할 변수
	private int count; // 만들어진 계좌 개수
	private int accountsMaxNum; // 계좌를 저장 할 수 있는 최대 공간 크기

	{
		count = 0;
	}

	/**
	 * 계좌를 저장할 공간을 정해주지 않았을 시 10개를 기본으로 함.
	 */
	AccountManager() {
		this(10);
		accountsMaxNum = 10;
	}

	/**
	 * 계좌를 저장할 수 있는 저장 공간 초기화
	 * 
	 * @param accountNum 계좌를 저장할 수 있는 크기
	 */
	AccountManager(int accountNum) {
		accounts = new Account[accountNum];
		accountsMaxNum = accountNum;
	}

	// setter/getter 구현
	public int getCount() {
		return count;
	}

	public int getAccountsMaxNum() {
		return accountsMaxNum;
	}

	/**
	 * 계좌를 추가하는 메소드
	 * 
	 * @param account 입력하고자 하는 계좌 정보
	 */
	public void add(Account account) {
		if (count >= accountsMaxNum) {
			System.out.println("죄송합니다. 더이상 계좌를 추가할 수 없습니다.");
		} else {
			accounts[count] = account;
			count++;
		}
	}

	/**
	 * 실제 들어 있는 계좌 전체를 반환해주는 메소드
	 * 
	 * @return
	 */
	public Account[] list() {
		Account[] list = new Account[count];
		for (int i = 0; i < count; i++) {
			list[i] = accounts[i];
		}
		return list;
	}

	/**
	 * 검색하고자 하는 계좌 번호를 받아 일치하는 것을 반환
	 * 
	 * @param accountNum 사용자가 입력하는 계좌 번호
	 * @return
	 */
	public Account get(String accountNum) {
		for (int i = 0; i < count; i++) {
			if (accounts[i].getAccountNum().equals(accountNum)) {
				return accounts[i];
			}
		}
		return null;
	}

	/**
	 * 계좌명을 받아 해당 계좌명으로 만들어진 모든 계좌를 보여주는 메소드
	 * 
	 * @param accountOwner 사용자가 입력한 계좌명
	 * @return
	 */
	public Account[] search(String accountOwner) {
		int counts = 0;
		int[] idx = new int[count];
		for (int i = 0; i < count; i++) {
			if (accounts[i].getAccountOwner().equals(accountOwner)) {
				idx[counts] = i;
				counts++;
			}
		}
		Account[] temp = new Account[counts];
		for (int i = 0; i < counts; i++) {
			temp[i] = accounts[idx[i]];
		}
		return temp;
	}

	/**
	 * 계좌 번호를 받아 해당 계좌가 존재하면 삭제하는 메소드
	 * 
	 * @param accountNum 삭제할 계좌 번호
	 * @return
	 */
	public boolean remove(String accountNum) {
		for (int i = 0; i < count; i++) {
			if (accounts[i].getAccountNum().equals(accountNum)) {
				accounts[i] = accounts[count - 1];
				count--;
				return true;
			}
		}
		return false;
	}
}
