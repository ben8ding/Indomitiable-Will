import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionPanel extends JPanel implements ActionListener {
	Main w;
	JButton menu;
	public InstructionPanel (Main w) {
		this.w = w;
		menu = new JButton("Return");
		menu.addActionListener(this);
		add(menu);
		JLabel label = new JLabel("\nWelcome!");
		add(label);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == menu) {
			w.titleScreen();
		}
		
	}

}
