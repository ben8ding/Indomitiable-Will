package screens;

import processing.core.PApplet;

/**
 * 
 * @author Matthew
 *
 */
public class WinScreen {
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.background(255);
		drawer.rect(drawer.width/2-200, drawer.height/2-115, 400, 100);
		drawer.fill(0);
		drawer.textAlign(PApplet.CENTER);
		drawer.textSize(50);
		drawer.text("Congradumalations, you win!", drawer.width/2, 200);
		drawer.text("Main Menu",drawer.width/2, drawer.height/2-45);

		drawer.popMatrix();
		drawer.popStyle();
	}
}
