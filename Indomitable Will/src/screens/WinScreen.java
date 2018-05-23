package screens;

import processing.core.PApplet;
import processing.core.PImage;

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
		drawer.rect(drawer.width / 2 - 200, drawer.height / 2 - 115, 400, 100);
		drawer.fill(0);
		drawer.textAlign(PApplet.CENTER);
		drawer.textSize(50);
		drawer.text("Congradumalations, you win!", drawer.width / 2, 200);
		drawer.text("Main Menu", drawer.width / 2, drawer.height / 2 - 45);
		PImage img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "Chicken.png");
		drawer.image(img, 0, 0);
		drawer.image(img, 120, 634);
		drawer.image(img, 456, 23);
		drawer.image(img, 503, 568);
		drawer.image(img, 593, 45);
		drawer.image(img, 934, 340);
		drawer.image(img, 58, 280);
		drawer.image(img, 200, 343);
		drawer.image(img, 870, 243);
	

		drawer.popMatrix();
		drawer.popStyle();
	}
}
