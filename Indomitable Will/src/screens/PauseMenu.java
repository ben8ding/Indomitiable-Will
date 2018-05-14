package screens;

import processing.core.PApplet;

public class PauseMenu {
	
	public PauseMenu() {
		
	}
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.fill(200);
		drawer.rect(drawer.width/2-75, drawer.height/2 - 200, 150, 50);
		drawer.textSize(20);
		drawer.fill(0);
		drawer.text("Resume Game", drawer.width/2-67, drawer.height/2-170);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
