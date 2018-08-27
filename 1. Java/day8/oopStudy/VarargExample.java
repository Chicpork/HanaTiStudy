package day8;

public class VarargExample {

	public static int sum1(int[] args) {
		int result = 0;
		for (int i : args) {
			result += i;
		}
		return result;
	}
	public static int sum2(int... args) {
		int result = 0;
		for (int i : args) {
			result += i;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		int[] value = {10,20,30};
		System.out.println(sum1(value));
		System.out.println(sum2(10,20,30));
	}
}
