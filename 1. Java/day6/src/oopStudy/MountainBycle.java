package oopStudy;
public class MountainBycle extends Bycle {

	// 추가 속성
	String frame;
	boolean suspension;

	public MountainBycle() {
		// super()가 없으면 컴파일 할 때 자동으로 추가되어 실행된다.
//		super();
		this(null, false);
	}

	public MountainBycle(String frame, boolean suspension) {
		// super()가 없으면 컴파일 할 때 자동으로 추가되어 실행된다.
//		super();
		this.frame = frame;
		this.suspension = suspension;
	}

	public MountainBycle(int id, String brand, String frame, boolean suspension) {
//		this.id = id;
//		this.brand = brand;
		super(id, brand);
		this.frame = frame;
		this.suspension = suspension;
	}
	
	public void running() {
		System.out.println("자전거가 빠르게 달립니다..");
	}
}
