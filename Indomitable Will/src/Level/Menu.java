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
		drawer.rect(drawer.width/2, 20,200,200);
		drawer.fill(0);
		drawer.text("How to Play", drawer.width/2, 30);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
