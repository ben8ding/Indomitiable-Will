import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class OptionPanel extends JPanel implements ActionListener {
	
	Main w;
	private JButton instructions;
	public OptionPanel(Main w) {
		this.w = w;
		JButton play = new JButton("Play");
		play.addActionListener(this);
		add(play);
		instructions = new JButton("How to Play");
		instructions.addActionListener(this);
		add(instructions);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == instructions) {
			w.howToPlay();
		} else {
			w.changePanel();
		}
		
	}
	
}