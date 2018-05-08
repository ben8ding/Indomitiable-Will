package processing;

import java.awt.Dimension;

import javax.swing.JFrame;

import Level.Level;
import Sprites.Player;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PImage;
import shapes.Line;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawingSurface extends PApplet {

	private Level testLevel;
	private ArrayList<Integer> keys;
	public static final int xSize = 1000;
	public static final int ySize = 700;

	public DrawingSurface() {
		keys = new ArrayList<Integer>();
		testLevel = new Level();
	}

	public void settings() {
		size(xSize, ySize);

	}

	public void setup() {
		background(255);

	}

	public void draw() {
		testLevel.draw(this);
		if (keys.contains((int)'W') || keys.contains(UP)){
			testLevel.getPlayer().mUp();
		} else {
			testLevel.getPlayer().sUp();
		}
		if (keys.contains((int)'A') || keys.contains(LEFT)) {
			testLevel.getPlayer().mLeft();
		} else {
			testLevel.getPlayer().sLeft();
		}
		if (keys.contains((int)'S') || keys.contains(DOWN)) {
			testLevel.getPlayer().mDown();
		} else {
			testLevel.getPlayer().sDown();
		}

		if (keys.contains((int)'D') || keys.contains(RIGHT)){
			testLevel.getPlayer().mRight();
		} else {
			testLevel.getPlayer().sRight();
		}
		if(keys.contains((int)'B')) {
			testLevel.getPlayer().startFiring();
		} else {
			testLevel.getPlayer().stopFiring();
		}
	}

	public void keyPressed() {
		if(!keys.contains(keyCode)) {
			keys.add(keyCode);
		}
	}

	public void keyReleased() {
		if(keys.contains(keyCode)) {
			keys.remove(keys.indexOf(keyCode));
		}
	}
	
	public int getMouseX(){
		return mouseX;
	}

	public int getMouseY(){
		return mouseY;
	}
	

}
