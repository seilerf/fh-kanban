package edu.fh.kanban.ui.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BacklogView extends JPanel implements View{

	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BacklogView(){
		
		JLabel label = new JLabel("Hallo2");
		this.add(label);
		this.setVisible(true);
	}

	

}
