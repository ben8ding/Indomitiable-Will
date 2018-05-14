package sprites;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Pickup{
	
	protected PImage img;
	
	public Pickup(PImage x)
	{
		img = x;
	}
	
	public void setup(PApplet drawer) {
		img = drawer.loadImage("sprites" + System.getProperty("file.separator") + "player.png");
	}
	
	public PImage getImg() {
		return img;
	}
	
	
}
