
import javax.swing.*;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;


public class Main {

	private JFrame window;

	
	private DrawingSurface panel2;
	
	private PSurfaceAWT.SmoothCanvas processingCanvas;
	
	public Main() {
		panel2 = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, panel2);
		PSurfaceAWT surf = (PSurfaceAWT) panel2.getSurface();
		processingCanvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		window = (JFrame)processingCanvas.getFrame();

		window.setBounds(0,0,800, 600);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

	    
	    window.getContentPane().removeAll();
	    
		
	}
	

	public static void main(String[] args)
	{
		Main m = new Main();
	}
	
}

