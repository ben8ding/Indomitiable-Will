package Pickups;

import java.util.ArrayList;

import Pickups.Weapon;
import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

public class Rifle extends Weapon {

	private final int BULLET_SPEED = 6;
	public Rifle() {
		ROF =1;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();

<<<<<<< HEAD
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 10,
				Math.sin(Math.toRadians(angle + 90)) * 10));
		
=======
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * BULLET_SPEED,
				Math.sin(Math.toRadians(angle + 90)) * BULLET_SPEED));
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * BULLET_SPEED,
				Math.sin(Math.toRadians(angle + 90)) * BULLET_SPEED));
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * BULLET_SPEED,
				Math.sin(Math.toRadians(angle + 90)) * BULLET_SPEED));
>>>>>>> branch 'Ben's_branch' of https://github.com/ben8ding/Indomitiable-Will.git

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
		return this;
	}

	public void draw(PApplet drawer) {
		
	}
}
