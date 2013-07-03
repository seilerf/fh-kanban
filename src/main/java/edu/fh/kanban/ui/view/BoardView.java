package edu.fh.kanban.ui.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class BoardView extends JPanel implements View{

	
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BoardView(){
		JButton button = new JButton();
		button.addChangeListener(null);
		add(button);
	
	}

	

}