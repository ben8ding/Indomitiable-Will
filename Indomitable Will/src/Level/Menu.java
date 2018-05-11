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
		drawer.fill(220);
		drawer.rect(drawer.width/2-200, drawer.height/2-115, 400, 100);
		drawer.rect(drawer.width/2-150, drawer.height/2+15,300,50);
		drawer.fill(0);
		drawer.textSize(30);
		
		drawer.text("How to Play", drawer.width/2-85, drawer.height/2 + 50);
		drawer.textSize(60);
		drawer.text("Play",drawer.width/2-55, drawer.height/2-45);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
