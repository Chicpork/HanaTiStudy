package day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsExample {

	public static void main(String[] args) {

		List<Account> list = new ArrayList<Account>();

		list.add(new Account("3333", "정지원", 1111, 2000));
		list.add(new Account("1111", "홍지원", 1111, 1000));
		list.add(new Account("2222", "김지원", 1111, 5000));

		Collections.sort(list, new NumberCompare());
		for (Account account : list) {
			System.out.println(account);
		}
		Collections.sort(list, new MoneyCompare());
		for (Account account : list) {
			System.out.println(account);
		}
	}

}
