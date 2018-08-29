package day10;

import java.util.Stack;

public class StackExample {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		stack.push("1. 안녕");
		stack.push("2. 잘가");
		stack.push("3. 다시봐");
		
		System.out.println(stack.size());
		System.out.println(stack.pop());
		System.out.println(stack.size());
		System.out.println(stack.peek());
		System.out.println(stack.size());
		
	}

}
