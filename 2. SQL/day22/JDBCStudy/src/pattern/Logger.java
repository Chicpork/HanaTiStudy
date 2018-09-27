package pattern;

import java.util.Calendar;

/**
 * 싱글톤 패턴 적용 클래스
 * @author 정지원
 *
 */
public class Logger {

	private static Logger logger = new Logger();

	private Logger() {}

	public static Logger getInstance() {
		return logger;
	}
	
	public void log(String message) {
		Calendar calendar = Calendar.getInstance();
		String today = String.format("%1$tF %1$tT", calendar);
		System.out.println("["+today+"] "+message);
	}
}
