package day10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import day9.AccountException;

/**
 * 은행 계좌 관리 어플리케이션
 * 
 * @author 정지원
 *
 */
public class AMS {

	public static void main(String[] args) {

		// 매니저의 계좌 추가 기능 실행
		AccountManager3 manager = new AccountManager3();
		try {
			manager.add(new Account("1111-2222-3333", "정지원", 123456, 100000));
			manager.add(new Account("2222-2222-3333", "박지성", 1111, 200000));
			manager.add(new Account("2222-3333-3333", "김기정", 1234, 300000));
			manager.add(new Account("2222-3333-4444", "정지원", 1234, 300000));
			manager.add(new Account("2222-3333-5555", "정지원", 1234, 300000));
			manager.add(new Account("2222-3333-5555", "정지원", 1234, 300000));
		} catch (AccountException e) {
			System.out.println(e.getMessage());
		}

		// 매니저의 리스트 기능 실행
		System.out.println("================ 전체 계좌 목록 ================");
		List<Account> list = manager.list();
		if (!list.isEmpty()) {
			for (Object account2 : list) {
				System.out.println("입출금계좌\t" + account2.toString());
			}
		} else {
			System.out.println("등록된 계좌가 없습니다.");
		}
		System.out.println();

		// 매니저의 계좌 가져오기 기능 실행
		System.out.println("================ 계좌 찾기 기능 ================");
		Account getAccount = manager.get("2222-3333-3333");
		if (getAccount == null) {
			System.out.println("해당하는 계좌가 존재하지 않습니다.");
		} else {
			System.out.println(getAccount.toString());
		}
		System.out.println();

		// 매니저의 서치 기능 실행
		System.out.println("================ 계좌 검색 기능 ================");
		List<Account> jiwon = manager.search("정지원");
		if (!jiwon.isEmpty()) {
			for (Object account2 : jiwon) {
				System.out.println(account2.toString());
			}
		} else {
			System.out.println("해당 이름을 가진 계좌는 존재 하지 않습니다.");
		}
		System.out.println();

		// 매니저의 제거 기능 실행
		System.out.println("================ 계좌 삭제 기능 ================");
		if (manager.remove("2222-2222-3333")) {
			System.out.println("성공적으로 제거 되었습니다.");
		} else {
			System.out.println("계좌 번호가 일치하지 않거나 존재하지 않는 계좌입니다.");
		}
		System.out.println();

	}

}
