package edu.fh.kanban.ui.view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardView extends JPanel implements View{

	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BoardView(){
		
		JLabel label = new JLabel("Hallo");
		this.add(label);
		this.setVisible(true);
	}

	

}