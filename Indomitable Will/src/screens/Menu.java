package screens;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author matthewli
 * @version 5-22-18
 *
 */
public class Menu {
	
	public Menu() {
		
	}
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.fill(220);
		drawer.rect(drawer.width/2-200, drawer.height/2-115, 400, 100);
		drawer.rect(drawer.width/2-150, drawer.height/2+15,300,50);
		drawer.fill(0);
		drawer.textSize(30);
		PImage img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "Chicken.png");
		drawer.image(img, 0, 0);
		drawer.image(img, 133, 134);
		drawer.image(img, 600, 23);
		drawer.image(img, 234, 568);
		drawer.image(img, 593, 599);
		drawer.image(img, 934, 554);
		drawer.image(img, 345, 100);
		drawer.image(img, 200, 343);
		drawer.image(img, 870, 243);

		drawer.text("How to Play", drawer.width/2-85, drawer.height/2 + 50);
		drawer.textSize(60);
		drawer.text("Play",drawer.width/2-55, drawer.height/2-45);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
