package day10;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
	public static void main(String[] args) {
		Set<String> set = new TreeSet<String>();
		set.add("김기정");
		set.add("홍기정");
		set.add("이기정");
		set.add("정기정");
		set.add("최기정");
		set.add("aaaaa");
		set.add("bbbbb");
		
		for (String string : set) {
			System.out.println(string);
		}
	}

}
