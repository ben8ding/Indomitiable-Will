package screens;

import processing.core.PApplet;

/**
 * 
 * @author Nathaniel
 *
 */
public class WinScreen {
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.background(255);
		drawer.fill(0);
		drawer.textAlign(PApplet.CENTER);
		drawer.textSize(20);
		drawer.text("Congradumalations, you win!", drawer.width/2, 50);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
