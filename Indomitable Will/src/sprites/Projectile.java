package sprites;

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
	 * @param xDir
	 * @param yDir
	 */
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
