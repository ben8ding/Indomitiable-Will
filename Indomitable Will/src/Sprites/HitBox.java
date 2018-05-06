package Sprites;
import processing.core.PApplet;
import shapes.Line;


public class HitBox extends Basic{

	private Line UYBox;
	private Line DYBox;
	private Line RXBox;
	private Line LXBox;
	
	
	public HitBox()
	{
		size = 25;
		hitBox();
	}
	
	public HitBox(int set)
	{
		size = set;
		hitBox();
	}
	
	public HitBox(Basic other)
	{
		size = other.getSize();
		hitBox();
	}
	
	public void draw(PApplet drawer) {
		
		UYBox.draw(drawer);
		DYBox.draw(drawer);
		RXBox.draw(drawer);
		LXBox.draw(drawer);
		
	}
	
	public void refreshLoc(Basic other)
	{
		this.xLoc = other.xLoc;
		this.yLoc = other.yLoc;
		hitBox();
	}

	public void hitBox()
	{
		UYBox = new Line(xLoc+25,yLoc-25,xLoc-25,yLoc-25);
		DYBox = new Line(xLoc+25,yLoc+25,xLoc-25,yLoc+25);
		RXBox = new Line(xLoc+25,yLoc-25,xLoc+25,yLoc+25);
		LXBox = new Line(xLoc-25,yLoc+25,xLoc-25,yLoc-25);
	}
	
	public boolean checkCollision(Line other)
	{
		
		
		
		if (this.checkCollisionU(other))
			return true;
		if (this.checkCollisionD(other))
			return true;
		if (this.checkCollisionL(other))
			return true;
		if (this.checkCollisionR(other))
			return true;
		
		
		
		
		return false;
		
	}
	
	
	
	public boolean checkCollisionU(Line other)
	{
		if (other.intersects(UYBox))
			return true;
		return false;
	}
	
	public boolean checkCollisionD(Line other)
	{
		if (other.intersects(DYBox))
			return true;
		return false;
	}
	
	public boolean checkCollisionL(Line other)
	{
		if (other.intersects(LXBox))
			return true;
		return false;
	}
	
	public boolean checkCollisionR(Line other)
	{
		if (other.intersects(RXBox))
			return true;
		return false;
	}
	
	public Line getUYBox()
	{
		return UYBox;
	}
	
	public Line getDYBox()
	{
		return DYBox;
	}
	
	public Line getRXBox()
	{
		return RXBox;
	}
	
	public Line getLXBox()
	{
		return LXBox;
	}
	
	
}
