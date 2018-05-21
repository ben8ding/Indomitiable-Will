package Pickups;

import java.util.ArrayList;

import processing.core.*;
import sprites.Projectile;

/**
 * 
 * @author Nathaniel
 * @version 5/18/18 11:03
 */
public class Pistol extends Weapon {

	public Pistol() {
		bSpeed = 6;
		ROF = 5;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {
		
		ArrayList<Projectile> result = new ArrayList<Projectile>();
		result.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle+90)) * bSpeed,
				Math.sin(Math.toRadians(angle+90)) * bSpeed, 100));
		return result;
	}
	
	public Obtainable getDrop() {
		return this;
	}
}
