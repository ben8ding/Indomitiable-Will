package shapes;

import java.awt.Color;

import processing.core.*;

/**
 * 
 * @author Nathaniel Lao
 * @version 9-20-2017
 */
public class Rectangle extends Shape {
	private double width, height;

	/**
	 * Creates a default instance of a Rectangle object with all dimensions set to
	 * zero.
	 */
	public Rectangle() {
		this(0, 0, 0, 0);
	}

	/**
	 * 
	 * Creates a new instance of a Rectangle object with the left and right edges of
	 * the rectangle at x and x + width. The top and bottom edges are at y and y +
	 * height.
	 * 
	 * @param x
	 *            The x-coordinate of the center
	 * @param y
	 *            The y-coordinate of the center
	 * @param width
	 *            width of the rectangle
	 * @param height
	 *            height of the rectangle
	 * @param color
	 *            color of the interior of the rectangle
	 */
	public Rectangle(double x, double y, double width, double height, Color color) {
		super(x, y);
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Invalid width/height");
		}
		this.width = width;
		this.height = height;
		super.setColor(color);
	}

	/**
	 * 
	 * Creates a new instance of a Rectangle object with the left and right edges of
	 * the rectangle at x and x + width. The top and bottom edges are at y and y +
	 * height.
	 * 
	 * @param x
	 *            The x-coordinate of the center
	 * @param y
	 *            The y-coordinate of the center
	 * @param width
	 *            width of the rectangle
	 * @param height
	 *            height of the rectangle This rectangle will be a default color,
	 *            white.
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x, y);
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Invalid width/height");
		}
		this.width = width;
		this.height = height;

	}

	public double calcPerimeter() {
		double peri = 2 * (width + height);
		return peri;
	}

	public double calcArea() {
		double area = width * height;
		return area;
	}

	public boolean isPointInside(double x, double y) {
		boolean result = false;
		if (x > this.x - width / 2 && x < this.x + width / 2 && y > this.y - height / 2 && y < this.y + height / 2) {
			result = true;
		}
		return result;
	}

	public Line getLeft() {
		return new Line(x, y, x, y + height);
	}

	public Line getTop() {
		return new Line(x, y, x + width, y);
	}

	public Line getBottom() {
		return new Line(x, y + height, x + width, y + height);
	}

	public Line getRight() {
		return new Line(x + width, y, x + width, y + height);
	}

	/**
	 * Draws a new instance of a Rectangle object with the left and right edges of
	 * the rectangle at x and x + width. The top and bottom edges are at y and y +
	 * height.
	 * 
	 * @param marker
	 *            links to the PApplet class's draw method, which draws everything
	 * @pre settings applied to marker beforehand will also apply to this rectangle
	 */
	public void draw(PApplet marker) {
		marker.pushMatrix();
		marker.pushStyle();
		super.draw(marker);
		marker.translate((float) x, (float) y);
		marker.rect(0, 0, (float) width * scale, (float) height * scale);
		marker.popStyle();
		marker.popMatrix();
	}
}
