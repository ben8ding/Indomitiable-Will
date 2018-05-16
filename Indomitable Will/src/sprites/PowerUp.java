package sprites;

import Pickups.Obtainable;
import processing.core.PApplet;
import processing.core.PImage;

public class PowerUp implements Obtainable {
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
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "Icons.png");
	}
	public PImage getImg() {
		// TODO Auto-generated method stub
		return img;
	}
	
	public void draw(PApplet drawer) {
		if(powerUp == powerUpType.SPEED) {
			img = img.get(0, 0, 18, 18);
		} else if (powerUp == powerUpType.FIRERATE) {
			img = img.get(72, 0, 90, 18);
		}
	}
	public void use(Player p) {
		if(powerUp == powerUpType.SPEED) {
			p.speedUp(240);
		} else if (powerUp == powerUpType.FIRERATE) {
			
		}
	}
	@Override
	public Obtainable getDrop() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
