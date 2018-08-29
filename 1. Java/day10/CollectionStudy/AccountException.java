package day10;

public class AccountException extends Exception {
//	String message; // 이미 Exception에서 존재해서 상속 받아 와있음.
	private int errorCode;

	
	public AccountException() {
		this("계좌 처리중 예기치 않은 에러 발생하였습니다.", -9);
	}

	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}  

	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
	}
	
	

}
