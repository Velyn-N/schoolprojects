package de.nmadev.shapes;

import de.nmadev.WebOut;

public class Display {
	
	public Display() {}
	
	public double getLength(Line line) {
		Point p1 = line.getPoint1();
		Point p2 = line.getPoint2();

		double res = Math.sqrt( Math.pow((p2.getX()-p1.getX()),2) + Math.pow(p2.getY(), p1.getY()) );

		WebOut.getInstance().write("Länge: " + res);
		return res;
	}
	
	public double getArea(Circle circle) {
		double res = Math.pow(circle.getRadius(),2) * Math.PI;

		WebOut.getInstance().write("Fläche: " + res);
		return res;
	}
	
	public double getArea(Rectangle rectangle) {
		Point p1 = rectangle.getPoint1();
		Point p2 = rectangle.getPoint2();
		Point p3 = rectangle.getPoint3();
		Point p4 = rectangle.getPoint4();

		double res = 0.5 * ( 
			(p1.getX()*p2.getX() - p1.getY()*p2.getY()) +
			(p2.getX()*p3.getX() - p2.getY()*p3.getY()) +
			(p3.getX()*p4.getX() - p3.getY()*p4.getY()) +
			(p4.getX()*p1.getX() - p4.getY()*p1.getY()) );

		WebOut.getInstance().write("Fläche: " + res);
		return res;
	}		
}
