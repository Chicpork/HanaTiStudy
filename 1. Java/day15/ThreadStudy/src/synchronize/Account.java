package synchronize;

/**
 * 일상생활의 은행계좌를 표현하기 위한 클래스(추상화)
 * 객체에 대한 데이터타입 정의
 */
public class Account /*extends Object */{
	
	//클래스(static) 변수
//	public static String bankName = "KOSTA Bank";
	public static final String BANK_NAME = "KOSTA Bank";
	
	
	// 인스턴스 변수
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;
	
	
	// 생성자(Constructor) Overloading(중복정의)
	public Account(){
//		accountNum = null;
//		accountOwner = null;
//		passwd = 0;
//		restMoney = 0L;
		this(null, null);
	}
	
	public Account(String accountNum, String accountOwner){
//		this.accountNum = accountNum;
//		this.accountOwner = accountOwner;
//		this.passwd = 1111;
//		this.restMoney = 0;
		this(accountNum, accountOwner, 1111);
	}
	
	public Account(String accountNum, String accountOwner, int passwd){
//		this.accountNum = accountNum;
//		this.accountOwner = accountOwner;
//		this.passwd = passwd;
//		this.restMoney = 0;
		this(accountNum, accountOwner, passwd, 0);
		
	}
	
	public Account(String accountNum, String accountOwner, int passwd, long restMoney){
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}
	
	// setter/getter methods
	public String getAccountNum(){
		return accountNum;
	}
	
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	
	public String getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	// 인스턴스 메소드
	public long deposit(long money){
		return restMoney += money;
	}

	public long withdraw(long money){
		return restMoney -= money;
	}

	public long getRestMoney(){
		return restMoney;	
	}

	public boolean checkPasswd(int pw){
		return passwd == pw;
	}

	// 객체가 가지고 있는 속성들 출력
	public void print(){
		System.out.println(accountNum + "\t" + accountOwner + "\t" + restMoney + "\t" + "****");
	}
	
	// 클래스 메소드
	public static double calculateRate() {
		return 0.08;
	}
	
	@Override
	public String toString() {
//		return  "입출금계좌\t"+ accountNum + "\t" + accountOwner + "\t*****\t" + restMoney;
		return  String.format("%-9s", "입출금계좌") + String.format("%-17s", accountNum) + String.format("%-6s", accountOwner) + String.format("%-7s", "*****")  + String.format("%,14d원", restMoney);
	}
	
	@Override
	public boolean equals(Object obj) {
		return toString().equals(obj.toString());
	}

}
