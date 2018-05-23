package screens;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author matthewli
 *
 */
public class LoseScreen {

	public void setup(PApplet drawer) {

	}

	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.rect(drawer.width / 2 - 200, drawer.height / 2 - 115, 400, 100);
		drawer.fill(0);
		PImage img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "sad.png");
		drawer.image(img, 503, 34);
		drawer.image(img, 442, 554);
		drawer.image(img, 34, 250);
		drawer.image(img, 245, 22);
		drawer.image(img, 240, 442);
		drawer.image(img, 720, 542);
		drawer.image(img, 849, 234);
		drawer.image(img, 10, 32);


		drawer.textAlign(PApplet.CENTER);
		drawer.textSize(50);
		drawer.text("Sucks to be you buddy, you lose!", drawer.width / 2, 200);
		drawer.text("Main Menu", drawer.width / 2, drawer.height / 2 - 45);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
