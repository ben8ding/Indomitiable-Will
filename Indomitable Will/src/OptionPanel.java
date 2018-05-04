import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class OptionPanel extends JPanel implements ActionListener {
	
	Main w;
	
	public OptionPanel(Main w) {
		this.w = w;
		JButton play = new JButton("Play");
		play.addActionListener(this);
		add(play);
		JButton instructions = new JButton("How to Play");
		instructions.addActionListener(this);
		add(instructions);
	}
	
	public void actionPerformed(ActionEvent e) {
		w.changePanel();
	}
	
}