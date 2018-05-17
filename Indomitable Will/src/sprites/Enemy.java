package sprites;

import processing.core.PApplet;
import shapes.Line;
import java.awt.Rectangle;

public class Enemy extends Basic {

	private HitBox hB;
	private int health;
	private final int PROJ_SPEED = 3;
	public Enemy() {
		super(500, 350, 15);
		health = 3;
		hB = new HitBox(this);
	}
	
	public Enemy(int x, int y) {
		super(y, x, 15);
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
		double angle = Math.atan((double)(y-getYLoc()) / (double)(x-getXLoc()));
		System.out.println(angle);
		angle = -angle;
		if(angle < 0) {
			angle += Math.toRadians(90);
		}
		return new Projectile(getXLoc(),getYLoc(), Math.cos(angle) * PROJ_SPEED, Math.sin(angle) * PROJ_SPEED);
		//return new Projectile(getXLoc(),getYLoc(),10,0);
	}
	public HitBox getBox() {
		return this.hB;
	}
}
