package edu.fh.kanban.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;





import com.itextpdf.text.Font;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.controller.CardController;
import edu.fh.kanban.ui.controller.ColumnController;


public class BoardView extends AbstractView implements View{


	private LinkedList<ColumnView> columnViews = new LinkedList<ColumnView>();
	
	private boolean changed;
	private BoardController boardController;

	
	//Constructor
	public BoardView(BoardController boardController){
		this.boardController = boardController;
		
		initComponents();
		System.out.println("BoardView()");
	}
	
	private void initComponents() {
		//Aufbau des Boards mit der Anzahl Spalten, die für die Darstellung notwendig sind;
		System.out.println("initComponents()");
		
		
		columnViews = boardController.createColumnViews(); 
		System.out.println(boardController.getColumnList().size());
		this.setLayout(new FormLayout(
				boardController.getColSpec(boardController.getColumnList().size()),
				
				boardController.getRowSpec(8)));
		
		this.showColumns(boardController.getColumnControllerList());
	}
	
	//Methode, die die Spalten in das GUI überträgt
	private void showColumns(LinkedList<ColumnController> columnControllerList) {
		System.out.println("showColumns()");
		int count = 2;
		//Spaltenüberschriften in das GUI schreiben
		for (Iterator<ColumnController> i = columnControllerList.iterator(); i.hasNext();){
			ColumnController columnController = i.next();
			String name = columnController.getColumn().getName();
			JLabel columnLabel = new JLabel(name);
			columnLabel.setAlignmentY(CENTER_ALIGNMENT);
			columnLabel.setFont(columnLabel.getFont().deriveFont(columnLabel.getFont().getStyle() | Font.BOLD));
			add(columnLabel, CC.xy(count, 2, CellConstraints.CENTER, CellConstraints.CENTER));
			
		
			
			this.showCards(count, columnController.getCardControllerList()); 
			
			count+=2;
		}
		
	}

	
	// Methode, die die Karten in das Board-GUI schreibt
	private void showCards(int column, LinkedList<CardController> cardControllerList){
		int i=0;
		System.out.println("showCards()");
		if (cardControllerList != null) {
			int row = 4;
			for (Iterator<CardController> iCardController = cardControllerList.iterator(); iCardController.hasNext();){
				
				CardController cardController = iCardController.next();
				Card card = cardController.getCard();//funktioniert richtig
				System.out.println("showCards: ID:" +card.getId() + " Value:" + card.getValue());
				
				CardView cardView = cardController.getCardView();
				
				
				cardView.setIdTextField(String.valueOf(card.getId()));
				cardView.setCardTitel(card.getHeadline());
				cardView.setDescriptionTextPane(card.getDescription());
				cardView.setValueComboBox(card.getValue());
				cardView.setJPanelColorForCreatingAExistingCard(card.getValue());
				
				cardView.setJRadioButton(card.getCreated(), card.getStarted(), card.getStarted());
				cardView.setSizeTextField(String.valueOf(card.getSize()));
				cardView.setBlockerToggleButton(card.getBlocker());
				
				cardView.setAllDisabledForBoard();
				
				System.out.println("in der BoardView:" + cardView.getBackground());
			
				add(cardView, CC.xy(column, row));
				row+=2;i+=1;
			}
		}
	}
	

	/**
	 * Alle auf dem Board dargestellten Karten werden auf Disabled gesetzt.
	 */
	public void setAllCardViewsDisabled() {
		try {
			BoardController currentBoardController;
			for(ColumnController columnController: boardController.getColumnControllerList()){
				
				for(CardController cardController: columnController.getCardControllerList()){
					cardController.getCardView().setAllDisabledForBoard();
				}
			}
	  } catch(IndexOutOfBoundsException e) {
		  e.printStackTrace();
	  }
	}
	
	/**
	 * Alle auf dme Board dargestellten Karten werden auf Enable
	 */
	public void setAllCardViewsEnabled() {
		try {
			BoardController currentBoardController;
			for(ColumnController columnController: boardController.getColumnControllerList()){
				
				for(CardController cardController: columnController.getCardControllerList()){
					cardController.getCardView().setAllEnabledForBoard();
				}
			}
	  } catch(IndexOutOfBoundsException e) {
		  e.printStackTrace();
	  }
	}
	
	public void setGUI(){
		getComponent().removeAll();
		//this.showColumns(board.getColumnList());
		updateUI();
		System.out.println("Ich wurde geändert!");
	}
	
	// Methode, die alle GUI-Elemente für das Board-GUI erzeugt
	public void buildBoard() {
		
	}
	
	@Override
	public JComponent getComponent() {
		return this;
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		System.out.println("Boardview wird akualisiert\n");
		updateUI();
	}
}