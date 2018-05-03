package shapes;
import java.awt.Color;

import processing.core.*;
/**
 * 
 * @author Nathaniel Lao
 * @version 9-21-17
 */
public class Circle extends Shape {
	private double radius;
	
	/**
	 * 
	 * @param x x-coordinate of the center of the circle
	 * @param y y-coordinate of the center of the circle
	 * @param radius radius of the circle
	 * @param color color of the circle
	 */
	public Circle(double x, double y, double radius, Color color) {
		super(x, y);
		if (radius < 0) {
			throw new IllegalArgumentException("Invalid radius");
		}
		this.radius = radius;
		super.setColor(color);
	}
	/**
	 * Makes a circle with the default color, white
	 * @param x x-coordinate of the center
	 * @param y y-coordinate of the center
	 * @param radius radius of the circle
	 */
	public Circle(double x, double y, double radius) {
		super(x, y);
		if (radius < 0) {
			throw new IllegalArgumentException("Invalid radius");
		}
		this.radius = radius;
	}
	/**
	 * creates a circle with values of 0, 0, 0
	 */
	public Circle() {
		this(0, 0, 0);
	}
	
	public double calcArea() {
		double result;
		result = Math.pow(radius, 2) * PApplet.PI;
		return result;
	}

	public double calcPerimeter() {
		double result;
		result = 2 * PApplet.PI * radius;
		return result;
	}
	
	public boolean isPointInside(double x, double y) {
		boolean result = false;
		if (Math.sqrt(
				Math.pow(Math.abs(x - this.x), 2) +
				Math.pow(Math.abs(this.y - y), 2)
				) < radius) {
			result = true;
		}
		return result;
	}

	public void draw(PApplet marker) {
		marker.pushStyle();
		super.draw(marker);
		marker.ellipseMode(marker.RADIUS);
		marker.ellipse((float)x, (float)y, (float) radius * scale, (float) radius * scale);
		marker.popStyle();
	}

	
	
}
