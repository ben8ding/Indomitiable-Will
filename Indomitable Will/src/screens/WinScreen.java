package screens;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * 
 * @author matthewli, Nathaniel
 * @version 5-22-18
 */
public class WinScreen {


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
		PImage chicken = drawer.loadImage("sprites" + System.getProperty("file.separator") + "Chicken.png");
		drawer.image(chicken, 0, 0);
		drawer.image(chicken, 120, 634);
		drawer.image(chicken, 456, 23);
		drawer.image(chicken, 503, 568);
		drawer.image(chicken, 593, 45);
		drawer.image(chicken, 934, 340);
		drawer.image(chicken, 58, 280);
		drawer.image(chicken, 200, 343);
		drawer.image(chicken, 870, 243);
	

		drawer.popMatrix();
		drawer.popStyle();
	}
}
