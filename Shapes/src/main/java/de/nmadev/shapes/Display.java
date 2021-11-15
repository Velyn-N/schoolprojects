package de.nmadev.shapes;

public class Display {
	
	public Display() {}
	
	public double getLength(Line line) {
		Point p1 = line.getPoint1();
		Point p2 = line.getPoint2();
		return Math.sqrt( Math.pow((p2.getX()-p1.getX()),2) + Math.pow(p2.getY(), p1.getY()) );
	}
	
	public double getArea(Circle circle) {
		return Math.pow(circle.getRadius(),2) * Math.PI;
	}
	
	public double getArea(Rectangle rectangle) {
		Point p1 = rectangle.getPoint1();
		Point p2 = rectangle.getPoint2();
		Point p3 = rectangle.getPoint3();
		Point p4 = rectangle.getPoint4();
		return 0.5 * ( 
			(p1.getX()*p2.getX() - p1.getY()*p2.getY()) +
			(p2.getX()*p3.getX() - p2.getY()*p3.getY()) +
			(p3.getX()*p4.getX() - p3.getY()*p4.getY()) +
			(p4.getX()*p1.getX() - p4.getY()*p1.getY()) );
	}		
}
