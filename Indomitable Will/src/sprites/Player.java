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
	private enum Direction{
			UP, RIGHT, DOWN, LEFT
	}
	// private int blockedDir;
	private ArrayList<Weapon> weapons;
	private ArrayList<PowerUp> powerups;
	/*
	 * 0 is unblocked, 1 is top, 2 is right, 3 is bottom, 4 is left
	 */
	private ArrayList<Direction> blockedDir =  new ArrayList<Direction>(4);
	public Player() {
		super(350, 300, 22);
		weapons = new ArrayList<Weapon>();
		
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
		System.out.println(blockedDir);
		drawer.popMatrix();
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
		// basic tank is just circle :P
		// drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		// hB.draw(drawer);
		if(blockedDir.contains(Direction.UP) && dy2 > 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.UP));
		} else if(blockedDir.contains(Direction.DOWN) && dy2 < 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.DOWN));
		}
		if(blockedDir.contains(Direction.RIGHT) && dx2 < 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		} else if(blockedDir.contains(Direction.RIGHT) && dx2 > 0) {
			blockedDir.remove(blockedDir.indexOf(Direction.RIGHT));
		}
		move();
		hB.draw(drawer);
		hB.refreshLoc(this);
		drawer.popStyle();

	}

	public boolean checkCollision(ArrayList<Rectangle> walls, ArrayList<Capsule> drops) {
		boolean result = false;
		for (Rectangle wall : walls) {
			if (checkCollision(wall))
				result = true;
		}
		for(Capsule drop: drops) {
			if(checkCollision(drop.getBox())) {
				if(drop.getItem() instanceof Weapon) {
					weapons.add((Weapon) drop.getItem());
				} else {
					
				}
				
			}
		}
		return result;
	}

	public boolean checkCollision(Rectangle wall) {
		boolean result = false;
		if (hB.checkCollision(wall)) {
			result = true;
			if(yLoc + 2*yVel > wall.getMinY() && yLoc + 2*yVel < wall.getMinY() + wall.getHeight()) {	
					yLoc = (int) (wall.getMinY() - 23);
					if(!blockedDir.contains(Direction.DOWN))
						blockedDir.add(Direction.DOWN);
					yVel = 0;
					dy2 = 0;
				
			} else if(yLoc + 2*yVel < wall.getMaxY() + 5) {
					yLoc = (int) (wall.getMinY() - 23);
				if(!blockedDir.contains(Direction.DOWN))
				blockedDir.add(Direction.DOWN);
				yVel = 0;
				dy2 = 0;
				
			}
			if(xLoc + 2*xVel > wall.getMinX() + 5) {
					xLoc = (int) (wall.getMinX() - 23);
					if(!blockedDir.contains(Direction.RIGHT))
						blockedDir.add(Direction.RIGHT);
						xVel = 0;
						dx2 = 0;
			} else if(xLoc + 2*xVel < wall.getMaxX()) {
					xLoc = (int) (wall.getMaxX() + 23);
					if(!blockedDir.contains(Direction.LEFT))
						blockedDir.add(Direction.LEFT);
						xVel = 0;
						dx2 = 0;
				}
		}
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
		if (!blockedDir.contains(Direction.UP)) {
			dy2 = -cs;
		
		}
		if(blockedDir.contains(Direction.DOWN)) {
	
		}
	}

	public void mDown() {
		angle = 0;
		if (!blockedDir.contains(Direction.DOWN)) {
			dy2 = cs;
		}
		if(blockedDir.contains(Direction.UP)) {
		
		}
	}

	public void mLeft() {
		angle = 90;
		if (!blockedDir.contains(Direction.LEFT)) {
			dx2 = -cs;
			
		}
		if(blockedDir.contains(Direction.RIGHT)) {
	
		}
	}

	public void mRight() {
		angle = 270;
		if (!blockedDir.contains(Direction.RIGHT)) {
			dx2 = cs;
		}
		if(blockedDir.contains(Direction.LEFT)) {
		
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
