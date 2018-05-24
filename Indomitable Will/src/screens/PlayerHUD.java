package screens;

import java.util.ArrayList;

import Pickups.Pistol;
import Pickups.PowerUp;
import Pickups.PowerUp.powerUpType;
import Pickups.Rifle;
import Pickups.Shotgun;
import Pickups.Weapon;
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
	private PImage pistol, shotgun, rifle, powerUpSpeed, powerUpFireRate;
	private PShape pistolRect, shotgunRect, rifleRect, powerUpRect, hpBar;
	public PlayerHUD(Player p) {
		this.p = p;
	}
	public PlayerHUD(PlayerHUD p) {
		this.p = p.p;
		pistol = p.pistol;
		shotgun = p.shotgun;
		rifle = p.rifle;
		pistolRect = p.pistolRect;
		shotgunRect = p.shotgunRect;
		rifleRect = p.rifleRect;
		powerUpRect = p.powerUpRect;
		hpBar = p.hpBar;
		powerUpFireRate = p.powerUpFireRate;
		powerUpSpeed = p.powerUpSpeed;
	}
	public void setup(PApplet drawer) {
		pistolRect = drawer.createShape(drawer.RECT, 20, 710, INDIC_SIZE, INDIC_SIZE, 5);
		shotgunRect = drawer.createShape(drawer.RECT, 120, 710, INDIC_SIZE, INDIC_SIZE, 5);
		rifleRect = drawer.createShape(drawer.RECT, 70, 710, INDIC_SIZE, INDIC_SIZE, 5);
		powerUpRect = drawer.createShape(drawer.RECT, 170, 710, INDIC_SIZE, INDIC_SIZE, 5);
		hpBar = drawer.createShape(drawer.RECT, 20, 680, 200, 20, 10);
		powerUpSpeed = drawer.loadImage("sprites/" + System.getProperty("file.separator") + "Icons.png").get(0, 0, 18, 18);
		powerUpFireRate = drawer.loadImage("sprites/" + System.getProperty("file.separator") + "Icons.png").get(72, 0, 18, 18);
	}
	public void draw(PApplet drawer) {
		drawer.pushStyle();
		drawer.pushMatrix();
		Weapon playerCurrWep = p.getCurrentWeapon();
		drawer.strokeWeight(10);
		if(playerCurrWep instanceof Pistol) {
			drawer.rect(20, 710, INDIC_SIZE, INDIC_SIZE,5);
		} else if (playerCurrWep instanceof Rifle) {
			drawer.rect(70, 710, INDIC_SIZE, INDIC_SIZE,5);
		} else if (playerCurrWep instanceof Shotgun) {
			drawer.rect(120, 710, INDIC_SIZE, INDIC_SIZE,5);
		}
		drawer.shape(pistolRect);
		drawer.shape(shotgunRect);
		drawer.shape(rifleRect);
		drawer.shape(powerUpRect);
		ArrayList<Weapon> weapons = p.getWeapons();
		for(Weapon w : weapons) {
			if(w instanceof Pistol && pistol == null) {
				pistol = w.getImg();
			} else if (w instanceof Shotgun && shotgun == null) {
				shotgun = w.getImg();
			} else if (w instanceof Rifle && rifle == null) {
				rifle = w.getImg();
			}
		}
		if(p.getBuff() == PowerUp.powerUpType.SPEED) {
			drawer.image(powerUpSpeed, 180, 720);
		} else if(p.getBuff() == PowerUp.powerUpType.FIRERATE) {
			drawer.image(powerUpFireRate, 180, 720);
		}
		if(pistol != null) {
			drawer.image(pistol, 30, 720);
		}
		if(shotgun != null) {
			drawer.image(shotgun, 130, 720);
		}
		if(rifle != null) {
			drawer.image(rifle, 80, 720);
		}
		
		drawer.strokeWeight(1);
		drawer.shape(hpBar);
		
		drawer.fill(0,255, 0);
		drawer.rect(20, 680, p.getHp()/ 50f * 200, 20, 10);
		drawer.fill(0);
		
		drawer.textSize(12);
		drawer.text(p.getHp() + "/50", 100, 695);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
