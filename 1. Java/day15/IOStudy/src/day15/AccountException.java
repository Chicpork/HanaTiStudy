package day15;

/**
 * 계좌를 처리할 때 발생하는 예외처리 클래스
 * 
 * @author 정지원
 */
// Serial 경고 메시지를 없애기 위해 추가한 어노테이션
@SuppressWarnings("serial")
public class AccountException extends Exception {
	// 에러 발생 코드
	private int errorCode;
	
	// 생성자
	public AccountException() {
		this("계좌 처리중 예기치 않은 에러 발생하였습니다.", 0);
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
		return " [ErrorCode : " + errorCode + "] *** " + getMessage()+" ***";
	}
	
}
