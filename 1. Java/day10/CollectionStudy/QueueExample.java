package day10;

import java.util.LinkedList;

public class QueueExample {

	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		queue.offer("AAAAA");
		queue.offer("BBBBB");
		queue.offer("CCCCC");
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		
	}

}
