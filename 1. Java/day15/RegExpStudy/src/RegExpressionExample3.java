/**
 * String 클래스의 정규표현식 지원 메소드들 활용
 * @author 김기정
 */
public class RegExpressionExample3 {
	
	public static void main(String[] args) {
		String msg1 = "hello world";
		System.out.println(msg1.matches("hello"));          // false
		System.out.println(msg1.matches("hello ([a-z]*)")); // true
	
		System.out.println("------------------------");
		String msg2 = "hello a9한";
		System.out.println(msg2.matches("hello ([a-z]*)"));
		System.out.println(msg2.matches("hello ([a-z0-9]*)"));
		System.out.println(msg2.matches("hello ([a-z0-9가-힣]*)"));
		System.out.println(msg2.matches("hello\\p{Space}([a-z0-9가-힣]*)"));
		
		System.out.println("------------------------");
		String msg3 = "gooooooogle";
		System.out.println(msg3.matches("google"));
		System.out.println(msg3.matches("go*gle"));
		
		System.out.println("------------------------");
		
		// 패턴(정규식)을 구분자로 사용하여 토큰 분리
		String message = "토큰1--토큰2**토큰3..토큰4";
		String regex = "(\\-\\-|\\*\\*|\\.\\.)";
		String[] tokens = message.split(regex);
		for (String token : tokens) {
			System.out.println(token);
		}
		
		// 문자열 치환
		String txt = "gooooooogle goooogle gooogle goooogle";
		System.out.println(txt.replaceAll("go*gle", "구글"));
	}
}
