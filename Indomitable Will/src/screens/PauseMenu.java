package screens;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author matthewli
 * @version 5-22-18
 */
public class PauseMenu {
	
	public PauseMenu() {
		
	}
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.fill(200);
		drawer.rect(drawer.width/2-150, drawer.height/2 - 200, 300, 50);
		drawer.rect(drawer.width/2-150, drawer.height/2 -140, 300, 50);
		drawer.rect(drawer.width/2-150, drawer.height/2-80, 300, 50);
		drawer.textSize(20);
		drawer.fill(0);
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
		drawer.text("Resume Game", drawer.width/2-67, drawer.height/2-167);
		drawer.text("How to Play", drawer.width/2 -56, drawer.height/2-108);
		drawer.text("Quit to Main Menu", drawer.width/2-85, drawer.height/2 - 46);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
