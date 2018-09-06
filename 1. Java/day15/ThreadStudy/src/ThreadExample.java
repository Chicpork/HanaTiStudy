
public class ThreadExample {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		
		for (int i = 0; i < 100; i++) {
			System.out.println("메인스레드에서 i값 출력 : "+i);
			if(i==50) {
				new Thread() {
					@Override
					public void run() {
						for (int j = 0; j < 50; j++) {
							System.out.println("사용자 스레드 j: "+j);
						}
					}
				}.start();
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("헐...");
					}
				}).start();
			}
		}
		
		
		System.out.println("프로그램 끝");
	}
}
