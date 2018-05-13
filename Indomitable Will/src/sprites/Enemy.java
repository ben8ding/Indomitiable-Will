package sprites;

import processing.core.PApplet;
import shapes.Line;
import java.awt.Rectangle;

public class Enemy extends Basic{

	private HitBox hB;
	private int health;
	
	public Enemy() {
		super(500, 350, 15);

		health = 3;
		hB = new HitBox(this);
		
		
	}
	
	@Override
	public void draw(PApplet drawer) {
		
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);
	
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		hB.draw(drawer);
		act();
		drawer.popStyle();
	}

	public boolean checkCollision(Rectangle other) {
		return hB.checkCollision(other);
	}

	public void takeDamage(int power) {
		health-=power;
	}
	
	public void act() {
		

		hB.refreshLoc(this);

	}
	
	public Projectile fire(int x, int y) {
		return new Projectile(getXLoc(),getYLoc(),(getXLoc()-x)*-0.1,(getYLoc()-y)*-0.1);
		//return new Projectile(getXLoc(),getYLoc(),10,0);
	}
	
}
