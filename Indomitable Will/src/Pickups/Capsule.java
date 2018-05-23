package Pickups;

import java.awt.Rectangle;

import processing.core.PApplet;
import sprites.Basic;
import sprites.HitBox;

/**
 * @author ben8d
 * @version 5-22-18
 */
public class Capsule extends Basic {

	private HitBox hB;
	private Obtainable item;

	public Capsule() {
		super(600, 450, 12); 

		hB = new HitBox(this);

	}

	public Capsule(int x, int y, Obtainable z) {
		super(x, y, 12);
		this.item = z;
		hB = new HitBox(this);
	}

	@Override
	public void draw(PApplet drawer) {

		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(120, 255, 10);
		drawer.image(item.getImg(), (float)(xLoc-hB.getWidth()/2), (float)(yLoc-hB.getHeight()/2));
		
		//hB.draw(drawer);
		act();
		drawer.popStyle();
	}

	public boolean checkCollision(Rectangle other) {
		return hB.checkCollision(other);
	}

	public void act() {
		hB.refreshLoc(this);
	}

	public Obtainable getItem() {
		return item;
	}

	public HitBox getBox() {
		return this.hB;
	}

}
