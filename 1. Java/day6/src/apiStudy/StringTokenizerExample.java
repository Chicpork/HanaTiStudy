package apiStudy;
import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		String date = "2018-08-23";
		StringTokenizer str = new StringTokenizer(date, "-");
		System.out.println(str.countTokens());
		
		while(str.hasMoreTokens()) {
			String token = str.nextToken();
			System.out.println(token);
		}
	}

}
