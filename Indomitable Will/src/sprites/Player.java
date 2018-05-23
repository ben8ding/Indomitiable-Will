package sprites;

/**
 * @author ben8d
 * @version 5-22-18
 */
import java.util.ArrayList;
import java.util.logging.Level;

import Pickups.Capsule;
import Pickups.Obtainable;
import Pickups.Pistol;
import Pickups.PowerUp;
import Pickups.Rifle;
import Pickups.Shotgun;
import Pickups.Weapon;
import processing.core.PApplet;
import processing.core.PImage;
import java.awt.Rectangle;

/**
 * @author Nathaniel,Matthew,Ben
 * @version 5-20-18 20:25
 *
 */
public class Player extends Basic {

	private int health;
	private boolean wall;
	private static final double cs = 3.48;
	private static final double sped = 3.52;
	private boolean firing;
	private int timer; 
	private Weapon currentWeapon = null;

	private enum Direction {
		UP, RIGHT, DOWN, LEFT
	}

	private PImage shotgun, pistol, rifle;
	// private int blockedDir;
	private ArrayList<Weapon> weapons;
	private boolean isFast;
	// spedTime/60 = numSeconds
	private int spedTime;
	/*
	 * 0 is unblocked, 1 is top, 2 is right, 3 is bottom, 4 is left
	 */
	private ArrayList<Direction> blockedDir = new ArrayList<Direction>(4);

	public Player() {

		super(50, 350, 22);
		weapons = new ArrayList<Weapon>();
		wall = false;
		health = 100;
		hB = new HitBox(this);
		timer = 0;
	}

	public Player(Player p) {
		super(50, 350, 22);
		img = p.img;
		weapons = p.weapons;
		currentWeapon = p.currentWeapon;
		health = p.health;
		hB = p.hB;
		wall = p.wall;
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player_fists.png");
		shotgun = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player_shotgun.png");
		rifle = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player_rifle.png");
		pistol = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player_pistol.png");
	}

	public void draw(PApplet drawer) {
		if (drawer.key == '1') {
			for (Weapon w : weapons) {
				if (w instanceof Pistol) {
					currentWeapon = w;
				}
			}
		} else if (drawer.key == '2') {
			for (Weapon w : weapons) {
				if (w instanceof Rifle) {
					currentWeapon = w;
				}
			}
		} else if (drawer.key == '3') {
			for (Weapon w : weapons) {
				if (w instanceof Shotgun) {
					currentWeapon = w;
				}
			}
		}
		if (weapons.size() == 1) {
			currentWeapon = weapons.get(0);
		}
		if (spedTime > 0) {
			isFast = true;
			spedTime--;
		} else if (spedTime == 0) {
			isFast = false;
		}
		timer++;

		drawer.pushMatrix();
		drawer.translate(xLoc, yLoc);
		drawer.rotate((float) Math.toRadians(angle + 90));
		drawer.translate(-xLoc, -yLoc);
		if (currentWeapon == null) {
			drawer.image(img, xLoc - img.width / 2, yLoc - img.width / 2);
		} else if (currentWeapon instanceof Shotgun) {
			drawer.image(shotgun, xLoc - shotgun.width / 2, yLoc - shotgun.height / 2);
		} else if (currentWeapon instanceof Pistol) {
			drawer.image(pistol, xLoc - pistol.width / 2, yLoc - pistol.width / 2);
		} else if (currentWeapon instanceof Rifle) {
			drawer.image(rifle, xLoc - rifle.width / 2, yLoc - rifle.width / 2);
		}

		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);

		move();
		//hB.draw(drawer);
		hB.refreshLoc(this);

		drawer.popStyle();
		drawer.popMatrix();
	}

	public Capsule checkCollection(ArrayList<Capsule> drops) {
		Capsule result = null;
		for (Capsule drop : drops) {
			if (checkCollision(drop.getBox())) {
				result = drop;
				Obtainable o = drop.getItem();
				if (o instanceof Weapon && !weapons.contains(o)) {
					weapons.add((Weapon) drop.getItem());
				}
			}
		}
		return result;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean checkCollision(ArrayList<Rectangle> walls) {
		boolean result = false;
		for (Rectangle wall : walls) {
			double predictedY = yLoc + yVel;
			double predictedX = xLoc + xVel;

			boolean bottomCol = predictedY + hB.getHeight() / 2 > wall.getMinY()
					&& predictedY < wall.getMinY() - hB.getHeight() / 4
					&& predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX - hB.getWidth() / 2 < wall.getMaxX();
			boolean topCol = predictedY - hB.getHeight() / 2 < wall.getMaxY()
					&& predictedY > wall.getMaxY() + hB.getHeight() / 4
					&& predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX - hB.getWidth() / 2 < wall.getMaxX();
			boolean leftCol = predictedX - hB.getWidth() / 2 < wall.getMaxX()
					&& predictedX > wall.getMaxX() + hB.getWidth() / 4
					&& predictedY + hB.getHeight() / 2 > wall.getMinY()
					&& predictedY - hB.getHeight() / 2 < wall.getMaxY();
			boolean rightCol = predictedX + hB.getWidth() / 2 > wall.getMinX()
					&& predictedX < wall.getMinX() - hB.getWidth() / 4
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
			if (!result) {
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

		return hB.checkCollision(hitbox);

	}

	private void move() {

		// if (!wall) {
		if (!isFast) {
			xVel = (int) (xVel + 0.3 * ((double) dx2 * 1.01 - 0.02 * (double) xVel));
			yVel = (int) (yVel + 0.3 * ((double) dy2 * 1.01 - 0.02 * (double) yVel));
		} else {
			xVel = (int) (xVel + 0.3 * ((double) dx2 * 1.01 - 0.02 * (double) xVel));
			yVel = (int) (yVel + 0.3 * ((double) dy2 * 1.01 - 0.02 * (double) yVel));
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
		if (weapons.size() > 0)
			firing = true;
	}

	public ArrayList<Projectile> fire() {
		ArrayList<Projectile> fire = new ArrayList<Projectile>();
		if (currentWeapon != null) {
			fire = currentWeapon.fire(getXLoc(), getYLoc(), angle);
		}

		return fire;
	}

	public HitBox getBox() {
		return this.hB;
	}

	public void takeDamage() {
		health--;
	}

	public void heal(int amount) {
		health += amount;
	}

	public int getHp() {
		return health;
	}

	public int getROF() {
		if (currentWeapon != null)
			return currentWeapon.getROF();
		else
			return 0;
	}

	public void speedUp(int time) {
		spedTime = time;
	}

	public boolean switchWeapon(Weapon w) {
		boolean result = false;
		if (weapons.contains(w)) {
			result = true;
			currentWeapon = w;
		}
		return result;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
}
