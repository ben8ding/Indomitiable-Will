package Pickups;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.Player;

/**
 * 
 * @author Nathaniel
 * @version 5-12-18
 */
public class PowerUp implements Obtainable {
	private PImage original;
	private PImage img;
	public enum powerUpType {
		SPEED, FIRERATE
	}
	private powerUpType powerUp;
	public PowerUp(powerUpType type) {
		powerUp = type;
	}
	public void setup(PApplet drawer) {
		// TODO Auto-generated method stub
		original = drawer.loadImage("sprites" + System.getProperty("file.separator") + "Icons.png");
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
	public PImage getImage() {
		if(powerUp == powerUpType.SPEED) {
			img = original.get(0, 0, 18, 18);
		} else if (powerUp == powerUpType.FIRERATE) {
			img = original.get(72, 0, 18, 18);
		}
		return img;
	}
	/**
	 * Increases speed of player when player picks up powerup
	 * @param p player object
	 */
	public void use(Player p) {
		if(powerUp == powerUpType.SPEED) {
			p.speedUp(240);
		}
	}
	
}
