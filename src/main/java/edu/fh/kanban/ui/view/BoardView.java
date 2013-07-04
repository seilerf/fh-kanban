package edu.fh.kanban.ui.view;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.jgoodies.forms.factories.CC;

import com.jgoodies.forms.layout.FormLayout;


public class BoardView extends JPanel implements View{
	
	
	private final String columnWidth = "60dlu";
	private final String rowHeight = "13dlu";
	private final String padding = "4dlu";
	
	private String colSpec;
	private String rowSpec;
	
	//Constructor
	public BoardView(){
		
		//Aufbau des Boards mit maximal 5 möglichen Spalten
		this.setLayout(new FormLayout(this.getColSpec(5), getRowSpec(8)));
		
		this.add(new JLabel("Spalte 1"), CC.xy(2, 2));
		this.add(new JLabel("Spalte 2"), CC.xy(4, 2));
		this.add(new JLabel("Spalte 3"), CC.xy(6, 2));
		this.add(new JLabel("Spalte 4"), CC.xy(8, 2));
		this.add(new JLabel("Spalte 5"), CC.xy(10, 2));
		
	}
	
	// Methode, die die Spaltenspezifikation für den Formbuilder erstellt und ausgibt
	private String getColSpec(int columns) {
		String colSpec = padding + ", " + columnWidth + ", ";
		
		int i = 0;
		for (i = 0; i < columns; i++){
			colSpec = colSpec + colSpec;
		}
		return colSpec;
	}
	
	// Methode, die die Zeilenspezifikation für den Formbuilder erstellt und ausgibt
	private String getRowSpec(int rows) {
			String rowSpec = padding + ", " + rowHeight + ", ";
			
			int i = 0;
			for (i = 0; i < rows; i++){
				rowSpec = rowSpec + rowSpec;
			}
			return rowSpec;
	}
	
	// Methode, die alle GUI-Elemente für das Board-GUI erzeugt
	
	public void buildBoard() {
		
	}
	
	@Override
	public JComponent getComponent() {
		return this;
	}
}