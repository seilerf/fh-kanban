package edu.fh.kanban.ui.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BoardView implements View{

	@Override
	public JComponent getComponent() {
		return new JPanel();
	}

	

}