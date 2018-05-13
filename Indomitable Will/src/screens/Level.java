package screens;
 
import java.util.ArrayList;

import processing.core.PApplet;
import shapes.Line;

import sprites.Capsule;

import java.awt.Rectangle;

import sprites.Enemy;
import sprites.Gun;
import sprites.Player;
import sprites.Projectile;

public class Level {

	
	private Player player;
	private ArrayList<Rectangle> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<Capsule> drops;
	private int timer;
	
	public Level()
	{

		player = new Player();
		walls = new ArrayList<Rectangle>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		drops = new ArrayList<Capsule>();
		enemies.add(new Enemy());

		drops.add(new Capsule(40,40, new Gun()));

		walls.add(new Rectangle(500, 0, 20, 700));
		walls.add(new Rectangle(0, 350, 1000, 20));

		
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
		drawer.rect(drawer.width-20,0,20,20);
		drawer.textSize(15);
		drawer.fill(0);
		drawer.text("II", drawer.width - 14,17);

		player.draw(drawer);
		drawer.stroke(255);

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
		if(player.isFiring()) {
			bullets.addAll(player.fire());
		}
		
		if(timer%100==0) {
			for (Enemy object : enemies) {
				bullets.add(object.fire(player.getXLoc(), player.getYLoc()));
			}
		}
	
		
		if(player.checkCollision(walls)) {
			System.out.println("ping");
		}
		
		drawer.popStyle();
		
	}

	public Player getPlayer() {
		return player;
	}
	
}
