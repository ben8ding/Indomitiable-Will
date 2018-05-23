package screens;

import processing.core.*;
import sprites.Player;
/**
 * 
 * @author Nathaniel
 *
 */
public class PlayerHUD {
	private final int INDIC_SIZE = 40;
	private Player p;
	private PShape pistol, shotgun, rifle, powerUp, hpBar;
	public PlayerHUD(Player p) {
		this.p = p;
	}
	public void setup(PApplet drawer) {
		pistol = drawer.createShape(drawer.RECT, 20, 710, INDIC_SIZE, INDIC_SIZE, 5);
		shotgun = drawer.createShape(drawer.RECT, 70, 710, INDIC_SIZE, INDIC_SIZE, 5);
		rifle = drawer.createShape(drawer.RECT, 120, 710, INDIC_SIZE, INDIC_SIZE, 5);
		powerUp = drawer.createShape(drawer.RECT, 170, 710, INDIC_SIZE, INDIC_SIZE, 5);
//		hpBar = drawer.createShape(drawer.RECT, 10);
	}
	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.pushMatrix();
		drawer.shape(pistol);
		drawer.shape(shotgun);
		drawer.shape(rifle);
		drawer.shape(powerUp);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
