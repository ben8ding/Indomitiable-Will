
import javax.swing.*;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;


public class Main {

	private JFrame window;
	
	private JPanel cardPanel;
	
	private OptionPanel panel1;    
	private InstructionPanel instructions;
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

		cardPanel = new JPanel();
	    CardLayout cl = new CardLayout();
	    cardPanel.setLayout(cl);
	    
	    window.getContentPane().removeAll();
	    
		panel1 = new OptionPanel(this);    
		instructions = new InstructionPanel(this);
	    panel2 = new DrawingSurface();
	    
	    cardPanel.add(panel1,"1");
	    
	    cardPanel.add(processingCanvas,"2");
	    cardPanel.add(instructions, "3");
	    window.setLayout(new BorderLayout());
	    
	    window.add(cardPanel);
	    window.revalidate();
	}
	

	public static void main(String[] args)
	{
		Main m = new Main();
	}
	public void howToPlay() {
		((CardLayout)cardPanel.getLayout()).last(cardPanel);
	}
	public void changePanel() {
		((CardLayout)cardPanel.getLayout()).next(cardPanel);
		processingCanvas.requestFocus();
	}
  
	
}

