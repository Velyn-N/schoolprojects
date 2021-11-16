package de.nmadev.shapes;

import java.util.ArrayList;

import de.nmadev.WebOut;

public class Display {
	
	public Display() {}
	
	public double getLength(Line line) {
		Point p1 = line.getPoint1();
		Point p2 = line.getPoint2();

		double res = Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow(p2.getY(), p1.getY()) );

		WebOut.getInstance().write("Length of Line: " + res);
		return res;
	}
	
	public double getArea(Circle circle) {
		double res = Math.pow(circle.getRadius(),2) * Math.PI;

		WebOut.getInstance().write("Area of Circle: " + res);
		return res;
	}
	
	public double getArea(Rectangle rectangle) {
		// Point p1 = rectangle.getPoint1();
		// Point p2 = rectangle.getPoint2();
		// Point p3 = rectangle.getPoint3();
		// Point p4 = rectangle.getPoint4();

		// double res = 0.5 * ( 
		// 	(p1.getX()*p2.getX() - p1.getY()*p2.getY()) +
		// 	(p2.getX()*p3.getX() - p2.getY()*p3.getY()) +
		// 	(p3.getX()*p4.getX() - p3.getY()*p4.getY()) +
		// 	(p4.getX()*p1.getX() - p4.getY()*p1.getY()) );

		ArrayList<Point> corners = new ArrayList<>();
		corners.add(rectangle.getPoint1());
		corners.add(rectangle.getPoint2());
		corners.add(rectangle.getPoint3());
		corners.add(rectangle.getPoint4());
		
		double res = getPolygonArea(corners);

		WebOut.getInstance().write("Area of Rectangle: " + res);
		return res;
	}	
	
	// Function to calculate area of a polygon with any number of corners
	private double getPolygonArea(ArrayList<Point> polygon) {
		double res = 0;

		// Can't calculate area of a line
		if (polygon.size() < 3) {
			return 0;
		}

		// Iterate through points
		for (int i = 0; i < polygon.size() - 1; i++) {
			Point p1 = polygon.get(i);
			Point p2 = polygon.get(i + 1);	
			res += (p1.getX() * p2.getX() - p1.getY() * p2.getY());
		}

		// Final point
		Point p1 = polygon.get(polygon.size() - 1);
		Point p2 = polygon.get(0);	
		res += (p1.getX() * p2.getX() - p1.getY() * p2.getY());

		// Halve it
		res = res / 2;

		return res;
	}
}
