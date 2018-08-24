package day7;

/**
 * 은행 계좌 관리 어플리케이션
 * 
 * @author 정지원
 *
 */
public class AMS {

	public static void main(String[] args) {

		// 매니저의 계좌 추가 기능 실행
		AccountManager manager = new AccountManager();
//		AccountManager manager = new AccountManager(100);
		Account account = new Account("1111-2222-3333", "정지원", 123456, 100000);
		manager.add(account);
		manager.add(new Account("2222-2222-3333", "박지성", 1111, 200000));
		manager.add(new Account("2222-3333-3333", "김기정", 1234, 300000));

		// Up Casting
		manager.add(new MinusAccount("9999-8888-7777", "김대출", 1111, 1000, 1000000));
		manager.add(new MinusAccount("9999-8888-5555", "홍길동", 1111, 1000, 1000000));

		manager.add(new Account("2222-5555-3333", "박찬호", 1111, 300000));

		// 매니저의 리스트 기능 실행
		Account[] list = manager.list();
		for (Account account2 : list) {
			if (account2 instanceof MinusAccount) {
				System.out.println("마이너스계좌\t"+account2.toString());
			}else {
				System.out.println("입출금계좌\t"+account2.toString());
			}
		}
		System.out.println();

		// 매니저의 서치 기능 실행
		Account[] jiwon = manager.search("정지원");
		for (Account account2 : jiwon) {
			System.out.println(account2.toString());
		}
		System.out.println();

		// 매니저의 제거 기능 실행
		if (manager.remove("2222-2222-3333")) {
			System.out.println("성공적으로 제거 되었습니다.");
		} else {
			System.out.println("계좌 번호가 일치하지 않거나 존재하지 않는 계좌입니다.");
		}
		System.out.println();

		// 매니저의 계좌 가져오기 기능 실행
		Account getAccount = manager.get("2222-3333-4444");
		if (getAccount == null) {
			System.out.println("해당하는 계좌가 존재하지 않습니다.");
		} else {
			System.out.println(getAccount.toString());
		}

	}

}
