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
	private final int BULLET_SPEED = 4;

	public Pistol() {
		ROF = 5;
	}

	public ArrayList<Projectile> fire(int xLoc, int yLoc, double angle) {
		ArrayList<Projectile> result = new ArrayList<Projectile>();
		result.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle)) * BULLET_SPEED,
				Math.sin(Math.toRadians(angle)) * BULLET_SPEED));
		return result;
	}
	
	public Obtainable getDrop() {
		return this;
	}
}
