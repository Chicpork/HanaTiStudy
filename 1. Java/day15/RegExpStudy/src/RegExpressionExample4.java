/**
 * 정규표현식을 활용한 유효성 체크
 * @author 김기정
 */
public class RegExpressionExample4 {

	public static void main(String[] args) {
		String[] emails = { "test@abc.com", "aaaa", "abcd@", "abc@mydomain", "bangry@xxx.co.kr" };
		
		// 이메일 유효성 검증을 위한 패턴(정규식)
		// bangry313@gmail.com
		//String pattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
		//String pattern = "^[a-zA-Z0-9]{6,}@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
		String pattern = "^[\\w]+@[\\w]+(\\.[\\w]+)+$";
		
		for (String email : emails) {
			System.out.println(email + " : " + email.matches(pattern));
		}
	}
}
