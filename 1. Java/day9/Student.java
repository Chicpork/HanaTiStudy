package day9;
/**
 * 제너릭클래스
 * @author 정지원
 *
 */
public class Student<T> {
	private String name;
	private T ssn;
	
	
	public Student() {
	}

	public Student(String name, T ssn) {
		this.name = name;
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getSsn() {
		return ssn;
	}

	public void setSsn(T ssn) {
		this.ssn = ssn;
	}
	
	public static void main(String[] args) {
		Student<Integer> student = new Student<Integer>("김기정",123456);
		student.setSsn(112233);
		
	}

}
