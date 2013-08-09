package edu.fh.kanban.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.jgoodies.forms.layout.LayoutMap;

import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.view.AbstractView;
import edu.fh.kanban.ui.view.BoardView;
import edu.fh.kanban.ui.view.CardView;
import edu.fh.kanban.ui.view.ColumnView;
import edu.fh.kanban.ui.view.View;

public class BoardController extends AbstractController{

	
	private final String columnWidth = "270dlu";//160
	private final String rowHeight = "135dlu";//130
	private final String padding = "4dlu";
	
	public static final String BOARDCHANGED_PROPERTY = "BoardChanged";
	
	private final JPopupMenu contextMenu = new JPopupMenu();
	protected CardView cardViewToMove = null;
	protected Column columnToMoveFrom = null;
	
	private static LinkedList<ColumnController> columnControllers = new LinkedList<>();
	private LinkedList<ColumnView> columnViews = new LinkedList<>();
	/*!!!Achtung!!!
	 * bevor der Controller aufgerufen wird müssen ihm seine
	 * Modelle hinzugefügt werden, also in diesem Fall die Boards
	 * Ansonsten funktioniert der Controller nicht, da er versucht über das
	 * Board die Spalten einzulesen und entsprechende 
	 * SpaltenController zu erstellen, die dann direkt den Column-Modellen
	 * zugeordnet werden
	 */
	public BoardController(){
		
	}
	public Board getBoard(){
		Board board;
		board = (Board)models.get(0);
		return board;
	}
	public void createContextMenu(){
		System.out.println("Kontextmenü wird erstellt...");
		JMenu moveMenu = new JMenu("Karte verschieben");
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Iterator<ColumnController> colControllers = getColumnControllerList().iterator(); colControllers.hasNext();){
					ColumnController colController = colControllers.next();
					
					if (e.getActionCommand().matches(colController.getColumn().getName())){
						// Move Card to selected Column...
						System.out.println("Zielspalte: " + e.getActionCommand());
						System.out.println("Actionlistener kennt: " + cardViewToMove);
						break;
					}
				}
			}
		};
				
		for (Iterator<Column> colIterator = getColumnList().iterator(); colIterator.hasNext();){
			JMenuItem colMenuItem = new JMenuItem(colIterator.next().getName());
			colMenuItem.addActionListener(al);
			moveMenu.add(colMenuItem);
		}
		contextMenu.add(moveMenu);
		
		for (Iterator<ColumnController> colControllers = this.getColumnControllerList().iterator(); colControllers.hasNext();){
			ColumnController columnController = colControllers.next();
			
			for (Iterator<CardController> cardControllers = columnController.getCardControllerList().iterator(); cardControllers.hasNext();){
				CardController cardController = cardControllers.next();
				CardView cardView = cardController.getCardView();
				
				cardView.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent me) {
						if (me.isPopupTrigger())
							contextMenu.show(me.getComponent(), me.getX(), me.getY());
							System.out.println("Component: " + me.getComponent().getName() + 
									"\nX: " + me.getX() + 
									"\nY: " + me.getY());
							cardViewToMove = (CardView) me.getComponent();
					}
		    	});
			}
		}
		
	}
	
	public void addModel(AbstractModel model) {
		models.add(model);
	    model.addPropertyChangeListener(this);
		createColumnControllers();
	}

	public LinkedList<Column> getColumnList(){
		LinkedList <Column> columnList = null;
		for (AbstractModel model: models) {
			Board board = (Board) model;
			columnList = board.getColumnList();
			
		}
		return columnList;
	}
	
	private void createColumnControllers(){
		 for (AbstractModel model: models) {

	         Board board = (Board) model;
	         LinkedList <Column> columnList = board.getColumnList();
	         Iterator<Column> colIterator = columnList.iterator();
	         while(colIterator.hasNext()){
	        	 Column currentColumn = colIterator.next();
	        	 ColumnController currentColumnController = new ColumnController();
	        	 ColumnView currentColumnView = new ColumnView(currentColumnController);
		         System.out.println("Hier werden SpaltenControllern die SpaltenModelle hinzugefügt");
	        	 try{
	        		 currentColumnController.addModel(currentColumn);
	        	 }
	        	 catch(NullPointerException e){
	        		 e.printStackTrace();
	        		 System.out.println("Die Spalte " + currentColumn.getName() + " enthält keine Karten");
	        	 }
		         
		         currentColumnController.addView(currentColumnView);
				 columnControllers.add(currentColumnController);
				 //this.addView(currentColumnView);
	         } 
	    }

	}
	

	// Methode, die die Spaltenspezifikation für den Formbuilder erstellt und ausgibt
	public String getColSpec(int columns) {
		String colSpec = padding + ", " + columnWidth + ", ";
		int i = 0;
		for (i = 0; i < columns; i++){
			// Zusammenfügung des Strings für die Spezifikation der Spalten im FormLayoutManager
			colSpec = colSpec + colSpec;
		}
		return colSpec;
	}
	
	// Methode, die die Zeilenspezifikation für den Formbuilder erstellt und ausgibt
	public String getRowSpec(int rows) {
			String rowSpec = padding + ", " + rowHeight + ", ";
			
			for (int i = 0; i < rows; i++)
				rowSpec = rowSpec + rowSpec;
			
			//String columns = "4dlu ,15dlu, ";
			rowSpec = "4dlu, 15dlu, " + rowSpec;
			return rowSpec;
	}

	public LinkedList<ColumnController> getColumnControllerList() {
		return columnControllers;
	}

	public void setColumnControllerList(LinkedList<ColumnController> columnController) {
		this.columnControllers = columnController;
	}

	public LinkedList<ColumnView> getColumnViews() {
		return columnViews;
	}

	public void setColumnViews(LinkedList<ColumnView> columnViews) {
		this.columnViews = columnViews;
	}

	public LinkedList<ColumnView> createColumnViews() {
		
		return columnViews;
	}

	
	
	


	

	


	

	
}
