package sprites;
import processing.core.PApplet;
import processing.core.PImage;

public interface Obtainable {
	
	
	void setup(PApplet drawer);
	Obtainable getDrop();
	PImage getImg();
	
	
}
