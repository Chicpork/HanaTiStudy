package kr.or.kosta.blog.common;

public class Validator {
	
	/**
	 * 아이디 유효성 검증
	 * 영문과숫자조합으로 8~10자 아이디
	 * ex) bangry313
	 * @param id
	 * @return
	 */
	public static boolean isValidId(String id){
		return id.trim().matches("^\\w{3,10}$");
	}

	/**
	 * 이메일 유효성 검증
	 * ex) bangry313@gmail.com
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		return email.trim().matches("^\\w{1,}@[a-zA-Z]{1,}\\.[a-zA-Z]{1,}$");
	}
	
	public static void main(String[] args) {
		System.out.println(isValidEmail("ak@nav."));
	}
}
