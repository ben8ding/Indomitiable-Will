package screens;

import java.util.ArrayList;

import processing.core.PApplet;
import shapes.Line;
import sprites.Enemy;
import sprites.Player;
import sprites.Projectile;

public class Level {

	
	private Player player;
	private ArrayList<Line> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Enemy> enemies;
	private int timer;
	
	public Level()
	{

		player = new Player();
		walls = new ArrayList<Line>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy());
		walls.add(new Line(500, 0, 500, 700));
		walls.add(new Line(0, 350, 1000, 350));
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
//		drawer.rect(drawer.width-20,drawer.height,20,20);
//		drawer.textSize(10);
		player.draw(drawer);
		player.checkCollision(walls);
		drawer.stroke(0);

		for (Line object : walls) {
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
