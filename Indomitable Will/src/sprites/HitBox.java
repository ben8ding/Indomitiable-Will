package sprites;

import java.awt.Rectangle;

import processing.core.PApplet;

/**
 * @author ben8d
 * @version 5-22-18
 */
public class HitBox extends Rectangle {

	public HitBox(int set) {
		super(20, 20, set, set);
	}

	public HitBox(Basic other) {
		super(other.getXLoc(), other.getYLoc(), other.getSize() * 2, other.getSize() * 2);
		other.getSize();
	}

	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.noFill();
		drawer.rect(x, y, width, height);
		drawer.popMatrix();

	}

	public void refreshLoc(Basic other) {
		setLocation(other.getXLoc() - other.getSize(), other.getYLoc() - other.getSize());
	}

	public boolean checkCollision(Rectangle other) {
		return this.intersects(other);
	}

	public boolean checkCollision(HitBox other) {
		return this.intersects(other);
	}
	
}
