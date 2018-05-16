package sprites;

import java.awt.Rectangle;

import processing.core.PApplet;
import shapes.Line;

public class Capsule extends Basic {

	private HitBox hB;
	private Obtainable item;

	public Capsule() {
		super(600, 450, 10);

		hB = new HitBox(this);

	}

	public Capsule(int x, int y, Obtainable z) {
		super(x, y, 10);

		this.item = z;
		hB = new HitBox(this);

	}

	@Override
	public void draw(PApplet drawer) {

		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(120,255,10);
		//drawer.image(item.getImg(), xLoc-size, yLoc-size);
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		hB.draw(drawer);
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
		return this.item;
	}
	public HitBox getBox() {
		return this.hB;
	}
}
