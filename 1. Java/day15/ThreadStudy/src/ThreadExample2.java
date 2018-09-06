
public class ThreadExample2 {

	static class MyThread extends Thread {
		
		public MyThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			System.out.println(getName()+"스레드 시작");
			for (int i = 0; i < 23200; i++) {
				
				}
			System.out.println(getName()+"스레드 끝");
		}
	}

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName());
		System.out.println("프로그램 시작");

		MyThread t1 = new MyThread("정지원");
		t1.setPriority(Thread.MAX_PRIORITY);
		MyThread t2 = new MyThread("방그리");
		t2.setPriority(Thread.MIN_PRIORITY);
		
		t1.start();
		t2.start();
		
		System.out.println("프로그램 끝");
	}
}
