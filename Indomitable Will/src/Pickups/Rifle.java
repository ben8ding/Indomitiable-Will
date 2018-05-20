package Pickups;

import java.util.ArrayList;

import Pickups.Weapon;
import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

public class Rifle extends Weapon {

	
	public Rifle() {
		ROF =3;
		bSpeed = 10;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {

		ArrayList<Projectile> fire = new ArrayList<Projectile>();



		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * bSpeed,
				Math.sin(Math.toRadians(angle + 90)) * bSpeed));


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
