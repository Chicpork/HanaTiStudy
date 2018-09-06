import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 정규표현식을 이용한 문자열 치환
 * @author 김기정
 */
public class RegExpressionExample2 {
	
	public static void main(String[] args) {
		String message = "A broken hand works, but not a broken heart.";
		
		// "broken"을 "drunken"으로 치환
		String regex = "broken";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		
		String replacedMessage = matcher.replaceAll("drunken");
		
		System.out.println("원본 문자열: " + message);
		System.out.println("치환된 문자열: " + replacedMessage);
		
		// String의 정규표현식 지원하는 replaceAll() 메소드 사용하여 쉽게 치환 가능
		//System.out.println(replacedMessage.replaceAll("drunken", "java"));
	}
}
