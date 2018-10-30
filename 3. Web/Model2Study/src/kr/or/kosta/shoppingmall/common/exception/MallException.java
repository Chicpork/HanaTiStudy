package kr.or.kosta.shoppingmall.common.exception;

/**
 * 사용자 정의 RuntimeException
 * 
 * @author 김기정
 */
public class MallException extends RuntimeException {
	
	private static final String DEFAULT_CODE = "KMS-0001";
	private String code;

	public MallException() {
		this(DEFAULT_CODE, "Database Server Error!");				
	}
		
	public MallException(String message) {
		super(message);
	}

	public MallException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MallException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public MallException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
