package day9;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class GenericExample {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("박소연");
		list.add("김홍기");
		list.add("류세은");
//		list.add(Calendar.getInstance());
		
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String string = iter.next();
			System.out.println(string);
		}
		
		for (String string : list) {
			System.out.println(string);
		}
	}

}
