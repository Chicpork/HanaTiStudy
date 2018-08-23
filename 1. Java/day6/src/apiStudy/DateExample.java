package apiStudy;
import java.util.Date;

public class DateExample {

	public static void main(String[] args) {
		Date today = new Date();
		System.out.println(today.getYear());
		System.out.println(today.toString());
		System.out.println(today.toLocaleString());
		// 밀리초 단위로 1970년 1월 1일 0시 0분 이후로 흐른 시간을 보여줌.
		System.out.println(today.getTime()); 
	}

}
