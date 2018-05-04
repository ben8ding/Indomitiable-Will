import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel implements ActionListener {
	Main w;
	
	public InstructionPanel (Main w) {
		this.w = w;
		JButton instructions = new JButton("Return");
		instructions.addActionListener(this);
		add(instructions);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		w.howToPlay();
	}

}
