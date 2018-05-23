package processing;

import java.awt.Dimension;

import javax.swing.JFrame;

import Pickups.Capsule;
import Pickups.Pistol;
import Pickups.Rifle;
import Pickups.Shotgun;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
import screens.*;
import sprites.Enemy;
import sprites.Player;
import screens.Menu;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * @author Matthew,Nathaniel,Ben
 * @version 5-22/18
 */
public class DrawingSurface extends PApplet {
	private WinScreen ws;
	private Menu menu;
	private PauseMenu pauseMenu;
	private Instructions instructions;
	private LoseScreen lose;
	private int currentLevel;
	private long startTime;
	private State previousState;
	private ArrayList<Level> levels;
	private ArrayList<Integer> keys;
	public static final int xSize = 1200;
	public static final int ySize = 800;
	private long waitTime;
	private enum State {
		PAUSED, MENU, GAME, INSTRUCTIONS, WIN, LOSE, STARTUP 
	};

	private State state;

	public DrawingSurface() {
		lose = new LoseScreen();
		ws = new WinScreen();
		state = previousState = State.STARTUP;
		instructions = new Instructions();
		keys = new ArrayList<Integer>();
		levels = new ArrayList<Level>();
		menu = new Menu();
		currentLevel = 0;
		// testLevel = new Level();
		for (int i = 0; i < 5; i++) {
			Level level = new Level();
			levels.add(level);
		}
		pauseMenu = new PauseMenu();
		startTime = 0;
	}

	public void settings() {
		size(xSize, ySize);
	}

	public void setup() {
		// background(255);
		ArrayList<Capsule> drops = new ArrayList<Capsule>();


		levels.get(0).addObstacle(new Rectangle((int) (700), (int) (350), 300, 50));
		levels.get(0).addObstacle(new Rectangle((int) (150), (int) (530), 300, 50));
		levels.get(0).addObstacle(new Rectangle(500, (int) 80, 50, 300));
		levels.get(0).addObstacle(new Rectangle(175, (int) (200), 50, 300));
		levels.get(0).addEnemy(new Enemy(600,600));

		drops.add(new Capsule(50, 300, new Pistol()));
		drops.add(new Capsule(50, 250, new Shotgun()));
		drops.add(new Capsule(50, 200, new Rifle()));

		levels.get(0).setDrops(drops);

		drops = new ArrayList<Capsule>();

		levels.get(1).addObstacle(new Rectangle((int) (300), (int) (630), 300, 50));
		levels.get(1).addObstacle(new Rectangle((int) (450), (int) (200), 300, 50));
		levels.get(1).addObstacle(new Rectangle(900, (int) 100, 50, 300));
		levels.get(1).addObstacle(new Rectangle(530, (int) (350), 50, 300));
		levels.get(1).addEnemy(new Enemy(700,600));
		levels.get(1).setDrops(drops);

		drops = new ArrayList<Capsule>();
		levels.get(2).addObstacle(new Rectangle((int) (200), (int) (560), 300, 50));
		levels.get(2).addObstacle(new Rectangle((int) (520), (int) (420), 300, 50));
		levels.get(2).addObstacle(new Rectangle(100, (int) 230, 50, 300));
		levels.get(2).addObstacle(new Rectangle(400, (int) (70), 50, 300));
		levels.get(2).addEnemy(new Enemy(400,450));
		levels.get(2).setDrops(drops);
		drops = new ArrayList<Capsule>();

		levels.get(3).addObstacle(new Rectangle(100, 500, 50, 100));
		levels.get(3).addObstacle(new Rectangle(200, 400, 50, 120));
		levels.get(3).addObstacle(new Rectangle(300, 300, 50, 120));
		levels.get(3).addObstacle(new Rectangle(400, 200, 50, 120));
		levels.get(3).addObstacle(new Rectangle(500, 100, 50, 120));
		levels.get(3).addObstacle(new Rectangle(600, 10, 50, 120));
		levels.get(3).addEnemy(new Enemy(40,40));
		levels.get(3).setDrops(drops);
		drops = new ArrayList<Capsule>();
		drops.add(new Capsule(600, 300, new Rifle()));

		levels.get(4).addObstacle(new Rectangle(750, 500, 50, 120));
		levels.get(4).addObstacle(new Rectangle(250, 0, 50, 120));
		levels.get(4).addObstacle(new Rectangle(350, 100, 50, 120));
		levels.get(4).addObstacle(new Rectangle(450, 200, 50, 120));
		levels.get(4).addObstacle(new Rectangle(550, 300, 50, 120));
		levels.get(4).addObstacle(new Rectangle(650, 400, 50, 120));
		levels.get(4).addEnemy(new Enemy(40,40));

		levels.get(4).setDrops(drops);

		for (Level level : levels) {
			level.setup(this);
		}
	}

