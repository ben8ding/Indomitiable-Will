package Sprites;

import processing.core.PApplet;

public class Projectile extends Basic {

	private HitBox hB;

	public Projectile() {
		super(20, 20, 10);
		xVel = 10;
		yVel = 10;
		hB = new HitBox(this);
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param angle
	 * @param velocity do not make this negative
	 */
	public Projectile(int x, int y, int angle, double velocity) {
		super(x, y, 10);
		if (angle == 0) {
			yVel = velocity;
		} else if (angle == 90) {
			xVel = -velocity;
		} else if (angle == 180) {
			yVel = -velocity;
		} else if (angle == 270) {
			xVel = velocity;
		}
	}

	public Projectile(int x, int y, double xDir, double yDir) {
		super(x, y, 10);
		xVel = xDir;
		yVel = yDir;
		hB = new HitBox(this);
	}

	public void act() {
		this.xLoc += this.xVel;
		this.yLoc += this.yVel;
	}

	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);
		act();
		drawer.popStyle();
	}

}
