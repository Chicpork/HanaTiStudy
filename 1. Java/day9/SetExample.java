package day9;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Set은 데이터의 중복 저장 없이 데이터를 관리하기 위한 규약(인터페이스)이다.<br>
 * 순서와 관련 없이 데이터를 관리한다.<br>
 * HashSet이 Set 인터페이스를 구현한 대표적인 클래스이다.
 * 
 * @author 정지원
 *
 */
public class SetExample {

	public static void main(String[] args) {
		Set set = null;
		set = new HashSet(10);
		
		set.add("황의조");
		set.add("손흥민");
		set.add("바나나");
		set.add(100); // Object obj = 100; Autoboxing을 이용해 대입이 가능.
		set.add(new Integer(100));
		set.add(Calendar.getInstance());
		set.add("황의조");
		
		System.out.println("담겨진 갯수: " + set.size());
		System.out.println("비어 있는지 여부: " + set.isEmpty());

		Set boddari = new HashSet();
		boddari.add("AAA");
		boddari.add("BBB");
		boddari.add("CCC");
		
		set.addAll(boddari);
		
		System.out.println("담겨진 갯수: " + set.size());
		
		boolean result = set.remove("바나나");
		System.out.println("삭제결과: "+result);

		System.out.println(set.contains("황의조"));
		System.out.println(set.contains(Calendar.getInstance()));
		
		// 요즘엔 잘 안쓴다. (확장 for문 이용)
		Object[] list = set.toArray();
		for (Object object : list) {
			if(object instanceof String) {
				System.out.println(((String) object).length());
			}
			System.out.println(object);
		}
		
		// 요즘엔 잘 안쓴다. (확장 for문 이용)
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Object object = iter.next();
			System.out.println(object);
		}
		
		for (Object object : set) {
			System.out.println(object);
		}
	}

}
