package day10;

import java.util.Comparator;

public class MoneyCompare implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		return (int)(o1.getRestMoney() - o2.getRestMoney());
	}

}
