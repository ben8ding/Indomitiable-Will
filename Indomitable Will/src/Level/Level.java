package Level;

import java.util.ArrayList;
import Sprites.Projectile;
import Sprites.Enemy;
import Sprites.Player;

import processing.core.PApplet;
import shapes.Line;

public class Level {

	
	private Player player;
	private ArrayList<Line> walls;
	private ArrayList<Projectile> bullets;
	private ArrayList<Enemy> enemies;
	
	public Level()
	{

		player = new Player();
		walls = new ArrayList<Line>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Enemy>();
		enemies.add(new Enemy());
		walls.add(new Line(500, 0, 500, 700));
		walls.add(new Line(0, 350, 1000, 350));
		bullets.add(new Projectile());
	}
	
	public void draw(PApplet drawer) {
	
		drawer.pushStyle();
		drawer.clear();
		drawer.background(255);

		player.draw(drawer);
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
		}
		
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
