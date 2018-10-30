package kr.or.kosta.blog.common;

/**
 * 정규 표현식을 이용해 유효성 검증을 해주는 클래스
 * @author 정지원
 *
 */
public class Validator {
	
	/**
	 * 아이디 유효성 검증
	 * 영문과숫자조합으로 8~10자 아이디
	 * ex) jiwon313
	 * @param id
	 * @return
	 */
	public static boolean isValidId(String id){
		return id.trim().matches("^\\w{3,10}$");
	}

	/**
	 * 이메일 유효성 검증
	 * ex) jiwon@gmail.com
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		return email.trim().matches("^\\w{1,}@[a-zA-Z]{1,}\\.[a-zA-Z]{1,}$");
	}
	
}
