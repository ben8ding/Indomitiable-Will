package screens;

import processing.core.PApplet;

public class Instructions {
	
	public Instructions() {
		
	}
	public void setup(PApplet drawer) {
		
	}
	public void draw(PApplet drawer) {
		
		drawer.pushMatrix();
		drawer.pushStyle();
		drawer.rect(0, 0, drawer.width, drawer.height);
		drawer.rect(drawer.width - 150, drawer.height - 50, 100, 30);
		drawer.fill(0);
		drawer.textSize(20);
		drawer.text("Back", drawer.width - 120, drawer.height - 30);
		drawer.textSize(14);
		drawer. text("This program is a top-down shooter game where the player represents the character Skal, whose mission is to save his village \nfrom an evil invasion. Skal can pick up multiple weapons, powerups, and other goodies to help aid his fight against the invaders. \nHe also can find a map with his progress. Skal has the ability to fight boss enemies, who have special abilities and are \nstronger than regular enemies.\n\nPress play to play the game. Skal should then enter rooms and defeat enemies to try to complete the game.",
				100, 100);
		drawer.popMatrix();
		drawer.popStyle();
	}
}
