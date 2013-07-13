package edu.fh.kanban.dao;

import java.io.File;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;

public class DataManager {
	
	private static Board board;

	public DataManager(){
				
	}
	
	public void openFile(File file) throws ParseException {
		if (file.getPath().endsWith(".xml")) {
			this.importFromXML(file);
		}
	}
	
	public void saveFile(File file) {
		if (file.getPath().endsWith(".xml")) {
			this.exportToXML(this.getBoard(), file);
			
		}
		
		else if(file.getPath().endsWith(".html")){
			this.exportToHTML(this.getBoard(), file);
		}
	}
	
	public void importFromXML(File xmlFile) throws ParseException {
		//xmlFile = new File("board.xml");
		
		XMLParser xmlParser = new XMLParser();
		this.board =  xmlParser.readBoardFromXML(xmlFile);
	}
	
	// Methode, die den Export des Boards in eine XML-Datei startet
		public void exportToXML(Board board, File file) {
			XMLParser xmlParser = new XMLParser();
			xmlParser.writeXML(board, file);
		}
		
		public void exportToHTML(Board board, File file){
			HTMLExport htmlExporter = new HTMLExport();
			htmlExporter.htmlOutput(board, file);
		}

	/**
	 * @return the board
	 */
	public static Board getBoard() {
		return board;
	}
	
	
	// Eine Methode, mit der sämtliche Karten in dem Board in eine einzige Liste geschoben werden
	public LinkedList<Card> getAllCards(LinkedList<Column> columnList) {
		LinkedList<Card> allCards = new LinkedList<Card>();
		
		for (Iterator<Column> iColumn = columnList.iterator(); iColumn.hasNext();){
			Column column = iColumn.next();
			
			if (column.getCards() != null) {
				for (Iterator<Card> iCard = column.getCards().iterator(); iCard.hasNext();)
					allCards.add(iCard.next());
			}
		}
		
		return allCards;
	}
	
	public void writePDF(){
		
	}
	
}
