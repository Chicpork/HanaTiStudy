import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex API를 이용한 정규표현식 사용
 * @author 김기정
 */
public class RegExpressionExample0 {

	public static void main(String[] args) {
		String message = "Hello Java World...";
		String regExp = "java";
//		Pattern pattern = Pattern.compile(regExp);// 대소문자 구분
		Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(message);
		System.out.println(matcher.matches()); // 패턴과 일치 여부 반환
		System.out.println(matcher.find());    // 패턴과 일치하는 문자열 존재 여부 반환
		
		System.out.println("-------------------------");
		
		String str = "as";
		pattern = Pattern.compile(".s");
		matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
		
		// Pattern 클래스의 static 메소드 활용
		System.out.println(Pattern.matches(".s", str));
		System.out.println(Pattern.matches("..s", str));
	}

}
