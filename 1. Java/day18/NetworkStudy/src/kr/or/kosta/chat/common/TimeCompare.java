package kr.or.kosta.chat.common;

import java.util.Comparator;

import kr.or.kosta.chat.server.Client;

/**
 * 계좌 정보를 출력할 때 순서를 정해주기 위한 클래스<br>
 * 계좌 정보를 내림차순으로 정렬한다.
 * 
 * @author 정지원
 */
public class TimeCompare implements Comparator<Client> {

	@Override
	public int compare(Client o1, Client o2) {
		if(o2.getTime() > o1.getTime()) {
			return -1;
		}else if(o2.getTime() == o1.getTime()) {
			return 0;
		}else {
			return 1;
		}
	}

}
