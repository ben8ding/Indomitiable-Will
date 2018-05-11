package sprites;

import processing.core.PApplet;
import shapes.Line;

public class Capsule extends Basic {

	private HitBox hB;
	private Pickup item;

	public Capsule() {
		super(600, 450, 10);

		hB = new HitBox(this);

	}

	public Capsule(int x, int y, Pickup z) {
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

	public int checkCollision(Line other) {

		return hB.checkCollision(other);
	}

	public void act() {

		hB.refreshLoc(this);

	}
	
	public Pickup getItem() {
		return this.item;
	}

}
