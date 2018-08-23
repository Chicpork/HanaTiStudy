package oopStudy;
public class Bycle /* extends Object */ {
	int id;
	String brand;

	public Bycle() {
		// super()가 없으면 컴파일 할 때 자동으로 추가되어 실행된다.
//		super();
		this(0, null);
	}

	public Bycle(int id, String brand) {
		// super()가 없으면 컴파일 할 때 자동으로 추가되어 실행된다.
//		super(); 
		this.id = id;
		this.brand = brand;
	}

	public void running() {
		System.out.println("자전거가 달립니다..");
	}
}