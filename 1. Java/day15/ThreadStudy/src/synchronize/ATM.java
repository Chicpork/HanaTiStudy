package synchronize;

/**
 * 은행 본점 서버와의 네트워크 통신을 통해 계좌 관련 처리 담당
 * @author 김기정
 *
 */
public class ATM {
	/** 테스트를 위한 공유 계좌 */
	Account account;
	
	public ATM(){
		account = new Account("1111-2222", "방그리가족통장", 1111, 100000);
	}
	
	/** 입금 */
	public void depositMoney(String accountNum, int passwd, long money, String user) throws Exception{
		System.out.println("***** " + user + " 입금 시작 *****");
		if(!checkAccountNum(accountNum)){
			throw new Exception("존재하지 않는 계좌입니다.");
		}
		if(!checkPasswd(passwd)){
			throw new Exception("비밀번호 오류입니다.");
		}
		
		// 본점 서버와의 통신에 약간의 시간이 소요된다 가정..
		Thread.sleep(300);
		account.deposit(money);
		Thread.sleep(300);
		System.out.println(user+" 입금 후 잔액 : " + account.getRestMoney());
		System.out.println("***** " + user + " 입금 완료 *****");
	}
	
	/** 출금 */
	public  /*synchronized*/ void withdrawMoney(String accountNum, int passwd, long money, String user) throws Exception{
		
//		synchronized (Account.class) {
			System.out.println("***** " + user + " 출금 시작 *****");
			
			if(!checkAccountNum(accountNum)){
				throw new Exception("존재하지 않는 계좌입니다.");
			}
			if(!checkPasswd(passwd)){
				throw new Exception("비밀번호 오류입니다.");
			}
			if(money > account.getRestMoney()){
				throw new Exception("잔액이 부족합니다.");
			}
			
			// 본점 서버와의 통신에 약간의 시간이 소요된다 가정..
			Thread.sleep(300);
			account.withdraw(money);
			
			Thread.sleep(300);
			System.out.println(user+" 출금 후 잔액 : " + account.getRestMoney());
			System.out.println("***** " + user + " 출금 완료 *****");
//		}
	}

	private boolean checkAccountNum(String accountNum) throws Exception{
		// 본점 서버와의 통신에 약간의 시간이 소요된다 가정..
		Thread.sleep(300);
		return accountNum.equals(account.getAccountNum());
	}
	
	private boolean checkPasswd(int passwd) throws Exception{
		// 본점 서버와의 통신에 약간의 시간이 소요된다 가정..
		Thread.sleep(300);
		return passwd == account.getPasswd();
	}

}
