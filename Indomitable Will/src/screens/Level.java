package screens;
 
import java.util.ArrayList;

import processing.core.PApplet;
import shapes.Line;
import shapes.Rectangle;
import sprites.Enemy;
import sprites.Player;
import sprites.Projectile;

public class Level {

	
	private Player player;
	private ArrayList<Rectangle> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Enemy> enemies;
	private int timer;
	
	public Level()
	{

		player = new Player();
		walls = new ArrayList<Rectangle>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy());
		walls.add(new Rectangle(500, 0, 20, 700));
		walls.add(new Rectangle(0, 350, 1000, 20));
		//bullets.add(new Projectile());
		timer = 0;
		
	}
	public void setup(PApplet drawer) {
		player.setup(drawer);
	}
	public void draw(PApplet drawer) {
		
		timer++;
		
		drawer.pushStyle();
		drawer.clear();
		drawer.background(255);
		drawer.rect(drawer.width-20,0,20,20);
		drawer.textSize(15);
		drawer.fill(0);
		drawer.text("II", drawer.width - 14,17);

		player.draw(drawer);
		player.checkCollision(walls);
		drawer.stroke(0);

		for (Rectangle object : walls) {
			object.draw(drawer);
		}
		for (Projectile object : bullets) {
			object.draw(drawer);
		}
		for (Enemy object : enemies) {
			object.draw(drawer);
		}
		if(player.isFiring()) {
			bullets.add(player.fire());
			for (Enemy object : enemies) {
				bullets.add(object.fire(player.getXLoc(), player.getYLoc()));
			}
		}
		
		/*if(timer%100==0) {
			for (Enemy object : enemies) {
				object.fire(player.getXLoc(), player.getYLoc());
			}
		}*/
		
		for (Projectile object : bullets) {
			for (Enemy object2 : enemies) {
				
			}
		}
		
		drawer.popStyle();
		
	}

	public Player getPlayer() {
		return player;
	}
	
}
