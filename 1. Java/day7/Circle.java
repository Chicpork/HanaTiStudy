package day7;

public class Circle extends Shape {
	private double radian;

	public Circle() {
		this(0.0, 0.0, 0.0);
	}

	public Circle(double x, double y, double radian) {
		this.x = x;
		this.y = y;
		this.radian = radian;
	}

	// getter-setter
	public double getRadian() {
		return radian;
	}

	public void setRadian(double radian) {
		this.radian = radian;
	}

	@Override
	public void draw() {
		System.out.println(x + ", " + y + ", " + getRadian() + "의 원입니다..");
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return Math.PI * Math.pow(getRadian(), 2);
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 2 * Math.PI * getRadian();
	}

	@Override
	public String toString() {
		return "Circle [radian=" + radian + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
	
}
