package Sprites;

import processing.core.PApplet;

public abstract class Basic {

	protected int xLoc;
	protected int yLoc;
	protected double xVel;
	protected double yVel;
	protected int size;
	protected double dx;
	protected double dy;
	protected double dx2;
	protected double dy2;
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getyLoc() {
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
	
	public double getyVel() {
		return yVel;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void setXVel(double set) {
		xVel = set;
	}
	
	public void setyVel(double set) {
		yVel = set;
	}
	
	public abstract void draw(PApplet drawer);
	
}
