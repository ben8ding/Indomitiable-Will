package Sprites;
import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;

public class Player extends Basic {

	private HitBox hB;
	private int health;
	private boolean wall;
	private static final double cs = 3.5;
	private boolean firing;
	public Player() {
		super(350, 300, 25);

		wall = false;
		health = 5;
		hB = new HitBox(this);

	}

	public void setup(PApplet drawer) {

	}

	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		hB.draw(drawer);
		move();
		hB.refreshLoc(this);
		drawer.popStyle();
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
		if (hB.checkCollision(other))
			wall = true;
		else
			wall = false;

		return hB.checkCollision(other);
	}

	public void move() {
		// if (!wall) {
		xVel = (int) (xVel + 0.3 * ((double) dx2 - 0.012 * (double) xVel));
		yVel = (int) (yVel + 0.3 * ((double) dy2 - 0.012 * (double) yVel));

		xLoc += xVel;
		yLoc += yVel;

		if (xLoc < -11) {
			xLoc = -11;
		}

		if (yLoc < -11) {
			yLoc = -11;
		}
		if (yLoc > 661) {
			yLoc = 661;
		}
		if (xLoc > 1011) {
			xLoc = 1011;
		}
		// }
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
		return new Projectile(getXLoc(),getYLoc(),(getXLoc()-500)*-0.1,(getYLoc()-350)*-0.1);
	}
}
