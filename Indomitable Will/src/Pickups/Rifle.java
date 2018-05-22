package Pickups;

import java.util.ArrayList;

import Pickups.Weapon;
import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

/**
 * @author ben8d
 * @version 5-22-18
 */
public class Rifle extends Weapon {
	private PImage img;
	
	public Rifle() {
		ROF = 1;
		bSpeed = 10;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
				Math.sin(Math.toRadians(angle + 90)) * bSpeed));
		return fire;

	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "rifle.png");
		// TODO Auto-generated method stub

	}
	@Override
	public PImage getImg() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public Obtainable getDrop() {
		// TODO Auto-generated method stub
		return this;
	}

	public void draw(PApplet drawer) {
		drawer.image(img, 0, 0);
	}
}
