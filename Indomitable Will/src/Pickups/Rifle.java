package Pickups;

import java.util.ArrayList;

import Pickups.Weapon;
import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

/**
 * @author ben8d,matthewli, Nathaniel
 * @version 5-22-18
 */
public class Rifle extends Weapon {
	private long timer;

	public Rifle() {
		ROF = 2;
		bSpeed = 10;
		timer = 0;
	}
	
	/**
	 * "Fires" projectile after a cooldown of 0.2 seconds by adding projectile into ArrayList
	 * @param xLoc x-coordinate of projectile
	 * @param yLoc y-coordinate of projectile
	 * @param angle angle to fire at
	 * @return fire ArrayList of projectiles
	 */
	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();
		if (System.nanoTime() - timer > 200000) {
			fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
					Math.sin(Math.toRadians(angle + 90)) * bSpeed));
			timer = System.nanoTime();
		}
		return fire;

	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "rifle.png");
		// TODO Auto-generated method stub

	}
}
