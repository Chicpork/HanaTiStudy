package day10;

import java.util.Enumeration;
import java.util.Hashtable;

public class MapExample2 {

	public static void main(String[] args) {
	
		Hashtable<String, Account> set = new Hashtable<>();
		
		Account account1 = new Account("1111","김기정",1111,1000);
		Account account2 = new Account("2222","정기정",1111,1000);
		
		set.put(account1.getAccountNum(), account1);
		set.put(account2.getAccountNum(), account2);
		
		System.out.println(set.get("1111"));
		
		Enumeration<String> e = set.keys();
		while(e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println(key);
		}
		
		Enumeration<Account> e2 = set.elements();
		
		for (String string : set.keySet()) {
			System.out.println(string);
		}
	}

}
