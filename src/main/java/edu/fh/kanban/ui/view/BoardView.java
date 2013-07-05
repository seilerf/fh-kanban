package edu.fh.kanban.ui.view;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;







import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.domain.Column;


public class BoardView extends JPanel implements View{
	
	
	private final String columnWidth = "90dlu";
	private final String rowHeight = "13dlu";
	private final String padding = "4dlu";
		
	//Constructor
	public BoardView(DataManager dm){
		
		//Aufbau des Boards mit der Anzahl Spalten, die für die Darstellung notwendig sind;
		this.setLayout(new FormLayout(this.getColSpec(dm.getCols().size()), getRowSpec(8)));
		
		this.writeColumns(dm.getCols());
	}
	
	//Methode, die die Spalten in das GUI überträgt
	private void writeColumns(LinkedList<Column> columns) {
		
		int count = 1;
		//Spaltenüberschriften in das GUI schreiben
		for (Iterator<Column> i = columns.iterator(); i.hasNext();){
			
			add(new JLabel(i.next().getName()), CC.xy((2*count), 2));
			count++;
		}
		
	}
	
	// Methode, die die Spaltenspezifikation für den Formbuilder erstellt und ausgibt
	private String getColSpec(int columns) {
		String colSpec = padding + ", " + columnWidth + ", ";
		
		int i = 0;
		for (i = 0; i < columns; i++){
			// Zusammenfügung des Strings für die Spezifikation der Spalten im FormLayoutManager
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