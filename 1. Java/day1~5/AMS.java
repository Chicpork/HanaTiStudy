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
		manager.add(new Account("2222-3333-4444", "정지원", 1234, 100000));
		manager.add(new Account("2222-2222-4444", "정지원", 1234, 200000));

		// 매니저의 리스트 기능 실행
		Account[] list = manager.list();
		for (Account account2 : list) {
			System.out.println(account2.toString());
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
