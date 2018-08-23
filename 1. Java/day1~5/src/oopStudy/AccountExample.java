package oopStudy;
/**
 * 프로그램 실행을 위한 애플리케이션 클래스 정의
 */
public class AccountExample{
    public static void main(String[] args){
        System.out.println("은행 계좌 어플리케이션 시작됨...");

        // 클래스로부터 객체(인트턴스) 생성
        //Account account; // 참조(레퍼런스) 변수
        //account = new Account();

        //// 인스턴스의 속성과 기능 사용
        //account.accountNum = "1111-2222-3333";
        //account.accountOwner = "정지원";
        //account.passwd = 1235;
        //account.restMoney = 100000;
        Account account = new Account("111-222-333","정지원",1234,100000);

        int passwd = 1234;
        boolean result = account.checkPasswd(passwd);
        if(result){
            long money = account.deposit(100000);
            System.out.println("입금 후 잔액: "+money);
            money = account.withdraw(10000);
            System.out.println("출금 후 잔액: "+money);
        }else{
            System.out.println("비밀번호 오류 입니다.");
        }
        
        //System.out.println(account.accountNum);

        Account account2 = new Account();
        // 인스턴스 변수의 경우는 JVM에 의해 자동 초기화됨.
        account2.setAccountNum("123-456-789");
        System.out.println(account2.getAccountNum());
        System.out.println(account2.getAccountOwner());
        System.out.println(account2.getPasswd());
        System.out.println(account2.getRestMoney());


        System.out.println(Account.bankName);

        System.out.println("은행 계좌 어플리케이션 종료됨...");
    }
}