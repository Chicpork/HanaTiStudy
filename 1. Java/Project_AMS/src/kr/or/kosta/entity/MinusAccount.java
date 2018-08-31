package kr.or.kosta.entity;

/**
 * Account를 확장한 마이너스계좌
 * 
 * @author 정지원
 *
 */
public class MinusAccount extends Account {
	private long borrowMoney;

	public MinusAccount() {
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) throws AccountException{
		if(borrowMoney < 0) {
			throw new AccountException("빌릴 돈에 음수를 입력할 수 없습니다.",-2); 
		}
		this.borrowMoney = borrowMoney;
	}

	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();
	}

	@Override
	public String toString() {
		return super.toString() + "\t" + getBorrowMoney();
	}
	
	public void print() {
		
	}

}