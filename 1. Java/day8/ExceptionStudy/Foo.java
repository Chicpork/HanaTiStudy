package day8;

public class Foo {

	public void someMethod() {
//		String message = null;
//		System.out.println(message.length()); // 여기서 exception(예외) 발생. new Null... 이 실행됨.

//		System.out.println(10/0);

		int[] array = { 1, 2, 3 };
		System.out.println(array[3]);
	}

	public void someMethod2() {
		try {
			String message = null;
//		System.out.println(message.length());
//		System.out.println(10/0);
			int[] array = { 1, 2, 3 };
			System.out.println(array[3]);
		} catch (NullPointerException e) {
			System.out.println("인스턴스 생성 안 됐음..");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ArithmeticException e) {
//			System.out.println("수학 계산 잘 못했어유..");
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
//			return;
			System.exit(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("예외 발생 여부와 상관없이 항상 실행 코드");
		}

	}

	public void someMethod3() throws NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException {
		String message = null;
		System.out.println(message.length());
		System.out.println(10 / 0);
		int[] array = { 1, 2, 3 };
		System.out.println(array[3]);
	}

	public static void main(String[] args) {
		System.out.println("JVM에 의해 프로그램 시작됨...");
		Foo foo = new Foo();
//		foo.someMethod();
//		foo.someMethod2();
		try {
			foo.someMethod3(); // 만약 someMethod3()에서 throws에서 선언된 예외가 발생하면 그 즉시 메소드를 종료하고 메소드 실행부분으로 돌아와 그 메소드
								// 안에서 실행하도록 한다.
		} catch (Exception e) {
			System.out.println("메인에서 모든 예외 처리 완료.");
		}
		System.out.println("프로그램 종료됨...");
	}
}
