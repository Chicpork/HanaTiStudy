package day8;

public class Bar {
	
	@Deprecated
	public void some() {
		System.out.println("썸타요..");
	}
	
	@Override
	public String toString() {
//		System.out.println("어노테이션 테스트");
		return "";
	}

	public static void main(String[] args) {
		Bar bar = new Bar();
		bar.some();
	}
}
