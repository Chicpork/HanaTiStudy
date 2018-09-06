import java.util.Random;

public class Car extends Thread {
	
	public Car(String name) {
//		super(name);
		setName(name);
	}
	
	public void run() {
		Random random = new Random();
		System.out.println(getName()+" 자동차 Start");
		for (int i = 0; i <= 10; i++) {
			System.out.println(getName()+" 자동차 "+i+"m 전진");
			try {
				Thread.sleep(random.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName()+" 자동차 Finish");
	}
}
