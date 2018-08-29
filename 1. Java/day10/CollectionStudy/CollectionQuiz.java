package day10;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectionQuiz {

	public static void main(String[] args) {
		Set<Account> set = new HashSet<Account>();

		set.add(new Account("1111", "정지원", 1111, 1000));
		set.add(new Account("2222", "김지원", 1111, 1000));
		set.add(new Account("1111", "정지원", 1111, 1000));
		System.out.println(set.size());

		Map<String, String> env = System.getenv();
		Set<String> keySet = env.keySet();
		for (String name : keySet) {
			String value = System.getenv(name);
			System.out.println(name + "=" + value);
		}
	}

}
