import java.util.function.BiPredicate;

public class LambdaExmaple {

	public static void main(String[] args) {

		MyFunc add = (a, b) -> a + b;
		MyFunc sub = (a, b) -> a - b;
		MyFunc mul = (a, b) -> a * b;
		MyFunc div = (a, b) -> a / b;
		System.out.println(add.Calculator(6, 4));
		System.out.println(sub.Calculator(6, 4));
		System.out.println(mul.Calculator(6, 4));
		System.out.println(div.Calculator(6, 4));
		
		BiPredicate<Integer,Integer> f = (a,b) -> a > b;
		System.out.println(f.test(10, 20));
		
		boolean result = myCheckFunction((a, b) -> a%3 != 0 && b%5 != 0, 10, 9);
        System.out.println(result);
		
	}
	public static boolean myCheckFunction(BiPredicate<Integer, Integer> bi, Integer a, Integer b) {
        return bi.test(a, b);
    }
}
