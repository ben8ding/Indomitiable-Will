package sprites;

import processing.Perceptron;
import processing.core.PApplet;

import java.awt.Rectangle;

public class Enemy extends Basic {

	protected int health;
	private final int PROJ_SPEED = 3;
	private int MAX_AMMO = 50;
	protected final int maxHealth;
	Perceptron perceptron;
	int ammo_cnt;
	
	public Enemy() {
		super(500, 350, 15);
		health = 3;
		maxHealth = 3;
		hB = new HitBox(this);
		perceptron = new Perceptron();
		ammo_cnt = MAX_AMMO / 5;

	}

	public Enemy(int x, int y) {
		super(x, y, 15);
		health = 3;
		maxHealth = 3;
		hB = new HitBox(this);
		perceptron = new Perceptron();

		ammo_cnt = MAX_AMMO / 5;
	}

	protected Enemy(int x, int y, int hp) {
		maxHealth = hp;
		health = maxHealth;
	}

	public void addAmmo() {
		ammo_cnt++;

		if (ammo_cnt > MAX_AMMO)
			ammo_cnt = MAX_AMMO;
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

		if (perceptron.getWy() != 0 && perceptron.getLid() >= 3) {
			drawer.stroke(255, 128, 128);
			drawer.line(0.0f, (float) (-600 * perceptron.getWc() / perceptron.getWy()), 
						900.0f, (float)((- 900 * perceptron.getWx() - 600 * perceptron.getWc()) / perceptron.getWy()));
			drawer.fill(255, 128, 128);
			drawer.text("perceptron boundary", 450, 
						(float)((- 450 * perceptron.getWx() - 600 * perceptron.getWc()) / perceptron.getWy()));
		}

		drawer.fill(0);
		drawer.textAlign(drawer.CENTER);
		drawer.textSize(12);
		drawer.text(health + "/" + maxHealth, xLoc, yLoc - size / 3 + 3);
		drawer.textSize(8);

		hB.draw(drawer);
		act();
		drawer.popStyle();
	}

	public boolean checkCollision(Rectangle other) {
		return hB.checkCollision(other);
	}

	public void takeDamage(int power) {
		health -= power;
	}

	public void act() {
		hB.refreshLoc(this);
	}

	public Projectile fire(int x, int y) {

		ammo_cnt--;

		int shade;

		if (perceptron.inferPerceptron(x, y) > 0)
			shade = 255;							// The shade changes according to whether AI predict hit or not. 
		else
			shade = 128;

		double vx = (double) (x - xLoc);			// Projectile direction is hard coded
		double vy = (double) (y - yLoc);

		double v = Math.sqrt(vx * vx + vy * vy);

		vx = 3 * vx / v;
		vy = 3 * vy / v;							// Projectile direction is hard coded

		return new Projectile(xLoc, yLoc, vx, vy, shade);
	}

	public HitBox getBox() {
		return this.hB;
	}
	
	public Perceptron getPerceptron() {
		return perceptron;
	}

	public void setPerceptron(Perceptron perceptron) {
		this.perceptron = perceptron;
	}
	
	public void train(int lid) {
		perceptron.trainPerceptron(lid);
	}
}
