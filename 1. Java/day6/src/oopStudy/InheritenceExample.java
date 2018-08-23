package oopStudy;
public class InheritenceExample {

	public static void main(String[] args) {
		Bycle bycle = new Bycle(10, "삼천리");
		System.out.println(bycle.brand);
		bycle.running();
		
		MountainBycle mountainBycle = new MountainBycle(10,"삼천포","카본",true);
		// 재사용
		System.out.println(mountainBycle.id);
		System.out.println(mountainBycle.brand);
		// 확장
		System.out.println(mountainBycle.frame);
		System.out.println(mountainBycle.suspension);
		mountainBycle.running();
				
	}

}