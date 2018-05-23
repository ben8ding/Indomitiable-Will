package Pickups;

import java.util.ArrayList;

import processing.core.PImage;
import sprites.Projectile;
import processing.core.PApplet;

/**
 * @author ben8d
 * @version 5-22-18
 */
public abstract class Weapon implements Obtainable {
	protected PImage img;
	protected int bSpeed;
	protected int ROF;
	public Weapon() {
		ROF = 20;
	}

	public abstract ArrayList<Projectile> fire(int xLoc, int yLoc, double angle);


	public PImage getImg() {
		// TODO Auto-generated method stub
		return img;
	}
	public Obtainable getDrop() {
		// TODO Auto-generated method stub
		return this;
	}

	public int getROF() {
		return ROF;
	}
	
}
