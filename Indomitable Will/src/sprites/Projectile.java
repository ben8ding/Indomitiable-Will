package sprites;

import processing.core.PApplet;

public class Projectile extends Basic {

	private HitBox hB;
	
	public int shade;

	public Projectile() {
		super(20, 20, 10);
		xVel = 10;
		yVel = 10;
		hB = new HitBox(this);
		shade = 128;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param xDir xVelocity
	 * @param yDir yVelocity
	 */
	public Projectile(int x, int y, double xDir, double yDir, int s) {
		super(x, y, 10);
		xVel = 5*xDir;
		yVel = 5*yDir;
		hB = new HitBox(this);
		
		shade = s;
		
	}

	public void act() {
		this.xLoc += this.xVel;
		this.yLoc += this.yVel;
	}

	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(shade);
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);
		act();
		drawer.popStyle();
	}
	public HitBox getBox() {
		return this.hB;
	}
}
