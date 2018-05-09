package Level;

import processing.core.PApplet;

public class Menu {
	
	public Menu() {
		
	}
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.rect(drawer.width/2-100, 20, 100, 30);
		drawer.rect(drawer.width/2, 20,100,30);
		drawer.fill(0);
		drawer.text("How to Play", drawer.width/2+20	, 40);
		drawer.text("Play",drawer.width/2-60, 40);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
