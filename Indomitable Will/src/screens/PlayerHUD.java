package screens;

import processing.core.*;
import sprites.Player;

public class PlayerHUD {
	private Player p;
	private PShape pistol, shotgun, rifle, powerUp;
	public PlayerHUD(Player p) {
		this.p = p;
	}
	public void setup(PApplet drawer) {
		pistol = drawer.createShape(drawer.RECT, 20, 710, 30, 30, 5);
		shotgun = drawer.createShape(drawer.RECT, 60, 710, 30, 30, 5);
		rifle = drawer.createShape(drawer.RECT, 100, 710, 30, 30, 5);
		powerUp = drawer.createShape(drawer.RECT, 140, 710, 30, 30, 5);
		
	}
	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.pushMatrix();
		drawer.stroke(0);
		drawer.strokeWeight(20);
		drawer.shape(pistol);
		drawer.shape(shotgun);
		drawer.shape(rifle);
		drawer.shape(powerUp);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
