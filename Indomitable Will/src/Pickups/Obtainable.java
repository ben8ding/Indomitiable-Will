package Pickups;
import processing.core.PApplet;
import processing.core.PImage;

public interface Obtainable {
	
	Obtainable getDrop();
	PImage getImg();
	void draw(PApplet drawer);
	void setup(PApplet drawer);
	
}
