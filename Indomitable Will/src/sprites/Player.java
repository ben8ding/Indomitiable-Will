package sprites;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;

public class Player extends Basic {

	private HitBox hB;
	private int health;
	private boolean wall;
	private static final double cs = 3.5;
	private boolean firing;
	private int moveIndex = 0;

	public Player() {
		super(350, 300, 22);
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
		drawer.image(img, xLoc - 24, yLoc - 24);
		drawer.popMatrix();
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P

		// drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);

		if (moveIndex % 2 == 0) {
			move();
		}
		moveIndex++;
		hB.refreshLoc(this);
		drawer.popStyle();

	}

	public boolean checkCollision(ArrayList<Line> walls) {
		boolean result = false;
		for (Line l : walls) {
			if (hB.checkCollision(l)) {
				//needs to be edited to include with side of the player
				result = true;
				break;
			}
		}
		System.out.println(result);
		return result;
	}

	private void move() {
		// if (!wall) {
		xVel = (int) (xVel + 0.3 * ((double) dx2 - 0.012 * (double) xVel));
		yVel = (int) (yVel + 0.3 * ((double) dy2 - 0.012 * (double) yVel));

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
		dy2 = -cs;
	}

	public void mDown() {
		angle = 0;
		dy2 = cs;
	}

	public void mLeft() {
		angle = 90;
		dx2 = -cs;
	}

	public void mRight() {
		angle = 270;
		dx2 = cs;
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

	public Projectile fire() {
		return new Projectile(getXLoc(), getYLoc(), Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15);
	}
}
