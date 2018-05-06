package Level;

import java.util.ArrayList;

import Sprites.Tank;
import processing.core.PApplet;
import shapes.Line;

public class Level {

	
	private Tank player;
	private ArrayList<Line> walls;
	
	public Level()
	{

		player = new Tank();
		walls = new ArrayList<Line>();
		walls.add(new Line(500, 0, 500, 700));

		walls.add(new Line(0, 350, 1000, 350));
	}
	
	public void draw(PApplet drawer) {
	
	
		drawer.clear();
		drawer.background(255);

		player.draw(drawer);
		drawer.stroke(0);

		for (Line object : walls) {
			object.draw(drawer);
		}

		for (Line object : walls) {
			if (player.checkCollisionU(object))
				player.rX();
			

		}
	
	}

	public Tank getPlayer() {
		return player;
	}
	
}
