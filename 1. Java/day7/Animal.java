package day7;

public abstract class Animal {
	protected String name;
	protected int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void printInf() {
		System.out.println("이름: "+name+", 나이: "+age);
	}
	
	public abstract void sleep();
	public abstract void eat();

}
