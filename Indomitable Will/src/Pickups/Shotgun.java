package Pickups;

import java.util.ArrayList;

import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

/**
 * @author ben8d
 * @version 5-22-18
 */
public class Shotgun extends Weapon {

	public Shotgun() {
		ROF = 10;
		bSpeed = 3;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
				Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));

		angle += 10;

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
				Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));

		angle -= 20;

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
				Math.sin(Math.toRadians(angle + 90)) * bSpeed, 255));

		return fire;

	}

	public void setup(PApplet drawer) {
		// TODO Auto-generated method stub
		img=drawer.loadImage("sprites" + System.getProperty("file.separator") + "shotgun.png");
	}
}
