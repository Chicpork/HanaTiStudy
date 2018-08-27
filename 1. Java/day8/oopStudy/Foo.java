package day8;

public class Foo {
	public void move(Directions directions) {
		switch(directions) {
		case EAST:
			System.out.println("동쪽 이동");
			break;
		case WEST:
			System.out.println("서쪽 이동");
			break;
		case NORTH:
			System.out.println("북쪽 이동");
			break;
		case SOUTH:
			System.out.println("남쪽 이동");
			break;
		}
	}
	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.move(Directions.NORTH);
		
		Directions[] list = Directions.values();
		for (Directions directions : list) {
			System.out.println(directions);
		}
		
		String name = "NORTH";
		Directions dir = Directions.valueOf(name);
		System.out.println(dir);
	}
}
