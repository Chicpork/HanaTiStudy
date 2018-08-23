package apiStudy;
import java.util.Random;

public class MathExample {

	public static void main(String[] args) {
		System.out.println("원주율: " + Math.PI);
		System.out.println("자연로그의 밑수: " + Math.E);

		System.out.println("-20의 절대값: " + Math.abs(-20));
		System.out.println("-20.3의 절대값: " + Math.abs(-20.3));

		System.out.println("최대값: " + Math.max(50, 30));
		System.out.println("최소값: " + Math.min(1.5, 3.3));

		System.out.println("2의 3승 값: " + Math.pow(2, 3));

		System.out.println("69.6의 반올림: " + Math.round(69.6));
		System.out.println("69.3의 절상: " + Math.ceil(69.3));
		System.out.println("69.8의 절하: " + Math.floor(69.8));

		// 임의값 (0.0 <= random < 1.0)
		System.out.println(Math.random());
		// 0 ~ 2 범위의 임의의 정수값
		System.out.println((int) (Math.random() * 3));

		// 로또 번호(1~45범위의 6개)
		for (int i = 0; i < 6; i++) {
			System.out.print((int) (Math.random() * 45) + 1 + "\t");
		}

		Random random = new Random();
		// 자바 데이터타입별 난수 발생
		System.out.println(random.nextBoolean());
		System.out.println(random.nextInt()); // -21~~ ~ 21~~~
		System.out.println(random.nextInt(100)); // 0~99
		System.out.println(random.nextDouble());

		// 로또 번호 생성
		for (int i = 0; i < 6; i++) {
			System.out.print(random.nextInt(45) + 1 + "\t");
		}

	}

}
