package shapes;

import processing.core.PApplet;

/*
 * Your code is short and concise in your intersects method
 * 
 * The drawing eclipse method is integrated well into the DrawingSurface class;
 * 
 *  Add more comments to your code so that the reader can understand what your using each method for
 *  
 *  Try adding perhaps a label that tells you the coordinates of the intersection as an additional feature
 */
/**
 * 
 * @author Nathaniel Lao
 * @version 9-16-17
 */
public class Line extends Shape {
	// coordinates and properties of the line
	private double x2, y2;

	/**
	 * draws the line
	 * 
	 * @param x1
	 *            x-coordinate of the starting point
	 * @param y1
	 *            y-coordinate for the starting point
	 * @param x2
	 *            x-coordinate of the ending point
	 * @param y2
	 *            y-coordinate of the ending point
	 */
	public Line(int x1, int y1, double x2, double y2) {
		super(x1, y1);
		this.x2 = x2;

		this.y2 = y2;
	}
	
	public Line(double x, double y, double angle, double length) {
		super(x, y);
		x2 = x + length*Math.cos(Math.toRadians(angle));
		y2 = y + length*Math.sin(Math.toRadians(angle));
	}
	/**
	 * 
	 * @param other
	 *            other line that the intersect is being found for
	 * @return returns the result if the lines intersect each other or not.
	 */
	public boolean intersects(Line other) {
		// defining variables that will be used
		double intersectX, intersectY;
		boolean intersect = false;
		// finding the intersect point of the two lines based on their endpoints
		intersectX = intersectX(other);
		intersectY = intersectY(other);

		// checking if the intersection is in between the endpoints of both lines
		if (((intersectX >= x && intersectX <= x2) || (intersectX <= x && intersectX >= x2))
				&& ((intersectY >= y && intersectY <= y2) || (intersectY <= y && intersectY >= y2))
				&& ((intersectX >= other.x && intersectX <= other.x2)
						|| (intersectX <= other.x && intersectX >= other.x2))
				&& ((intersectY >= other.y && intersectY <= other.y2)
						|| (intersectY <= other.y && intersectY >= other.y2))) {
			// if it is then "true" is returned
			intersect = true;
		}

		return intersect;
	}

	/**
	 * draws the line
	 * 
	 * @param drawer
	 *            PApplet that links to the draw method.
	 */
	public void draw(PApplet drawer) {
		super.draw(drawer);
		drawer.line((float) x, (float) y, (float) x2, (float) y2);
	}

	public void setPoint2(double x2, double y2) {
		this.x2 = x2;
		this.y2 = y2;
	}

	/**
	 * 
	 * @param other
	 *            line that this one is being compared to
	 * @return returns the x-coordinate of the intersection of the lines
	 */
	public double intersectX(Line other) {
		double intersectX = ((x * y2 - y * x2) * (other.x - other.x2)
				- (x - x2) * (other.x * other.y2 - other.x2 * other.y))
				/ ((x - x2) * (other.y - other.y2) - (y - y2) * (other.x - other.x2));
		return intersectX;
	}

	/**
	 * 
	 * @param other
	 *            line that this one is being compared to
	 * @return returns the y-coordinate of the intersection
	 */
	public double intersectY(Line other) {

		double intersectY = ((x * y2 - y * x2) * (other.y - other.y2)
				- (y - y2) * (other.x * other.y2 - other.y * other.x2))
				/ ((x - x2) * (other.y - other.y2) - (y - y2) * (other.x - other.x2));
		return intersectY;
	}

	public double calcArea() {
		return 0;
	}

	public double calcPerimeter() {
		double length = Math.sqrt(Math.pow((getX() - x2), 2) + Math.pow((getY() - y2), 2));
		return 0;
	}

	public boolean isPointInside(double x, double y) {
		double m = (y2 - getY() / x2 - getX());
	    double b = getY() - (m * getX());
	    if (y == (m * x) +  b) {
	        return true;
	    }
		return false;
	}
	public double getPoint2X() {
		return x2;
	}
	public double getPoint2Y() {
		return y2;
	}

}
