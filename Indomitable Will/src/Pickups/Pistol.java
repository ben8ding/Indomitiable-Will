package Pickups;

import java.util.ArrayList;

import processing.core.*;
import sprites.Projectile;

/**
 * 
 * @author Nathaniel,matthewli
 * @version 5-22-18
 */
public class Pistol extends Weapon {
	private long timer;

	public Pistol() {
		bSpeed = 6;
		ROF = 5;
		timer = 0;
	}

	/**
	 * "Fires" projectile after a cooldown of 0.5 seconds by adding projectile into ArrayList
	 * @param xLoc x-coordinate of projectile
	 * @param yLoc y-coordinate of projectile
	 * @param angle angle to fire at
	 * @return result ArrayList of projectiles
	 */
	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> result = new ArrayList<Projectile>();
		if (System.nanoTime() - timer > 500000) {
			result.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
					Math.sin(Math.toRadians(angle + 90)) * bSpeed, 100));
			timer = System.nanoTime();
		}
		return result;
	}

	public Obtainable getDrop() {
		return this;
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "pistol.png");
	}

}
