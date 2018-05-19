package Pickups;

import java.util.ArrayList;

import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

public class Shotgun extends Weapon {

	public Shotgun() {
		ROF = 20;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15, 255));

		angle += 10;

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15, 255));

		angle -= 20;

		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15, 255));

		return fire;

	}

	public void setup(PApplet drawer) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public PImage getImg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Obtainable getDrop() {
		// TODO Auto-generated method stub
		return null;
	}

	public void draw(PApplet drawer) {

	}
}
