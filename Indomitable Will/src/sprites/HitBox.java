package sprites;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import shapes.Line;


public class HitBox extends Basic {
	private Line UYBox;
	private Line DYBox;
	private Line RXBox;
	private Line LXBox;
	
	
	public HitBox(int set)
	{
		super(0,0,set);
		hitBox();
	}
	public HitBox(Basic other)
	{
		super(0,0,other.getSize());
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
		UYBox = new Line(xLoc+size,yLoc-size,xLoc-size,yLoc-size);
		DYBox = new Line(xLoc+size,yLoc+size,xLoc-size,yLoc+size);
		RXBox = new Line(xLoc+size,yLoc-size,xLoc+size,yLoc+size);
		LXBox = new Line(xLoc-size,yLoc+size,xLoc-size,yLoc-size);
	}
	
	public int checkCollision(Line other)
	{
		if (this.checkCollisionU(other))
			return 1;
		if (this.checkCollisionR(other))
			return 2;
		if (this.checkCollisionD(other))
			return 3;
		if (this.checkCollisionL(other))
			return 4;
		return 0;
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
