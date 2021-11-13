package de.nmadev.shapes;

public class Circle {
	
	private Point middlePoint;
	private double radius;
	private Display display;
	
	public Circle() {
		this.display = new Display();
	}
	
	public Circle(Point middlePoint, double radius) {
		this();
		this.middlePoint = middlePoint;
		this.radius = radius;
	}
	
	
	
	public void callDisplay() {
		display.getArea(this);
	}
	
	
	
	public Point getMiddlePoint() {
		return middlePoint;
	}

	public void setMiddlePoint(Point middlePoint) {
		this.middlePoint = middlePoint;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Circle{" + middlePoint + "/[R:" + radius + "]}";
	}
}
