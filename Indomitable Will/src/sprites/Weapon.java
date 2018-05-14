package sprites;

import java.util.ArrayList;

import processing.core.PImage;

public class Weapon {
	private enum weaponType {SHOTGUN, RIFLE, SWORD};
	
	public int getWeaponType() {
		return 1;
	}
	
	
	
	public ArrayList<Projectile> fire(int xLoc,int yLoc, double angle) {
		
		ArrayList<Projectile> fire = new ArrayList<Projectile>();
		
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15));
		
		angle+=10;
		
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15));
		
		angle-=20;
		
		fire.add(new Projectile(xLoc, yLoc, Math.cos(Math.toRadians(angle + 90)) * 15,
				Math.sin(Math.toRadians(angle + 90)) * 15));
		
		return fire;
	}
}
 