package oopStudy;
/**
 * Account를 확장한 마이너스계좌
 * 
 * @author 정지원
 *
 */
public class MinusAccount extends Account {
	private long borrowMoney;

	public MinusAccount() {
		this(null, null, 0, 0, 0);
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();
	}

	public static void main(String[] args) {
		MinusAccount minusAccount = new MinusAccount();
		System.out.println(minusAccount.getBorrowMoney());

		MinusAccount minusAccount2 = new MinusAccount("1111-2222-3333", "정지원", 1234, 0, 1000000);
		minusAccount2.deposit(10000);
		System.out.println("현재잔액: " + minusAccount2.getRestMoney());
	}
}