package day9;

import java.util.Vector;
import java.util.Calendar;
import java.util.Enumeration;
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
public class ListExample2 {

	public static void main(String[] args) {
		Vector list = null;
//		list = new Vector();
		list = new Vector(10, 5); // Vector(초기 저장공간, 증가치);
		
		list.addElement("황의조");
		list.addElement("바나나");
		list.addElement("손흥민");
		list.addElement(100); // Object obj = 100; Autoboxing을 이용해 대입이 가능.
		list.addElement(new Integer(100));
		list.addElement(Calendar.getInstance());
		list.addElement("황의조");
		
		System.out.println("담겨진 갯수: " + list.size());
		System.out.println("비어 있는지 여부: " + list.isEmpty());

		System.out.println(list.elementAt(0));
		System.out.println("삭제 여부 : "+list.removeElement("바나나"));
		
		Enumeration e = list.elements();
		while(e.hasMoreElements()) {
			Object object = e.nextElement();
			System.out.println(object);
		}
	}

}
