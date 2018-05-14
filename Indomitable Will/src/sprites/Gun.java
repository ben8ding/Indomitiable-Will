package sprites;

import processing.core.PImage;

public class Gun extends Pickup{

	public Gun(PImage img) {
		super(img);
		
		
	}
	
	public Gun() {
		super(null);
	}
	
	public Projectile fire(int x, int y, int angle) {
		return new Projectile(x, y, Math.cos(Math.toRadians(angle+90))*15, Math.sin(Math.toRadians(angle+90))*15);
	}
	
}
