package edu.fh.kanban.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.itextpdf.text.Font;
import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.controller.CardController;
import edu.fh.kanban.ui.controller.ColumnController;


public class BoardView extends AbstractView implements View{


	private LinkedList<ColumnView> columnViews = new LinkedList<ColumnView>();
	private BoardController boardController;
	
	private final JPopupMenu contextMenu = new JPopupMenu();
	protected CardController cardToMove = null;

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

			this.showCards(count, columnController.getCardControllers()); 
			
			count+=2;
		}
		
	}
	
	public void createContextMenu(){
		System.out.println("Kontextmenü wird erstellt...");
		JMenu moveMenu = new JMenu("Karte verschieben");
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColumnController columnFrom = null;
				ColumnController columnTo = null;
				
				//Merkt in columnFrom den Ausgangsspaltencontroller
				for (Iterator<ColumnController> colControllers = boardController.getColumnControllerList().iterator(); colControllers.hasNext();){
					ColumnController colController = colControllers.next();
						// SpaltenController ermitteln, zu dem die ausgewählte Karte aktuell gehört
						for (Iterator<CardController> cardControllerIterator = colController.getCardControllers().iterator(); cardControllerIterator.hasNext();){
							if (cardToMove == cardControllerIterator.next())
								columnFrom = colController;
						}
					
					// SpaltenController ermitteln, zu dem die ausgewählte Karte hinzugefügt werden soll
					if (e.getActionCommand().matches(colController.getColumn().getName()))
						columnTo = colController;
						
				}
				
				// Entferne Karte aus der Column
				columnFrom.getCardList().remove(cardToMove.getCard());
				columnFrom.getCardControllers().remove(cardToMove);
				columnFrom.getColumnView().remove(cardToMove.getCardView());
				
				// Karte der Zielspalte hinzufügen
				columnTo.getColumn().addCard(cardToMove.getCard());
				columnTo.addCardController(cardToMove);
				columnTo.getColumnView().add(cardToMove.getCardView());
				// Aktualisierung im Board durchführen
				boardController.getBoard().setChanged();
			}
		};
		//Erzeuge die Spalten im Kontextmenü, füge ActionListener hinzu, ordne Menu die Spalteneinträge zu		
		for (Iterator<Column> colIterator = boardController.getColumnList().iterator(); colIterator.hasNext();){
			JMenuItem colMenuItem = new JMenuItem(colIterator.next().getName());
			colMenuItem.addActionListener(al);
			moveMenu.add(colMenuItem);
		}
		
		//Füge dem BoardView das PopupMenu "moveMenu" hinzu
	
		contextMenu.add(moveMenu);
		
		for (Iterator<ColumnController> colControllers = boardController.getColumnControllerList().iterator(); colControllers.hasNext();){
			ColumnController columnController = colControllers.next();
			
			for (Iterator<CardController> cardControllers = columnController.getCardControllers().iterator(); cardControllers.hasNext();){
				CardController cardController = cardControllers.next();
				CardView cardView = cardController.getCardView();
					
					cardView.addMouseListener(new MouseAdapter() {
						public void mouseReleased(MouseEvent me) {
							if (me.isPopupTrigger())
								contextMenu.show(me.getComponent(), me.getX(), me.getY());
								System.out.println("Component: " + me.getComponent().getName() + 
										"\nX: " + me.getX() + 
										"\nY: " + me.getY());
								CardView cardView = (CardView) me.getComponent();
								cardToMove = cardView.getController();		
						}
			    	});
			}
		}
		
	}
	
	public void updateContextMenu(PropertyChangeEvent e){

		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColumnController columnFrom = null;
				ColumnController columnTo = null;
				
				//QuellController
				for (Iterator<ColumnController> colControllers = boardController.getColumnControllerList().iterator(); colControllers.hasNext();){
					ColumnController colController = colControllers.next();
						// SpaltenController ermitteln, zu dem die ausgewählte Karte aktuell gehört
						for (Iterator<CardController> cardControllerIterator = colController.getCardControllers().iterator(); cardControllerIterator.hasNext();){
							if (cardToMove == cardControllerIterator.next())
								columnFrom = colController;
						}
					
				//ZielController
					if (e.getActionCommand().matches(colController.getColumn().getName()))
						columnTo = colController;
						
				}
				//Entfernen und Einfügen aus/in alter/neue Spalte
				System.out.println("Actionlistener kennt: " + cardToMove.getCard().getHeadline());
				System.out.println("Quellspalte: " + columnFrom.getColumn().getName());
				System.out.println("Zielspalte: " + columnTo.getColumn().getName());
				
				// Entferne Karte aus der Column
				columnFrom.getCardList().remove(cardToMove.getCard());
				columnFrom.getCardControllers().remove(cardToMove);
				columnFrom.getColumnView().remove(cardToMove.getCardView());
				
				// Karte der Zielspalte hinzufügen
				columnTo.getColumn().addCard(cardToMove.getCard());
				columnTo.addCardController(cardToMove);
				columnTo.getColumnView().add(cardToMove.getCardView());
				// Aktualisierung im Board durchführen
				boardController.getBoard().setChanged();
			}
		};
	
		
		for (Iterator<ColumnController> colControllers = boardController.getColumnControllerList().iterator(); colControllers.hasNext();){
			ColumnController columnController = colControllers.next();
			
			for (Iterator<CardController> cardControllers = columnController.getCardControllers().iterator(); cardControllers.hasNext();){
				CardController cardController = cardControllers.next();
				CardView cardView = cardController.getCardView();
					
					cardView.addMouseListener(new MouseAdapter() {
						public void mouseReleased(MouseEvent me) {
							if (me.isPopupTrigger())
								contextMenu.show(me.getComponent(), me.getX(), me.getY());
								System.out.println("Component: " + me.getComponent().getName() + 
										"\nX: " + me.getX() + 
										"\nY: " + me.getY());
								CardView cardView = (CardView) me.getComponent();
								cardToMove = cardView.getController();	
						}
			    	});
			}
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
				Card card = cardController.getCard();
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
			for(ColumnController columnController: boardController.getColumnControllerList()){
				
				for(CardController cardController: columnController.getCardControllers()){
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
			for(ColumnController columnController: boardController.getColumnControllerList()){
				
				for(CardController cardController: columnController.getCardControllers()){
					cardController.getCardView().setAllEnabledForBoard();
				}
			}
	  } catch(IndexOutOfBoundsException e) {
		  e.printStackTrace();
	  }
	}
	
	public void setGUI(){
		getComponent().removeAll();
		updateUI();
	}
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public void updateGUI(){
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		
		switch (event.getPropertyName()) {
			case BoardController.BOARDCHANGED_PROPERTY: {
				System.out.println("Boardview wird akualisiert\n");
				this.removeAll();
				this.initComponents();
				this.updateUI();
	            break;
			}
			case BoardController.CARDADDED_PROPERTY:{
				this.updateContextMenu(event);
			}
			
		}
	}
}