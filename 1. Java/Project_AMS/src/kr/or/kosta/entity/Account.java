package kr.or.kosta.entity;

/**
 * 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의<br>
 * 은행계좌를 추상화한 객체.
 * 
 * @author 정지원
 */
public class Account {

	// 은행 이름
	public final static String bankName = "하나은행";

	// 인스턴스 변수 선언
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;

	// 생성자
	public Account() throws AccountException {
		throw new AccountException("계좌 정보가 입력되지 않았습니다.",-1);
	}

	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	// setter/getter 메소드
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	public long getRestMoney() {
		return restMoney;
	}

	/**
	 * 계좌에 입금하는 기능
	 * 
	 * @param money 입금할 금액
	 * @return
	 * @throws AccountException 예외 발생시 전달할 예외클래스
	 */
	public long deposit(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -2);
		}
		restMoney += money;
		return restMoney;
	}

	/**
	 * 계좌에서 돈 출금하는 기능
	 * 
	 * @param money 출금할 금액
	 * @return
	 * @throws AccountException 예외 발생시 전달할 예외클래스
	 */
	public long withdraw(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("출금하고자 하는 금액은 음수일 수 없습니다.", -3);
		}
		if (money > restMoney) {
			throw new AccountException("잔액이 부족합니다.", -4);
		}
		restMoney -= money;
		return restMoney;
	}

	/**
	 * 계좌 비밀번호와 입력한 비밀번호의 일치를 확인하는 기능
	 * 
	 * @param pw 사용자가 입력한 비밀번호
	 * @return
	 */
	public boolean checkPasswd(int pw) {
		return passwd == pw;
	}

	// Object 클래스의 toString() 메소드 오버라이딩
	@Override
	public String toString() {
		return String.format("%1$21s%2$13s%3$,20d",getAccountNum(),getAccountOwner(),getRestMoney());
	}

	// Object 클래스의 equals() 메소드 오버라이딩
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Account) {
			return toString().equals(obj.toString()); // 위임형 비교
		}
		return false;
	}
}