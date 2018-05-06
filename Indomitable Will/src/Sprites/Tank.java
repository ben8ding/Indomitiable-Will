package Sprites;
import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;

public class Tank extends Basic {

	private HitBox hB;
	public static final double cs = 3.5;

	public Tank() {

		xLoc = 350;
		yLoc = 300;
		size = 25;
		hB = new HitBox(this);

	}

	public void setup(PApplet drawer) {

	}

	public void draw(PApplet drawer) {

		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		hB.draw(drawer);

		act();
	}

	public void act() {
		xLoc += xVel;
		yLoc += yVel;

		move();

		hB.refreshLoc(this);

	}

	public boolean checkCollisionU(Line other) {
		return hB.checkCollisionU(other);
	}

	public boolean checkCollisionD(Line other) {
		return hB.checkCollisionD(other);
	}

	public boolean checkCollisionR(Line other) {
		return hB.checkCollisionR(other);
	}

	public boolean checkCollisionL(Line other) {
		return hB.checkCollisionL(other);
	}
	
	public boolean checkCollision(Line other) {
		return hB.checkCollision(other);
	}

	public void move() {

		double dxf = (double) dx;

		dxf += 0.3 * ((double) dx2 - 0.012 * (float) dx);

		dx = (int) dxf;

		double dyf = (double) dy;

		dyf += 0.3 * ((double) dy2 - 0.012 * (double) dy);

		dy = (int) dyf;

		xLoc += dx;
		yLoc += dy;

		if (xLoc < -11) {
			xLoc = -11;
		}

		if (yLoc < -11) {
			yLoc = -11;
		}
		if (yLoc > 589) {
			yLoc = 589;
		}
		if (xLoc > 989) {
			xLoc = 989;
		}

	}

	public void mUp() {

		dy2 = -cs;

	}

	public void mDown() {
		dy2 = cs;
	}

	public void mRight() {
		dx2 = cs;
	}

	public void mLeft() {
		dx2 = -cs;
	}

	public void sUp() {

		dy2 = 0;
	}

	public void sDown() {
		dy2 = 0;
	}

	public void sRight() {
		dx2 = 0;
	}

	public void sLeft() {
		dx2 = 0;
	}
	
	public void rX() {

		dy = 0;
	}

	public void rY() {
		dy = 0;
	}

	

}
