package sprites;

import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PApplet;
public class Weapon extends Pickup {
	public enum weaponType {SHOTGUN, RIFLE, SWORD};
	private weaponType weapon;
	public weaponType getWeaponType() {
		return weapon;
	}
	
	public Weapon(PImage image, weaponType weaponType) {
		super(image);
		this.weapon = weaponType;
	}
	
	public ArrayList<Projectile> fire(int xLoc,int yLoc, double angle) {
		if(weapon == weaponType.SHOTGUN) {
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
		} else if (weapon == weaponType.RIFLE) {
			return null;
		} 
		return null;
	}
}
 