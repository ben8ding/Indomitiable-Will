

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	private int state;
	private Rectangle screenRect;


	private ArrayList<Shape> obstacles;

	private ArrayList<Integer> keys;
	
	private ArrayList<PImage> assets;
	

	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		assets = new ArrayList<PImage>();
		state = 1;
		
		
	}

	public void runMe() {
		runSketch();
	}

	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
	}

	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		
		
		
		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;
		background(255,255,255);
		pushMatrix();
		

		scale(ratioX, ratioY);
		rect(0,0,100,100);
		
		
		popMatrix();

	}


	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
}

