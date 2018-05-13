package sprites;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;
import java.awt.Rectangle;

/**
 * 
 * 
 *
 */
public class Player extends Basic {

	private HitBox hB;
	private int health;
	private boolean wall;
	private static final double cs = 3.5;
	private boolean firing;

	private int moveIndex = 0;
	// private int blockedDir;
	private ArrayList<Weapon> weapons;

	/*
	 * 0 is unblocked, 1 is top, 2 is right, 3 is bottom, 4 is left
	 */
	private ArrayList<Integer> blockedDir = new ArrayList<Integer>(4);

	public Player() {
		super(350, 350, 22);
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon());
		wall = false;
		health = 5;
		hB = new HitBox(this);
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player.png");
	}

	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.translate(xLoc, yLoc);
		drawer.rotate((float) Math.toRadians(angle));
		drawer.translate(-xLoc, -yLoc);

		drawer.image(img, xLoc - size, yLoc - size);

		drawer.popMatrix();
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P

		// drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);

		move();
		hB.draw(drawer);
		hB.refreshLoc(this);
		drawer.popStyle();

	}

	public boolean checkCollision(ArrayList<Rectangle> walls) {
		boolean result = false;
		for (Rectangle wall : walls) {
			if (this.hB.checkCollision(wall))
				return true;
		}
		return result;
	}

	public boolean checkCollision(Rectangle wall) {
		boolean result = false;
		if (this.hB.checkCollision(wall))
			return true;
		return result;
	}

	private void move() {
		// if (!wall) {
		xVel = (int) (xVel + 0.3 * ((double) dx2 * 1.01 - 0.02 * (double) xVel));
		yVel = (int) (yVel + 0.3 * ((double) dy2 * 1.01 - 0.02 * (double) yVel));

		xLoc += xVel;
		yLoc += yVel;

		if (xLoc < 20) {
			xLoc = 20;
		}

		if (yLoc < 20) {
			yLoc = 20;
		}
		if (yLoc > 650) {
			yLoc = 650;
		}
		if (xLoc > 980) {
			xLoc = 980;
		}
		// }
	}

	public void mUp() {
		angle = 180;
		if (!blockedDir.contains(1)) {
			dy2 = -cs;
		}

	}

	public void mDown() {
		angle = 0;
		if (!blockedDir.contains(3)) {
			dy2 = cs;
		}

	}

	public void mLeft() {
		angle = 90;
		if (!blockedDir.contains(4)) {
			dx2 = -cs;
		}

	}

	public void mRight() {
		angle = 270;
		if (!blockedDir.contains(2)) {
			dx2 = cs;
		}

	}

	public void stopY() {
		if (dy2 != 0) {
			dy2 = 0;
		}
	}

	public void stopX() {
		if (dx2 != 0) {
			dx2 = 0;
		}
	}

	public void rX() {
		xVel = 0;
	}

	public void rY() {
		yVel = 0;
	}

	public boolean isFiring() {
		return firing;
	}

	public void stopFiring() {
		firing = false;
	}

	public void startFiring() {
		firing = true;
	}

	public ArrayList<Projectile> fire() {
		return this.weapons.get(0).fire(getXLoc(), getYLoc(), angle);
	}

	public HitBox getBox() {
		return this.hB;
	}

}
