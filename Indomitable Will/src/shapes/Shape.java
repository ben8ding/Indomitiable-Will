package shapes;

import processing.core.*;
import java.awt.Color;

/**
 * @author natelao
 * @version 9-29-17
 */
public abstract class Shape {
	/**
	 * x-coordinate of the shape
	 */
	protected double x;
	/**
	 * y-coordinate of the shape
	 */
	protected double y;
	private Color bground;
	/**
	 * scale of the object
	 */
	protected float scale = 1;
	private Color strokeColor;
	private double strokeWidth = 1;

	/**
	 * Sets x and y values of the object
	 * 
	 * @param x
	 *            x value of the shape
	 * @param y
	 *            y value of the shape
	 * 
	 */
	public Shape(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This sets up the drawing for the shape
	 * 
	 * @param marker
	 *            PApplet that draws the object
	 * @pre settings applied to marker beforehand will also apply to this shape
	 * @post the PApplet will have settings as defined in this method.
	 *
	 */
	public void draw(PApplet marker) {
		if (strokeColor != null) {
			marker.stroke(strokeColor.getRGB());
		} else {
			marker.stroke(0);
		}
		marker.strokeWeight((float) strokeWidth);
		if (bground != null) {
			marker.fill(bground.getRGB());
		} else {
			marker.noFill();
		}

	}

	/**
	 * 
	 * @return returns the area of the shape
	 */
	public abstract double calcArea();

	/**
	 * 
	 * @return returns the perimeter of the shape
	 */
	public abstract double calcPerimeter();

	/**
	 * 
	 * @param x
	 *            x-coordinate of the tested point
	 * @param y
	 *            y-coordinate of the tested point
	 * @return tells if the point is inside the shape
	 */
	public abstract boolean isPointInside(double x, double y);

	/**
	 * This method moves the shape to some coordinates x and y
	 * 
	 * @param x
	 *            new x-coordinate of the shape
	 * @param y
	 *            new y-coordinate of the shape
	 */
	public void setPoint1(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This method moves the shape by some coordinates x and y
	 * 
	 * @param x
	 *            x-coordinate that the shape moves by
	 * @param y
	 *            x-coordinate that the shape moves by
	 */
	public void moveBy(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * This method changes the fill color of the shape.
	 * 
	 * @param color
	 *            new color of the shape
	 */
	public void setColor(Color color) {
		bground = color;
	}

	/**
	 * This method changes the scale of the shape.
	 * 
	 * @param scale
	 *            new scale of the shape
	 */
	public void scale(float scale) {
		if (this.scale < 0.000001f && scale < 0) {

		} else {
			this.scale += scale;
		}
	}

	/**
	 * 
	 * @return returns the x-coordinate field of the shape
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return returns the y-coordinate field of the shape
	 */
	public double getY() {
		return y;
	}

	/**
	 * changes the stroke color of the object
	 * 
	 * @param color
	 *            new outline color of the shape
	 */
	public void setStrokeColor(Color color) {
		strokeColor = color;
	}

	/**
	 * this method changes the stroke width of the object.
	 * 
	 * @param strokeWidth
	 *            stroke width of the new object
	 */
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
}
