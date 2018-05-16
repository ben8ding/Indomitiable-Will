package processing;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
import screens.*;
import shapes.Line;
import sprites.Player;
import screens.Menu;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DrawingSurface extends PApplet {

	private Menu menu;
	private PauseMenu pauseMenu;
	//private Level testLevel,level1,level2,level3,level4;
	private int currentLevel;
	private ArrayList<Level> levels;
	private ArrayList<Integer> keys;
	public static final int xSize = 1000;
	public static final int ySize = 700;

	private enum State {
		PAUSED, MENU, GAME, INSTRUCTIONS
	};

	private State state;

	public DrawingSurface() {
		keys = new ArrayList<Integer>();
		levels = new ArrayList<Level>();
		menu = new Menu();
		currentLevel = 0;
	//	testLevel = new Level();
		for(int i = 0; i < 5; i++) {
			Level level = new Level();
			levels.add(level);
		}
		state = State.MENU;
		pauseMenu = new PauseMenu();
	}

	public void settings() {
		size(xSize, ySize);
	}

	public void setup() {
		// background(255);
		for(Level level : levels) {
			level.addObstacle(new Rectangle((int)(Math.random()*1000), (int)(Math.random()*700),(int)(Math.random()*1000), (int)(Math.random()*700)));
			level.addObstacle(new Rectangle((int)(Math.random()*1000), (int)(Math.random()*700),(int)(Math.random()*1000), (int)(Math.random()*700)));
			level.setup(this);
		}
//		testLevel.addObstacle(new Rectangle(500, 0, 20, 350));
//		testLevel.addObstacle(new Rectangle(0, 350, 300, 20));
//		level1.addObstacle(new Rectangle(100,100,350,350));
//		level1.addObstacle(new Rectangle(500,200, 100,302));
//		
	//	testLevel.setup(this);
	}

	public void draw() {
		pushStyle();
		Level current = levels.get(currentLevel);
		if (state == State.MENU) {
			menu.draw(this);
		}
		if (state != State.GAME) {

			if (getMouseX() > width / 2 - 150 && getMouseX() < width / 2 + 150 && getMouseY() > height / 2 + 15
					&& getMouseY() < height / 2 + 50 && mousePressed && state == State.MENU) {

				rect(0, 0, width, height);
				rect(width - 150, height - 50, 100, 30);
				fill(0);
				textSize(20);
				text("Back", width - 120, height - 30);
				textSize(14);
				text("This program is a top-down shooter game where the player represents the character Skal, whose mission is to save his village \nfrom an evil invasion. Skal can pick up multiple weapons, powerups, and other goodies to help aid his fight against the invaders. \nHe also can find a map with his progress. Skal has the ability to fight boss enemies, who have special abilities and are \nstronger than regular enemies.\n\nPress play to play the game. Skal should then enter rooms and defeat enemies to try to complete the game.",
						100, 100);
				state = State.INSTRUCTIONS;
			} else if (getMouseX() > width - 150 && getMouseX() < width - 50 && getMouseY() > height - 50
					&& getMouseY() < height - 20 && mousePressed && state == State.INSTRUCTIONS) {
				state = State.MENU;
				background(255);
				menu.draw(this);
			} else if (getMouseX() > width / 2 - 200 && getMouseX() < width / 2 + 200 && getMouseY() > height / 2 - 115
					&& getMouseY() < height / 2 - 15 && mousePressed && state == State.MENU) {
				state = State.GAME;
			} else if (getMouseX() > width / 2 - 75 && getMouseX() < width / 2 + 75 && getMouseY() > height / 2 - 200
					&& getMouseY() < height / 2 - 150 && state == State.PAUSED && mousePressed) {
				state = State.GAME;
				current.draw(this);
			}
		} else {

			current.draw(this);
			// these booleans track if the player is moving in a certain direction
			boolean down = keys.contains((int) 'S') || keys.contains(DOWN);
			boolean up = keys.contains((int) 'W') || keys.contains(UP);
			boolean left = keys.contains((int) 'A') || keys.contains(LEFT);
			boolean right = keys.contains((int) 'D') || keys.contains(RIGHT);
			if (getMouseX() > width - 20 && getMouseX() < width && getMouseY() > 0 && getMouseY() < 20
					&& state == State.GAME && mousePressed) {
				state = State.PAUSED;
				background(255);
				pauseMenu.draw(this);
			}
			if (keys.contains((int) 'B')) {
				current.getPlayer().startFiring();
			} else {
				current.getPlayer().stopFiring();
			}
			if (up) {
				current.getPlayer().mUp();
			} else if (down) {
				current.getPlayer().mDown();
			} else {
				current.getPlayer().stopY();
			}
			if (left) {
				current.getPlayer().mLeft();
			} else if (right) {
				current.getPlayer().mRight();
			} else {
				current.getPlayer().stopX();
			}
		}
		popStyle();
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
