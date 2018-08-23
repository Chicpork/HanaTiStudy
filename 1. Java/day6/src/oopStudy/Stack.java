package oopStudy;
/**
 * LIFO 구조의 스택 구현
 * 
 * @author 정지원
 *
 */
public class Stack {
	private int[] array;
	private int stackNum = 0; // Stack에 저장된 자료 개수를 저장하기 위한 변수
	private int stackMaxNum = 0;

	Stack() {
		this(10);
		stackMaxNum = 10;
	}

	/**
	 * Stack 생성자. array 크기를 지정하기 위해 number를 받아옴.
	 * 
	 * @param number array 배열 크기 지정.
	 */
	Stack(int number) {
		array = new int[number];
		stackMaxNum = number;
	}

	/**
	 * 사용자로부터 값을 받아와 stack에 추가하는 메소드
	 * 
	 * @param src 사용자 입력 값
	 */
	public void push(int src) {
		if (stackNum >= stackMaxNum) {
			System.out.println("Stack이 가득 찼습니다.");
		} else {
			array[stackNum++] = src;
		}
	}

	/**
	 * Stack에서 값을 하나 꺼내 준 뒤 꺼낸 값은 삭제하는 메소드
	 * 
	 * @return
	 */
	public int pop() {
		if (stackNum <= 0) {
			System.out.println("Stack에 자료가 없습니다. 0을 반환합니다.");
			return 0;
		} else {
			int temp = array[--stackNum];
			array[stackNum] = 0;
			return temp;
		}

	}

	/**
	 * Stack의 길이를 반환해주는 메소드
	 * 
	 * @return
	 */
	public int length() {
		return stackNum;
	}

	public static void main(String[] args) {
		Stack stack = new Stack(10);
		stack.push(5);
		stack.push(1);
		stack.push(9);
		for (int i = 0; i < 10; i++) {
			stack.push(1);
		}
		System.out.println(stack.length());
		// 테스트를 위한 출력
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		for (int i = 0; i < 10; i++) {
			System.out.println(stack.pop());
		}

	}
}
