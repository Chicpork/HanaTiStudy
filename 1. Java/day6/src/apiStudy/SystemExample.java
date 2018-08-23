package apiStudy;
public class SystemExample {

	public static void main(String[] args) {
		System.out.println("프로그램 Start");
		
		
//		System.exit(0); // JVM을 종료하는 명령어
		System.gc(); // 가비지 콜렉터를 실행하는 명령어 그런데 바로 실행하지는 않음.
		
		long start = System.currentTimeMillis();

		
		for(int i=0;i<10000000;i++) {
			
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		System.out.println(System.getenv("path"));
		
		System.out.println("프로그램 Finish");
	}

}
