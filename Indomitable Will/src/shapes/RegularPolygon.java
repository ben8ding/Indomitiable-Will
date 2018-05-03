package shapes;

import java.awt.Color;

import processing.core.PApplet;

public class RegularPolygon extends Shape {
	private int numSides;
	private double sideLength;
	private Circle outCircle;
	private Circle inCircle;
	private Line[] sides;
	private double inR, outR;
	/**
	 * This constructor makes an equilateral triangle at 200,200 with side length 100.
	 */
	public RegularPolygon() {
		this(3,100);
	}
	/**
	 * This constructor makes a regular polygon at 200,200 with the number of sides and side length determined by the user.
	 * @param numSides number of sides of the polygon, must be at least 3
	 * @param sideLength length of each side of the polygon, must be at least 0
	 */
	public RegularPolygon(int numSides, double sideLength) {
		super(200, 200);
		this.numSides = numSides;
		this.sideLength = sideLength;
		sides = new Line[numSides];
		sides[0] = new Line(x, y, 0, sideLength);
		for(int i = 1; i <= numSides-1; i++) {
				sides[i] = new Line(sides[i-1].getPoint2X(), sides[i-1].getPoint2Y(), (180-calcVertexAngle())*i, sideLength);
		}
	}
	/**
	 * This method returns the number of sides in the polygon.
	 * @return number of sides in the polygon
	 */
	public int getNumSides() {
		return numSides;
	}
	/**
	 * This method returns the length of each side in the polygon.
	 * @return length of each side
	 */
	public double getSideLength() {
		return sideLength;
	}
	/**
	 * This method returns the vertex angle of the polygon in degrees.
	 * @return vertex angle of the polygon in degrees
	 */
	public double calcVertexAngle() {
		return (numSides - 2) / (double)numSides * 180;
	}
	/**
	 * This method returns the area of the rectangle.
	 * @return the area of the rectangle
	 */
	public double calcArea() {
		return .5 * numSides * Math.pow(getR(), 2) * Math.sin(2 * Math.PI / numSides);
	}
	/**
	 * This method returns the perimeter of the polygon.
	 * @return the perimeter of the polygon
	 */
	public double calcPerimeter() {
		return numSides * sideLength;
	}
	/**
	 * This method returns if the point is inside the polygon.
	 * @param x x-coordinate of the tested point
	 * @param y y-coordinate of the tested point
	 * @return if the point is inside the polygon
	 */
	public boolean isPointInside(double x, double y) {
		boolean result = false;
		Line[] test = new Line[4];
		test[0] = new Line((int)x, (int)y, 0,y);
		test[1] = new Line((int)x, (int)y, x,0);
		test[2] = new Line((int)x, (int)y, x,99999999);
		test[3] = new Line((int)x, (int)y, 99999999,y);
		int count = 0;
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < numSides; j++) {
				if (test[i].intersects(sides[j])) {
				count++;
				}
			}
			
		}
		if (count == 4) {
			result = true;
		}
		return result;
	}
	/**
	 * This method returns the inner radius, or apothem, of the polygon.
	 * @return the apothem of the polygon
	 */
	public double getr() {
		calcr();
		return inR;
	}
	/**
	 * This method returns the radius, or the distance between center and vertex, of the polygon.
	 * @return radius of the polygon
	 */
	public double getR() {
		calcR();
		return outR;
	}
	/**
	 * 
	 * This method draws the shape.
	 * @param marker PApplet that links to the DrawingSurface
	 * 
	 */
	public void draw(PApplet marker) {
		for (int i = 0; i < sides.length; i++) {
			sides[i].draw(marker);
		}
	}
	/**
	 * This method draws the inscribed and circumscribed circles of the polygon.
	 * @param marker PApplet that links to the DrawingSurface
	 */
	public void drawBoundingCircles(PApplet marker) {
		inCircle = new Circle(getCenterX(),getCenterY(),getr());
		outCircle = new Circle(getCenterX(),getCenterY(),getR());
		inCircle.setStrokeColor(Color.RED);
		outCircle.setStrokeColor(Color.RED);
		inCircle.draw(marker);
		outCircle.draw(marker);
	}
	/**
	 * This method returns the x-coordinate of the center of the polygon.
	 * @return x-coordinate of the center of the polygon
	 */
	public double getCenterX() {
		return x + sideLength/2;
	}
	/**
	 * This method returns the y-coordinate of the center of the polygon.
	 * @return y-coordinate of the center of the polygon
	 */
	public double getCenterY() {
		return y + calcArea()/calcPerimeter()/.5;
	}
	private void calcr() {
		inR = .5 * sideLength * (1 / Math.tan(Math.PI / numSides));
	}

	private void calcR() {
		outR = .5 * sideLength * (1 / Math.sin(Math.PI / numSides));
	}

}
