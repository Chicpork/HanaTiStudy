package kr.or.kosta.entity;

import java.util.Comparator;

/**
 * 계좌 정보를 출력할 때 순서를 정해주기 위한 클래스<br>
 * 계좌 정보를 내림차순으로 정렬한다.
 * 
 * @author 정지원
 */
public class NumberCompare implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
