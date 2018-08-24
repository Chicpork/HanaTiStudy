package day7;

public class Dog extends Animal {
	private boolean loyalty;

	public Dog() {
		this("안녕", 5, true);
	}

	public Dog(String name, int age, boolean loyalty) {
		this.name = name;
		this.age = age;
		this.loyalty = loyalty;
	}

	@Override
	public void sleep() {
		System.out.println("강아지가 주무십니다..");
	}

	@Override
	public void eat() {
		System.out.println("강아지가 먹습니다..");
	}

	public static void main(String[] args) {
		Animal animal = new Dog("루니", 3, false);
		animal.eat();
		animal.sleep();
	}
}