	public void draw() {
		pushStyle();
		// draw menu on first play
		if (state == State.STARTUP) {
			menu.draw(this);
			previousState = state;
			state = State.MENU;
		}
		Level current = levels.get(currentLevel);
		current.setID(currentLevel);

		System.out.printf("Player health: %d\n", levels.get(currentLevel).getPlayer().getHp());

		// if player dies, then send to lose menu
		if (levels.get(currentLevel).getPlayer().getHp() <= 0 && state == State.GAME) {
			System.out.println("we at the lose menu");
			state = State.LOSE;
			background(255);
			lose.draw(this);
		}

		// if player clears a level
		if (current.isCleared() && state == State.GAME) {
			if (currentLevel != 4) {
				System.out.println(currentLevel);
				current = levels.get(currentLevel + 1);
				current = new Level(current, levels.get(currentLevel).getPlayer());
				currentLevel++;
				levels.set(currentLevel, current);
			} else {
				state = State.WIN;
				clear();
				ws.draw(this);
			}
		}
		// stuff that happens if player is not in game
		if (state != State.GAME) {
			if (getMouseX() > width / 2 - 150 && getMouseX() < width / 2 + 150 && getMouseY() > height / 2 + 15
					&& getMouseY() < height / 2 + 50 && mousePressed && state == State.MENU) {
				previousState = state;
				state = State.INSTRUCTIONS;
				instructions.draw(this);
			} else if (getMouseX() > width - 150 && getMouseX() < width - 50 && getMouseY() > height - 50
					&& getMouseY() < height - 20 && mousePressed && state == State.INSTRUCTIONS) {// if at instructions
																									// and want to click
																									// back

				background(255);
				if (previousState == State.PAUSED)// goes back to pause
					pauseMenu.draw(this);
				else// go back to main menu
					menu.draw(this);
				state = previousState;
				previousState = State.INSTRUCTIONS;
			} else if (getMouseX() > width / 2 - 200 && getMouseX() < width / 2 + 200 && getMouseY() > height / 2 - 115
					&& getMouseY() < height / 2 - 15 && mousePressed && state == State.MENU
					&& System.nanoTime() - waitTime >= 100000000) {// if at menu and want to play game
				if (previousState == State.LOSE || previousState == State.WIN||previousState ==State.PAUSED) {
					levels = null;
					levels = new ArrayList<>();
					for (int i = 0; i < 5; i++) {
						Level level = new Level();
						levels.add(level);
					}
					this.setup();
				}
				previousState = state;
				state = State.GAME; 
				levels.get(0).draw(this);
				// System.out.println("hallo from the far east side");
			} else if (getMouseX() > width / 2 - 150 && getMouseX() < width / 2 + 150 && getMouseY() > height / 2 - 200
					&& getMouseY() < height / 2 - 150 && state == State.PAUSED && mousePressed) {// if paused and wants
																									// to continue
																									// playing duh game
				previousState = state;
				state = State.GAME;
				current.draw(this);
			} else if (getMouseX() > width / 2 - 150 && getMouseX() < width / 2 + 150 && getMouseY() > height / 2 - 140
					&& getMouseY() < height / 2 - 90 && state == State.PAUSED && mousePressed) {// if paused and wants
																								// to see instructions
				previousState = state;
				state = State.INSTRUCTIONS;
				instructions.draw(this);
			} else if (getMouseX() > width / 2 - 150 && getMouseX() < width / 2 + 150 && getMouseY() > height / 2 - 80
					&& getMouseY() < height / 2 - 30 && state == State.PAUSED && mousePressed) { // if pause menu and
																									// presses return to
																									// main menu
				previousState = state;
				state = State.MENU;
				background(255);
				menu.draw(this);
				currentLevel = 0;
				waitTime = System.nanoTime();
			} // if player is at death menu and clicks play again
			if ((state == State.LOSE ||state == State.WIN)&& getMouseX() > width / 2 - 200 && getMouseX() < width + 200
					&& getMouseY() > height / 2 - 115 && getMouseY() < height / 2 - 15 && mousePressed) {
				previousState = state;
				state = State.MENU;
				background(255);
				menu.draw(this);
				
				currentLevel = 0;
				waitTime = System.nanoTime();
			}
		} else {

			current.draw(this);
			// these booleans track if the player is moving in a certain direction
			boolean down = keys.contains((int) 'S') || keys.contains(DOWN);
			boolean up = keys.contains((int) 'W') || keys.contains(UP);
			boolean left = keys.contains((int) 'A') || keys.contains(LEFT);
			boolean right = keys.contains((int) 'D') || keys.contains(RIGHT);
			if (getMouseX() > width - 35 && getMouseX() < width - 10 && getMouseY() > 0 && getMouseY() < 30
					&& state == State.GAME && mousePressed) {
				previousState = state;
				state = State.PAUSED;
				background(255);
				pauseMenu.draw(this);
			}
			if (keys.contains((int) 'B') && startTime == 0) {
				// System.out.println("hai");
				startTime = System.nanoTime();
				current.getPlayer().startFiring();
			} else if (System.nanoTime() >= startTime + 100000000 * current.getPlayer().getROF()) {
				startTime = 0;
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
