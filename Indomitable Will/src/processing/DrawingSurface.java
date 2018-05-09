package processing;

import java.awt.Dimension;

import javax.swing.JFrame;

import Level.*;
import Level.Menu;
import Sprites.Player;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DrawingSurface extends PApplet {

	private boolean levelStart;
	private Menu menu;
	private Level testLevel;
	private ArrayList<Integer> keys;
	public static final int xSize = 1000;
	public static final int ySize = 700;

	public DrawingSurface() {
		keys = new ArrayList<Integer>();
		testLevel = new Level();
		menu = new Menu();
		levelStart = true;
	}

	public void settings() {
		size(xSize, ySize);

	}

	public void setup() {
		background(255);
		testLevel.setup(this);
	}

	public void draw() {
		if(levelStart == false) {
			menu.draw(this);
			if(getMouseX() > width/2&& getMouseX() < width/2 + 200 &&getMouseY() > 20 && getMouseY()<200) {
				
			}
		}
		else {
			testLevel.draw(this);
			boolean down = keys.contains((int) 'S') || keys.contains(DOWN);
			boolean up = keys.contains((int) 'W') || keys.contains(UP);
			boolean left = keys.contains((int) 'A') || keys.contains(LEFT);
			boolean right = keys.contains((int) 'D') || keys.contains(RIGHT);
			// these booleans track if the player is moving in a certain direction
			if (keys.contains((int) 'B')) {
				testLevel.getPlayer().startFiring();
			} else {
				testLevel.getPlayer().stopFiring();
			}
			if (up) {
				testLevel.getPlayer().mUp();
			} else if (down) {
				testLevel.getPlayer().mDown();
			} else {
				testLevel.getPlayer().stopY();
			}
			if (left) {
				testLevel.getPlayer().mLeft();
			} else if (right) {
				testLevel.getPlayer().mRight();
			} else {
				testLevel.getPlayer().stopX();
			}
		}
	}

	public void keyPressed() {
		if (!keys.contains(keyCode)) {
			keys.add(keyCode);
		}
	}

	public void keyReleased() {
		if (keys.contains(keyCode))
			keys.remove(keys.indexOf(keyCode));
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

}
