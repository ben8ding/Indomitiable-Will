package sprites;

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
	@Override
	public void setup(PApplet drawer) {
		// TODO Auto-generated method stub
		img = drawer.loadImage("Icons");
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
	public void draw(PApplet drawer) {
		if(powerUp == powerUpType.SPEED) {
			img = drawer.get(0, 0, 18, 18);
		} else if (powerUp == powerUpType.FIRERATE) {
			img = drawer.get(72, 0, 90, 18);
		}
	}
	
}
