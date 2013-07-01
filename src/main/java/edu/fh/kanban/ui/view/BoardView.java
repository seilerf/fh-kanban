package edu.fh.kanban.ui.view;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class BoardView extends JPanel implements View{

	private JTextField textfield = new JTextField();
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BoardView(){
		 FormLayout formLayout = new FormLayout("p,2dlu,p:grow");
	     CellConstraints cc = new CellConstraints();
	        
	    DefaultFormBuilder builder = new DefaultFormBuilder(formLayout); 
		JLabel label = new JLabel("Hallo");
		add(label);
		add(textfield);
	}

	

}