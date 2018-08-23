package oopStudy;

import java.util.Scanner;

/**
 * 레퍼런스타입 배열 선언, 생성, 초기화
 * 
 * @author 정지원
 *
 */
public class ArrayExample4 {
	public static void main(String[] args) {
//		Account account = new Account("1111-2222-3333", "정지원", 123456, 100000);
		Account[] accounts = new Account[100];
		int index = 0;
		
		// 은행 계좌 개설
		accounts[index] = new Account("1111-2222-3333", "정지원", 123456, 100000);
		index++;

		accounts[index] = new Account("2222-2222-3333", "김기정", 123456, 200000);
		index++;

		accounts[index] = new Account("3333-2222-3333", "박지성", 123456, 200000);
		index++;
		
		// 전체 계좌 목록 출력
		for (int i=0;i<index;i++) {
			System.out.println(accounts[i].toString());
		}
		
		// 계좌 번호로 계좌 조회
		String num = null;
		int idx = -1;
		Scanner sc = new Scanner(System.in);
		System.out.print("검색 계좌번호 : ");
		num = sc.nextLine();
		for (int i = 0; i < index; i++) {
			System.out.print(accounts[i].isAccountNum(num));	
		}
		for (int i = 0; i < index; i++) {
			if(accounts[i].getAccountNum().equals(num)) {
				idx = i;
				break;
			}
		}
		if(idx==-1) {
			System.out.println("검색된 계좌가 없습니다.");
		}else {
			System.out.println(accounts[idx].toString());
		}
	}

}
