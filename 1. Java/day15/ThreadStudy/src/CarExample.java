public class CarExample {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("메인 스레드에 의해 프로그램 시작됨...");
		
		Car car = new Car("방그리");
		Car car2 = new Car("김홍기");
		Car car3 = new Car("홍길동");
		car.start();
		car2.start();
		car3.start();
		
		car.join();
		car2.join();
		car3.join();
		
		System.out.println("메인 스레드에 의해 프로그램 종료됨...");
	}

}
