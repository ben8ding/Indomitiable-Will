
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

	}

	public void keyPressed() {
		if (keyCode == '1') {

		}
		if (keyCode == '2') {

		}
		if (keyCode == '3') {

		}
		if (key == 'w') {
			// System.out.println("cake");
			testLevel.getPlayer().mUp();
		}
		if (key == 'a') {
			testLevel.getPlayer().mLeft();
		}
		if (key == 's') {
			testLevel.getPlayer().mDown();
		}

		if (key == 'd') {
			testLevel.getPlayer().mRight();
		}
		if(key == 'b') {
			System.out.println("fire");
			testLevel.getPlayer().startFiring();
		}
		if (keyCode == PApplet.RIGHT) {
			// System.out.println("cake");
			testLevel.getPlayer().mRight();
		}
		if (keyCode == PApplet.LEFT) {
			testLevel.getPlayer().mLeft();
		}
		if (keyCode == PApplet.DOWN) {
			testLevel.getPlayer().mDown();
		}

		if (keyCode == PApplet.UP) {
			testLevel.getPlayer().mUp();
		}
		
	}

	public void keyReleased() {

		if (keyCode == PApplet.RIGHT) {
			testLevel.getPlayer().sRight();
		}
		if (keyCode == PApplet.LEFT) {
			testLevel.getPlayer().sLeft();
		}

		if (keyCode == PApplet.DOWN) {
			testLevel.getPlayer().sDown();
		}

		if (keyCode == PApplet.UP) {
			testLevel.getPlayer().sUp();
		}
		
		if (key == 'w') {
			// System.out.println("cake");
			testLevel.getPlayer().sUp();
		}
		if (key == 'a') {
			testLevel.getPlayer().sLeft();
		}
		if (key == 's') {
			testLevel.getPlayer().sDown();
		}

		if (key == 'd') {
			testLevel.getPlayer().sRight();
		}
		if(key == 'b') {
			testLevel.getPlayer().stopFiring();
		}
	}
	
	public int getMouseX(){
		return mouseX;
	}

	public int getMouseY(){
		return mouseY;
	}
	
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}

}
