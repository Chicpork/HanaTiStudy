package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import day9.AccountException;

/**
 * 배열을 이용한 은행 계좌 관리
 * 
 * @author 정지원
 *
 */
public class AccountManager3 {
	private Hashtable<String, Account> accounts;

	/**
	 * 계좌를 저장할 공간을 정해주지 않았을 시 10개를 기본으로 함.
	 */
	AccountManager3() {
		this(10);
	}

	/**
	 * 계좌를 저장할 수 있는 저장 공간 초기화
	 * 
	 * @param capacity 계좌를 저장할 수 있는 크기
	 */
	AccountManager3(int capacity) {
		accounts = new Hashtable<String, Account>();
	}

	/**
	 * 계좌를 추가하는 메소드
	 * 
	 * @param account 입력하고자 하는 계좌 정보
	 */
	public void add(Account account) throws AccountException {
		if(accounts.containsKey(account.getAccountNum())) {
			throw new AccountException("이미 등록되어 있는 계좌 번호입니다.", -4);
		}
		accounts.put(account.getAccountNum(), account);
	}

	/**
	 * 실제 들어 있는 계좌 전체를 반환해주는 메소드
	 * 
	 * @return
	 */
	public List<Account> list() {
		Enumeration<Account> e = accounts.elements();
		List<Account> lists = new ArrayList<Account>(accounts.size());
		while (e.hasMoreElements()) {
			lists.add(e.nextElement());
		}
		Collections.sort(lists, new NumberCompare());
		return lists;
	}

	/**
	 * 검색하고자 하는 계좌 번호를 받아 일치하는 것을 반환
	 * 
	 * @param accountNum 사용자가 입력하는 계좌 번호
	 * @return
	 */
	public Account get(String accountNum) {
		if (accounts.containsKey(accountNum)) {
			return accounts.get(accountNum);
		}
		return null;
	}

	/**
	 * 계좌명을 받아 해당 계좌명으로 만들어진 모든 계좌를 보여주는 메소드
	 * 
	 * @param accountOwner 사용자가 입력한 계좌명
	 * @return
	 */
	public List<Account> search(String accountOwner) {
		Enumeration<Account> e = accounts.elements();
		List<Account> lists = new ArrayList<Account>();
		Account account;
		while (e.hasMoreElements()) {
			account = e.nextElement();
			if (account.getAccountOwner().equals(accountOwner)) {
				lists.add(account);
			}
		}
		Collections.sort(lists, new NumberCompare());
		return lists;
	}

	/**
	 * 계좌 번호를 받아 해당 계좌가 존재하면 삭제하는 메소드
	 * 
	 * @param accountNum 삭제할 계좌 번호
	 * @return
	 */
	public boolean remove(String accountNum) {
		return accounts.remove(accountNum) != null;
	}
}