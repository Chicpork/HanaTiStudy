package synchronize;

/**
 * 스레드 동기화 예제
 * @author 김기정
 *
 */
public class ATMExample {
	
	public static void main(String[] args) {
		
		// 스레드에 의해 공유되는 ATM 생성
		ATM atm = new ATM();
		
		Family mom = new Family("엄마");
		mom.setAtm(atm);
		Family son = new Family("아들");
		son.setAtm(atm);
		
		mom.start();
		son.start();
	}

}
