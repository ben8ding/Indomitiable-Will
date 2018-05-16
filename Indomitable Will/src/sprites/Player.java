package sprites;

import java.util.ArrayList;
import java.util.logging.Level;

import Pickups.Capsule;
import Pickups.PowerUp;
import Pickups.Rifle;
import Pickups.Shotgun;
import Pickups.Weapon;
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
	private static final double sped = 4.0;
	private boolean firing;
	private int timer;

	private enum Direction {
		UP, RIGHT, DOWN, LEFT
	}

	// private int blockedDir;
	private ArrayList<Weapon> weapons;
	private PowerUp powerup;
	private boolean isFast;
	private int spedTime;
	/*
	 * 0 is unblocked, 1 is top, 2 is right, 3 is bottom, 4 is left
	 */
	private ArrayList<Direction> blockedDir = new ArrayList<Direction>(4);

	public Player() {
		super(350, 300, 22);
		weapons = new ArrayList<Weapon>();
		weapons.add(new Rifle());
		wall = false;
		health = 5;
		hB = new HitBox(this);
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player.png");
	}

	public void draw(PApplet drawer) {
		if (spedTime > 0) {
			isFast = true;
			spedTime--;
		} else if (spedTime == 0) {
			isFast = false;
		}
		timer++;
		drawer.pushMatrix();
		drawer.translate(xLoc, yLoc);
		drawer.rotate((float) Math.toRadians(angle));
		drawer.translate(-xLoc, -yLoc);

		drawer.image(img, xLoc - size, yLoc - size);
		//System.out.println(blockedDir);
		drawer.popMatrix();
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P
		// drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);
		if (blockedDir.contains(Direction.UP) && dy2 > 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.UP));
		} else if (blockedDir.contains(Direction.DOWN) && dy2 < 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.DOWN));
		}
		if (blockedDir.contains(Direction.RIGHT) && dx2 < 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		} else if (blockedDir.contains(Direction.RIGHT) && dx2 > 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		}
		move();
		hB.draw(drawer);
		hB.refreshLoc(this);
		
		drawer.popStyle();

	}

	public Capsule checkCollection(ArrayList<Capsule> drops) {
		Capsule result = null;
		for (Capsule drop : drops) {
			if (checkCollision(drop.getBox())) {
				result = drop;
				if (drop.getItem() instanceof Weapon) {
					weapons.add((Weapon) drop.getItem());
				} else if (drop.getItem() instanceof PowerUp) {
					powerup = (PowerUp) drop.getItem();
				}
			}
		}
		return result;
	}

	public boolean checkCollision(ArrayList<Rectangle> walls) {
		boolean result = false;
		for (Rectangle wall : walls) {
			if (checkCollision(wall)) {
				result = true;
			}

		}
		return result;
	}

	public boolean checkCollision(Rectangle hitbox) {
		// if (hB.checkCollision(wall)) {
		// result = true;
		// if(yLoc + 2*yVel > wall.getMinY() && yLoc + 2*yVel < wall.getMinY() +
		// wall.getHeight()) {
		// yLoc = (int) (wall.getMinY() - 23);
		// if(!blockedDir.contains(Direction.DOWN))
		// blockedDir.add(Direction.DOWN);
		// yVel = 0;
		// dy2 = 0;
		//
		// } else if(yLoc + 2*yVel < wall.getMaxY() + 5) {
		// yLoc = (int) (wall.getMinY() - 23);
		// if(!blockedDir.contains(Direction.DOWN))
		// blockedDir.add(Direction.DOWN);
		// yVel = 0;
		// dy2 = 0;
		//
		// }
		// if(xLoc + 2*xVel > wall.getMinX() + 5) {
		// xLoc = (int) (wall.getMinX() - 23);
		// if(!blockedDir.contains(Direction.RIGHT))
		// blockedDir.add(Direction.RIGHT);
		// xVel = 0;
		// dx2 = 0;
		// } else if(xLoc + 2*xVel < wall.getMaxX()) {
		// xLoc = (int) (wall.getMaxX() + 23);
		// if(!blockedDir.contains(Direction.LEFT))
		// blockedDir.add(Direction.LEFT);
		// xVel = 0;
		// dx2 = 0;
		// }
		// }
		return hB.checkCollision(hitbox);
	}

	private void move() {
		// if (!wall) {
		if (!isFast) {
			xVel = (int) (xVel + 0.3 * ((double) dx2 * 1.01 - 0.02 * (double) xVel));
			yVel = (int) (yVel + 0.3 * ((double) dy2 * 1.01 - 0.02 * (double) yVel));
		} else {
			xVel = (int) (xVel + 0.5 * ((double) dx2 * 1.01 - 0.01 * (double) xVel));
			yVel = (int) (yVel + 0.5 * ((double) dy2 * 1.01 - 0.01 * (double) yVel));
		}
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
		if (!blockedDir.contains(Direction.UP)) {
			if (!isFast)
				dy2 = -cs;
			else
				dy2 = -sped;

		}
		if (blockedDir.contains(Direction.DOWN)) {

		}
	}

	public void mDown() {
		angle = 0;
		if (!blockedDir.contains(Direction.DOWN)) {
			if (!isFast)
				dy2 = cs;
			else
				dy2 = sped;
		}
		if (blockedDir.contains(Direction.UP)) {

		}
	}

	public void mLeft() {
		angle = 90;
		if (!blockedDir.contains(Direction.LEFT)) {
			if (!isFast)
				dx2 = -cs;
			else
				dx2 = -sped;

		}
		if (blockedDir.contains(Direction.RIGHT)) {

		}
	}

	public void mRight() {
		angle = 270;
		if (!blockedDir.contains(Direction.RIGHT)) {
			if (!isFast)
				dx2 = cs;
			else
				dx2 = sped;
		}
		if (blockedDir.contains(Direction.LEFT)) {

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
		//System.out.println("ping");
		if(timer%weapons.get(0).getROF()==0);
			firing = true;
		
	}

	public ArrayList<Projectile> fire() {
		return this.weapons.get(0).fire(getXLoc(), getYLoc(), angle);
	}

	public HitBox getBox() {
		return this.hB;
	}

	public void speedUp(int time) {
		spedTime = time;
	}
}
