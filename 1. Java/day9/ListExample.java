package day9;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * List는 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다.
 * Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을 가진다.
 * ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다.
 * 
 * @author 정지원
 *
 */
public class ListExample {

	public static void main(String[] args) {
		List list = null;
		list = new ArrayList();
		
		list.add("황의조");
		list.add("손흥민");
		list.add("바나나");
		list.add(100); // Object obj = 100; Autoboxing을 이용해 대입이 가능.
		list.add(new Integer(100));
		list.add(Calendar.getInstance());
		list.add("황의조");
		
		System.out.println("담겨진 갯수: " + list.size());
		System.out.println("비어 있는지 여부: " + list.isEmpty());

		List boddari = new ArrayList();
		boddari.add("AAA");
		boddari.add("BBB");
		boddari.add("CCC");
		
		list.addAll(boddari);
		
		System.out.println("담겨진 갯수: " + list.size());
		
		boolean result = list.remove("바나나");
		System.out.println("삭제결과: "+result);

		System.out.println(list.contains("황의조"));
		System.out.println(list.contains(Calendar.getInstance()));
		
		// 요즘엔 잘 안쓴다. (확장 for문 이용)
		Object[] lists = list.toArray();
		for (Object object : lists) {
			if(object instanceof String) {
				System.out.println(((String) object).length());
			}
			System.out.println(object);
		}
		
		// 요즘엔 잘 안쓴다. (확장 for문 이용)
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			Object object = iter.next();
			System.out.println(object);
		}
		
		for (Object object : list) {
			System.out.println(object);
		}
		
		// List에 추가된 규약 메소드들...
		list.add(0, "정지원");
		System.out.println(list.get(0));
		System.out.println(list.remove(0)); // 지우고 그 값을 리턴해줌.
		System.out.println(list.set(0, "황희찬"));
		
		System.out.println(list.size());
		List l = list.subList(0, 3);
		for (Object object : l) {
			System.out.println(object);
		}
	}

}
