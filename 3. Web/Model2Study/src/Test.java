
public class Test {

	public static void main(String[] args) {
		String ex = "()(((()())(())()))(())";
		String ex2 = "(((()(()()))(())()))(()())";
		System.out.println(count(ex));
		System.out.println(count(ex2));
	}

	public static int count(String ex) {
		int addNum = 0;
		int total = 0;
		for (int i = 0; i < ex.length() - 1; i++) {
			if (ex.charAt(i) == '(' && ex.charAt(i + 1) == '(') {
				addNum++;
			} else if (ex.charAt(i) == '(' && ex.charAt(i + 1) == ')') {
				total += addNum--;
			} else if (ex.charAt(i) == ')' && ex.charAt(i + 1) == ')') {
				total++;
				addNum--;
			} else if (ex.charAt(i) == ')' && ex.charAt(i + 1) == '(') {
				addNum++;
			}
		}
		return total;
	}
}
