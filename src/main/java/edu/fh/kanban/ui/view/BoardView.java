package edu.fh.kanban.ui.view;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;










import com.itextpdf.text.Font;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.controller.CardController;


public class BoardView extends JPanel implements View{
	
	private CardController cardController;
	private final String columnWidth = "250dlu";//160
	private final String rowHeight = "135dlu";//130
	private final String padding = "4dlu";
		
	//Constructor
	public BoardView(DataManager dm, CardController cardController){
		this.cardController = cardController;
		//Aufbau des Boards mit der Anzahl Spalten, die für die Darstellung notwendig sind;
		this.setLayout(new FormLayout(this.getColSpec(dm.getColumns().size()), getRowSpec(8)));
		
		this.writeColumns(dm.getColumns());
	}
	
	//Methode, die die Spalten in das GUI überträgt
	private void writeColumns(LinkedList<Column> columns) {
		
		int count = 2;
		//Spaltenüberschriften in das GUI schreiben
		for (Iterator<Column> i = columns.iterator(); i.hasNext();){
			Column column = i.next();
			String name = column.getName();
			JLabel columnLabel = new JLabel(name);
			columnLabel.setAlignmentY(CENTER_ALIGNMENT);
			columnLabel.setFont(columnLabel.getFont().deriveFont(columnLabel.getFont().getStyle() | Font.BOLD));
			add(columnLabel, CC.xy(count, 2, CellConstraints.CENTER, CellConstraints.CENTER));
			
			// Karten übertragen
			LinkedList<Card> cardList = column.getCards();
			this.createCards(count, cardList); 
			
			count+=2;
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
			
			for (int i = 0; i < rows; i++)
				rowSpec = rowSpec + rowSpec;
			
			String columns = "4dlu ,15dlu, ";
			rowSpec = "4dlu, 15dlu, " + rowSpec;
			return rowSpec;
	}
	
	// Methode, die die Karten in das Board-GUI schreibt
	private void createCards(int column, LinkedList<Card> cardList){
		
		if (cardList != null) {
			int row = 4;
			for (Iterator<Card> iCard = cardList.iterator(); iCard.hasNext();){
				Card card = iCard.next();
				CardView cardView = new CardView(cardController);
				cardView.setIdTextField(String.valueOf(card.getId()));
				//cardView.setValueComboBox(card.getValue());
				//cardView.setJRadioButton(null, null, null, null);
				cardView.setWorkloadTextField(String.valueOf(card.getWorkload()));
				cardView.setBlockerToggleButton(card.getBlocker());
				cardView.setBackground(card.getBackGround());
				add(cardView, CC.xy(column, row));
				row+=2;
			}
		}
	}
	
	// Methode, die alle GUI-Elemente für das Board-GUI erzeugt
	public void buildBoard() {
		
	}
	
	@Override
	public JComponent getComponent() {
		return this;
	}
}