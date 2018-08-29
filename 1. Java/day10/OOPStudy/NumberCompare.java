package day10;

import java.util.Comparator;

public class NumberCompare implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getAccountNum().compareTo(o2.getAccountNum());
	}

}
