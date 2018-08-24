package day7;

public class Rectangle extends Shape {
	private double width;
	private double height;

	public Rectangle() {
		this(0.0, 0.0, 0.0, 0.0);
	}

	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void draw() {
		System.out.println(x + ", " + y + ", " + getWidth() + ", " + getHeight() + "의 사각형입니다.");
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 2 * (getWidth() + getHeight());
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return getWidth() * getHeight();
	}

	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	

}
