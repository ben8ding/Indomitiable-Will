package screens;

import processing.core.PApplet;

/**
 * 
 * @author Matthew
 *
 */
public class LoseScreen {
	
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.rect(drawer.width/2-200, drawer.height/2-115, 400, 100);
		drawer.fill(0);
		drawer.textAlign(PApplet.CENTER);
		drawer.textSize(50);
		drawer.text("Sucks to be you buddy, you lose!", drawer.width/2, 200);
		drawer.text("Main Menu",drawer.width/2, drawer.height/2-45);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
