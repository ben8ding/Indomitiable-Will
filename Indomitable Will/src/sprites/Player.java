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
		weapons.add(new Shotgun());
		wall = false;
		health = 5;
		hB = new HitBox(this);
		timer = 0;
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
		// System.out.println(timer);
		// if (firing)
		// timer++;
		// else
		// timer = 0;

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
		// if (blockedDir.contains(Direction.UP) && dy2 > 0) {
		// blockedDir.remove(blockedDir.indexOf(Direction.UP));
		// } else if (blockedDir.contains(Direction.DOWN) && dy2 < 0) {
		// blockedDir.remove(blockedDir.indexOf(Direction.DOWN));
		// }
		// if (blockedDir.contains(Direction.RIGHT) && dx2 < 0) {
		// blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		// } else if (blockedDir.contains(Direction.RIGHT) && dx2 > 0) {
		// blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		// }
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
		// for (Rectangle wall : walls) {
		// double predictedY = yLoc + 2*yVel;
		// double predictedX = xLoc + 2*xVel;
		// if(predictedY > wall.getMinY() && predictedY < wall.getMinY() + 23 ||
		// (predictedX > wall.getMinX() && predictedX + hB.getWidth() <
		// wall.getMinX())|| (predictedX < wall.getMaxX() && predictedX -hB.getWidth() >
		// wall.getMaxX())) {
		//// yLoc = (int) (wall.getMinY() - 23);
		// if(!blockedDir.contains(Direction.DOWN))
		// blockedDir.add(Direction.DOWN);
		// yVel = 0;
		// dy2 = 0;
		// } else if(predictedY < wall.getMaxY() && predictedY > wall.getMaxY()-23 &&
		// predictedX > wall.getMinX() && predictedX < wall.getMaxX()) {
		//// yLoc = (int) (wall.getMinY() - 23);
		// if(!blockedDir.contains(Direction.DOWN))
		// blockedDir.add(Direction.DOWN);
		// yVel = 0;
		// dy2 = 0;
		// }
		// if(predictedX > wall.getMinX() && predictedX < wall.getMaxX() && predictedY >
		// wall.getMinY() && predictedY < wall.getMaxY()) {
		//// xLoc = (int) (wall.getMinX() - 23);
		// if(!blockedDir.contains(Direction.RIGHT))
		// blockedDir.add(Direction.RIGHT);
		// xVel = 0;
		// dx2 = 0;
		// } else if(predictedX < wall.getMaxX() && predictedX > wall.getMaxX()-23 &&
		// predictedY > wall.getMinY() && predictedY < wall.getMaxY()) {
		//// xLoc = (int) (wall.getMaxX() + 23);
		// if(!blockedDir.contains(Direction.LEFT))
		// blockedDir.add(Direction.LEFT);
		// xVel = 0;
		// dx2 = 0;
		// }

		// }
		for (Rectangle wall : walls) {
			double predictedY = yLoc + yVel;
			double predictedX = xLoc + xVel;
			boolean bottomCol = predictedY + hB.getHeight() / 2 > wall.getMinY()
					&& predictedY < wall.getMinY()
					&& predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX - hB.getWidth() / 2 < wall.getMaxX();
			boolean topCol = predictedY - hB.getHeight() / 2 < wall.getMaxY()
					&& predictedY > wall.getMaxY()
					&& predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX - hB.getWidth() / 2 < wall.getMaxX();
			boolean leftCol = predictedX - hB.getWidth() / 2 < wall.getMaxX()
					&& predictedX > wall.getMaxX() 
					&& predictedY + hB.getHeight() / 2 > wall.getMinY()
					&& predictedY - hB.getHeight() / 2 < wall.getMaxY();
			boolean rightCol = predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX < wall.getMinX()
					&& predictedY + hB.getHeight() / 2 > wall.getMinY()
					&& predictedY - hB.getHeight() / 2 < wall.getMaxY();
			if (bottomCol) {
				if (!blockedDir.contains(Direction.DOWN))
					blockedDir.add(Direction.DOWN);
				result = true;
				yVel = 0;
				dy2 = 0;
			}
			if (topCol) {
				if (!blockedDir.contains(Direction.UP))
					blockedDir.add(Direction.UP);
				result = true;
				yVel = 0;
				dy2 = 0;
			}

			if (rightCol) {
				if (!blockedDir.contains(Direction.RIGHT))
					blockedDir.add(Direction.RIGHT);
				result = true;
				xVel = 0;
				dx2 = 0;
			} 
			if (leftCol) {
				// xLoc = (int) (wall.getMaxX() + 23);
				if (!blockedDir.contains(Direction.LEFT))
					blockedDir.add(Direction.LEFT);
				result = true;
				xVel = 0;
				dx2 = 0;
			} 
			if(!result) {
				if (blockedDir.contains(Direction.DOWN))
					blockedDir.remove(blockedDir.indexOf(Direction.DOWN));
				if (blockedDir.contains(Direction.UP))
					blockedDir.remove(blockedDir.indexOf(Direction.UP));
				if (blockedDir.contains(Direction.LEFT))
					blockedDir.remove(blockedDir.indexOf(Direction.LEFT));
				if (blockedDir.contains(Direction.RIGHT))
					blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
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
		// System.out.println("ping");
		if (timer % weapons.get(0).getROF() == 0)
			firing = true;

	}

	public ArrayList<Projectile> fire() {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();

		if (timer % this.weapons.get(0).getROF() == 0 || timer <= this.weapons.get(0).getROF())
			fire = this.weapons.get(0).fire(getXLoc(), getYLoc(), angle);
		System.out.println(fire + "hi");
		return fire;
	}

	public HitBox getBox() {
		return this.hB;
	}

	public void speedUp(int time) {
		spedTime = time;
	}
}
