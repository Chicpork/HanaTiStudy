package oopStudy;
/**
 * 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의
 * 은행계좌 객체
 */
public class Account{
	
	//
	static{
        System.out.println("초기화 생성 블럭입니다..1");
        System.out.println("초기화 생성 블럭입니다..2");
    }
	
	// 
	public final static String bankName = "하나은행";
	
    // 인스턴스 변수 선언
    private String accountNum;
    private String accountOwner;
    private int passwd;
    private long restMoney;

    // 생성자
    public Account(){
        this(null,null,0,0);
    }
    public Account(String accountNum,String accountOwner){
        this.accountNum = accountNum;
        this.accountOwner = accountOwner;
    }
    public Account(String accountNum,String accountOwner,int passwd,long restMoney){
        this.accountNum = accountNum;
        this.accountOwner = accountOwner;
        this.passwd = passwd;
        this.restMoney = restMoney;
    }

    // setter/getter 메소드
    public void setAccountNum(String accountNum){
        this.accountNum = accountNum;
    }
    public String getAccountNum(){
        return accountNum;
    }
    public void setAccountOwner(String accountOwner){
        this.accountOwner = accountOwner;
    }
    public String getAccountOwner(){
        return accountOwner;
    }
    public void setPasswd(int passwd){
        this.passwd=passwd;
    }
    public int getPasswd(){
        return passwd;
    }
    public void setRestMoney(long restMoney){
        this.restMoney=restMoney;
    }

    // 인스턴스 메소드
    public long deposit(long money){
        restMoney += money;
        return restMoney;
    }
    public long withdraw(long money){
        restMoney -= money;
        return restMoney;
    }
    public long getRestMoney(){
        return restMoney;
    }
    public boolean checkPasswd(int pw){
        return passwd == pw;
    }
    public String toString() {
    	return getAccountNum()+"\t"+getAccountOwner()+"\t"+getRestMoney();
    }
    public String isAccountNum(String src) {
    	if(accountNum.equals(src)) {
    		return "검색된 계좌 정보는 다음과 같습니다.\n"+getAccountOwner()+"\t"+getRestMoney()+"\n";
    	}else {
    		return "";
    	}
    }
}