package de.nmadev.shapes;

public class Line {
	private Point point1;
	private Point point2;
	private Display display;
	
	public Line() {
		this.display = new Display();
	}
	
	public Line(Point point1, Point point2) {
		this();
		this.point1 = point1;
		this.point2 = point2;
	}
	
	
	
	public void callDisplay() {
		display.getLength(this);
	}
	
	
	
	public Point getPoint1() {
		return point1;
	}
	public void setPoint1(Point point1) {
		this.point1 = point1;
	}
	
	public Point getPoint2() {
		return point2;
	}
	
	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	@Override
	public String toString() {
		return "Line{" + point1 + "/" + point2 + "}";
	}
}