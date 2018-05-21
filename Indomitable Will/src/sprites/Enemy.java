package sprites;

import processing.core.PApplet;
import shapes.Line;
import java.awt.Rectangle;

public class Enemy extends Basic {

	
	private int health;
	private final int PROJ_SPEED = 3;
	private int MAX_AMMO = 30;
	
	double wx, wy, wc; 	// weights for x, y, and constant
	int pout;		// perceptron output
	int goal;		// whether hit goal
	double l_rate;	// learning rate
	
	int ammo_cnt;
	
	
	public Enemy() {
		super(500, 350, 15);
		health = 3;
		hB = new HitBox(this);
		
		ammo_cnt = MAX_AMMO / 5;
		
		init();
	}
	
	public Enemy(int x, int y) {
		super(x, y, 15);
		health = 3;
		hB = new HitBox(this);
		
		init();
		
		ammo_cnt = MAX_AMMO / 5;
	}
	
	public void init() {
//		wx = wy = wc = 0;
		
		wx = 0.1 * (Math.random() - 0.5);
		wy = 0.1 * (Math.random() - 0.5);
		wc = 0.1 * (Math.random() - 0.5);
		
		l_rate = 0.002;
	}
	
	public void addAmmo() {
		ammo_cnt++;
		
		if(ammo_cnt > MAX_AMMO) ammo_cnt = MAX_AMMO;
	}
	
	public int getAmmoCount() {
		return ammo_cnt;
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
		
		if(wy != 0) {
			drawer.line(0.0f, (float)(- 600 * wc/wy), 900.0f, (float)(900 * (-wx - wc)/wy));
		}
		
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
	
	public void trainPerceptron() {
		
		
		for(int i=0; i<5; i++) {
			
			double x_s = Math.random();
			double y_s = Math.random();		// scale x, y
	
//			if(x_s + y_s > 1) goal = 0;
//			else goal = 1;
			
			if(x_s - y_s > 0.25) goal = 0;
			else goal = 1;
			
			
			double p = wx * x_s + wy * y_s + wc * 1.0;
			
			if(p > 0) pout = 1;
			else pout = 0;
			
			wx += (goal - pout) * l_rate * x_s;
			wy += (goal - pout) * l_rate * y_s;
			wc += (goal - pout) * l_rate;
			
		double norm = (wx*wx + wy*wy + wc*wc);
		
		norm = Math.sqrt(norm);
			
			System.out.printf("weights %f, %f %f ", wx, wy, wc);
			System.out.printf("(goal, pout) %d, %d, %f\n", goal, pout, norm);
		}		
	}
	

	
	public int inferPerceptron(int x, int y) {
		double x_s = (float)x / (float)600;
		double y_s = (float)y / (float)600;
		
		double p = wx * x_s + wy * y_s + wc * 1.0;
		
		if(p > 0) {
			pout = 1;
		}
		else {
			pout = 0;
		}	
		
		return pout;
	}
	
	public Projectile fire(int x, int y) {
		
		ammo_cnt--;
		
		int shade;
		
		if (x + y > 600) {
//			shade = 128;
//			goal = 0;
		}
		else {
//			shade = 255;
//			goal = 1;
		}
		
		if(inferPerceptron(x, y) > 0) 
			shade = 255;
		else
			shade = 128;
		
		return new Projectile(xLoc,yLoc,(x - xLoc) * 0.01,(y - yLoc) * 0.01, shade);
	}
	
	public HitBox getBox() {
		return this.hB;
	}
}
