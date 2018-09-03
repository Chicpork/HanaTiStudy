package kr.or.kosta.entity;

/**
 * Account를 확장한 마이너스계좌
 * 
 * @author 정지원
 */
public class MinusAccount extends Account {
	// 대출금
	private long borrowMoney;

	// 생성자
	public MinusAccount() throws AccountException {
		throw new AccountException("계좌 정보가 입력되지 않았습니다.",-5);
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	// getter-setter
	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) throws AccountException{
		if(borrowMoney < 0) {
			throw new AccountException("빌릴 돈에 음수를 입력할 수 없습니다.",-6); 
		}
		this.borrowMoney = borrowMoney;
	}

	// Account 클래스의 getRestMoney를 오버라이딩해 사용
	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();
	}

	// Account 클래스의 toString() 메소드 오버라이딩
	@Override
	public String toString() {
		return String.format("%1$s%2$,20d", super.toString(),getBorrowMoney());
	}
}