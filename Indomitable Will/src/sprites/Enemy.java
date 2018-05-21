package sprites;

import processing.core.PApplet;
import shapes.Line;
import java.awt.Rectangle;

public class Enemy extends Basic {

	
	protected int health;
	private final int PROJ_SPEED = 3;
	protected final int maxHealth;
	double wx, wy, wc; 	// weights for x, y, and constant
	int pout;		// perceptron output
	int goal;		// whether hit goal
	double l_rate;	// learning rate
	
	
	public Enemy() {
		super(500, 350, 15);
		health = 3;
		maxHealth = 3;
		hB = new HitBox(this);
		
		init();
	}
	
	public Enemy(int x, int y) {
		super(x, y, 15);
		health = 3;
		maxHealth = 3;
		hB = new HitBox(this);
		
		init();
	}
	protected Enemy(int x, int y, int hp) {
		maxHealth = hp;
		health = maxHealth;
	}
	public void init() {
		wx = wy = wc = 0;
		l_rate = 0.01;
	}
	
	public int getHp() {
		return health;
	}
	
	@Override
	public void draw(PApplet drawer) {
		
		drawer.pushStyle();
		drawer.stroke(0);
		drawer.fill(255);	
		drawer.ellipse(xLoc, yLoc, size * 2, size * 2);
		drawer.fill(0);
		drawer.textAlign(drawer.CENTER);
		drawer.textSize(12);
		drawer.text(health + "/" + maxHealth, xLoc,  yLoc-size/3+3);
		drawer.textSize(8);
	
//		if(wy != 0) 
//			drawer.line(0.0f, (float)(- 1000 * wc/wy), 1000.0f, (float)(1000 * (-wx - wc)/wy));

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
	
		int shade;
		
		if (x + y > 1000) {
			shade = 128;
			goal = 0;
		}
		else {
			shade = 255;
			goal = 1;
		}
	
		for(int i=0; i<10; i++) {
		
		double x_s = Math.random();
		double y_s = Math.random();		// scale x, y

		if(x_s + y_s > 1) goal = 1;
		else goal = 0;
		
		double p = wx * x_s + wy * y_s + wc * 1.0;
		
		if(p > 0) pout = 1;
		else pout = 0;
		
		wx += (goal - pout) * l_rate * x_s;
		wy += (goal - pout) * l_rate * y_s;
		wc += (goal - pout) * l_rate;
		
//		System.out.printf("weights %f, %f %f", wx, wy, wc);
//		System.out.printf("goal-pout %d, %d\n", goal, pout);
		}
		
		double vx = (double)(x - xLoc);
		double vy = (double)(y - yLoc);
		
		double v = Math.sqrt(vx*vx + vy*vy);
		
		vx = 3 * vx / v;
		vy = 3 * vy / v;
		
		return new Projectile(xLoc,yLoc,vx,vy, shade);
	}
	public HitBox getBox() {
		return this.hB;
	}
}
