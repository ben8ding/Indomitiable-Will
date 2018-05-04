import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel implements ActionListener {
	Main w;
	
	public InstructionPanel (Main w) {
		this.w = w;
		JButton play = new JButton("Play");
		play.addActionListener(this);
		add(play);
		JButton instructions = new JButton("How to Play");
		instructions.addActionListener(this);
		add(instructions);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		w.howToPlay();
	}

}
