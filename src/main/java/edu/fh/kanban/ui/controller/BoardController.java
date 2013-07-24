package edu.fh.kanban.ui.controller;

import java.util.Iterator;
import java.util.LinkedList;

import com.jgoodies.forms.layout.LayoutMap;

import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.view.ColumnView;

public class BoardController extends AbstractController{
	public final static String GUICHANGE_PROPERTY = "GUI";
	
	private final String columnWidth = "270dlu";//160
	private final String rowHeight = "135dlu";//130
	private final String padding = "4dlu";
	
	private LinkedList<ColumnController> columnControllers = new LinkedList<>();
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
	        		 System.out.println("Die Spalte " + currentColumn.getName() + " enthält keine Karten");
	        	 }
		         
		         currentColumnController.addView(currentColumnView);
				 columnControllers.add(currentColumnController);
				 this.addView(currentColumnView);
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
		
		return null;
	}

	
}
