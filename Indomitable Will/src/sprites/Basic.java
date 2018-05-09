package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Basic {
	protected PImage img;
	protected int xLoc;
	protected int yLoc;
	protected double xVel;
	protected double yVel;
	protected int size;
	protected double dx2;
	protected double dy2;
	protected double angle;
	public Basic(int x, int y) {
		xLoc = x;
		yLoc = y;
	}
	
	public Basic(int x, int y, int size) {
		xLoc = x;
		yLoc = y;
		this.size = size;
	}
	
	public Basic()
	{
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	
	public void setXLoc(int set) {
		xLoc = set;
	}
	
	public void setyLoc(int set) {
		yLoc = set;
	}
	
	public double getXVel() {
		return xVel;
	}
	
	public double getYVel() {
		return yVel;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setXVel(double set) {
		xVel = set;
	}
	
	public void setYVel(double set) {
		yVel = set;
	}
	
	public abstract void draw(PApplet drawer);
	
}
