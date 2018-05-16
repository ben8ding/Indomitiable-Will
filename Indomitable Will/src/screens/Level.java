package screens;

import java.awt.Rectangle;
import java.util.ArrayList;

import Pickups.Capsule;
import Pickups.Obtainable;
import Pickups.PowerUp;
import Pickups.Weapon;
import processing.core.PApplet;
import sprites.Enemy;
import sprites.Player;
import sprites.Projectile;

public class Level {

	private Player player;
	private ArrayList<Rectangle> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Capsule> drops;
	private int timer;

	public Level() {

		player = new Player();
		walls = new ArrayList<Rectangle>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		drops = new ArrayList<Capsule>();
		enemies.add(new Enemy(400, 250));
//		walls.add(new Rectangle(0,0,1000,10));
//		walls.add(new Rectangle(0,0,10,700));
//		walls.add(new Rectangle(990,0,-10,700));
//		walls.add(new Rectangle(0,690,1000,-20));
		// drops.add(new Capsule(40,40, new Weapon(Weapon.weaponType.SHOTGUN)));
		drops.add(new Capsule(600, 40, new PowerUp(PowerUp.powerUpType.SPEED)));
		bullets.add(new Projectile());
		walls.add(new Rectangle(500, 0, 100, 350));
		walls.add(new Rectangle(60, 350, 300, 100));
		timer = 0;

	}
	

	public void setup(PApplet drawer) {
		player.setup(drawer);
		for (Capsule object : drops) {
			object.getItem().setup(drawer);
		}

	}

	public void draw(PApplet drawer) {

		timer++;
		drawer.clear();
		drawer.pushStyle();
		drawer.background(255);
		drawer.rect(drawer.width - 20, 0, 20, 20);
		drawer.textSize(15);
		drawer.fill(0);
		drawer.text("II", drawer.width - 14, 17);
		Capsule used = player.checkCollection(drops);
		if (used != null) {
			Obtainable drop = used.getItem();
			if (drop instanceof PowerUp) {
				((PowerUp) drop).use(player);
				drops.remove(used);
			}

		}
		
		player.draw(drawer);
		player.checkCollision(walls);
		drawer.stroke(0);
		for (Rectangle object : walls) {
			drawer.rect(object.x, object.y, object.width, object.height);
		}
		for (Projectile object : bullets) {
			object.draw(drawer);
		}
		for (Enemy object : enemies) {
			object.draw(drawer);
		}
		for (Capsule object : drops) {
			object.draw(drawer);
		}
		if (player.isFiring()) {
			bullets.addAll(player.fire());
		}

		if (timer % 100 == 0) {
			for (Enemy object : enemies) {
				bullets.add(object.fire(player.getXLoc(), player.getYLoc()));
			}
		}

		// for(Projectile bullet : bullets) {
		// if(player.checkCollision(bullet.getBox()));
		// System.out.println("pong");
		//
		// }

		

		drawer.popStyle();

	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Rectangle> getWalls() {
		return walls;
	}

//	public void setWalls(ArrayList<Rectangle> walls) {
//		this.walls = walls;
//	}

	public ArrayList<Projectile> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Projectile> bullets) {
		this.bullets = bullets;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Capsule> getDrops() {
		return drops;
	}

	public void setDrops(ArrayList<Capsule> drops) {
		this.drops = drops;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void addObstacle(Rectangle rect) {
		walls.add(rect);
	}

}
