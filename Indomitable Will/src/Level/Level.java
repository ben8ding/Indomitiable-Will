package Level;

import java.util.ArrayList;
import Sprites.Projectile;
import Sprites.Tank;

import processing.core.PApplet;
import shapes.Line;

public class Level {

	
	private Tank player;
	private ArrayList<Line> walls;
	private ArrayList<Projectile> bullets;
	
	public Level()
	{

		player = new Tank();
		walls = new ArrayList<Line>();
		bullets = new ArrayList<Projectile>();
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

		for (Line object : walls) {
			if (player.checkCollision(object)) {
				bullets.add(new Projectile(player.getXLoc(),player.getYLoc(),(player.getXLoc()-500)*-0.1,(player.getYLoc()-350)*-0.1));
			}

		}
	
		drawer.popStyle();
		
	}

	public Tank getPlayer() {
		return player;
	}
	
}
