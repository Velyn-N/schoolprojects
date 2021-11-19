package de.nmadev.shapes;

import java.util.ArrayList;
import java.util.Objects;

import de.nmadev.WebOut;

public class Display {
	private static Display instance;
	private WebOut wOut;

	public Display() {
		wOut = WebOut.getInstance();
		instance = this;
	}

	public static Display getInstance() {
		return (Objects.nonNull(instance)) ? instance : new Display();
	}
	
	public double getLength(Line line) {
		Point p1 = line.getPoint1();
		Point p2 = line.getPoint2();

		double res = Math.sqrt( Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2) );

		wOut.write("Length of Line: " + res);
		return res;
	}
	
	public double getArea(Circle circle) {
		double res = Math.pow(circle.getRadius(),2) * Math.PI;

		wOut.write("Area of Circle: " + res);
		return res;
	}
	
	public double getArea(Rectangle rectangle) {
		ArrayList<Point> corners = new ArrayList<>();
		corners.add(rectangle.getPoint1());
		corners.add(rectangle.getPoint2());
		corners.add(rectangle.getPoint3());
		corners.add(rectangle.getPoint4());
		
		double res = getPolygonArea(corners);

		wOut.write("Area of Rectangle: " + res);
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
		for (int i = 0; i < polygon.size() - 2; i++) {
			Point p1 = polygon.get(i);
			Point p2 = polygon.get(i + 1);	
			res += (p1.getX() * p2.getY() - p1.getY() * p2.getX());
		}

		// Final point
		Point p1 = polygon.get(polygon.size() - 1);
		Point p2 = polygon.get(0);	
		res += (p1.getX() * p2.getY() - p1.getY() * p2.getX());

		// Halve it
		res = Math.abs( res / 2 );

		return res;
	}
}
