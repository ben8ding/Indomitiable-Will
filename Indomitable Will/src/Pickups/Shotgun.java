package Pickups;

import java.util.ArrayList;

import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

/**
 * @author ben8d,matthewli
 * @version 5-22-18
 */
public class Shotgun extends Weapon {
	private long timer;

	public Shotgun() {
		ROF = 10;
		bSpeed = 3;
		timer = 0;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {
		ArrayList<Projectile> fire = new ArrayList<Projectile>();
		if (System.nanoTime() - timer > 1000000000) {

			fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
					Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));

			angle += 10;

			fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
					Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));

			angle -= 20;

			fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
					Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));
			timer = System.nanoTime();
		}
		return fire;

	}

	public void setup(PApplet drawer) {
		// TODO Auto-generated method stub
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "shotgun.png");
	}

}
