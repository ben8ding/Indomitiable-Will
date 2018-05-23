package Pickups;
import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author Nathaniel
 * @version 5-12-18
 */
public interface Obtainable {
	
	Obtainable getDrop();
	PImage getImg();
	void setup(PApplet drawer);
	
}
