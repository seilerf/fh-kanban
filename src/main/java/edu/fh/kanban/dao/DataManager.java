package edu.fh.kanban.dao;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import edu.fh.kanban.domain.Blocker;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;

public class DataManager {
	
	private Board board;
	private LinkedList<Column> columnList;

	public DataManager(){
		
		this.board = new Board();						// prüfen...
		this.columnList = new LinkedList <Column>();
		
	}
	
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	//Methode, die eine XML-Datei verarbeitet und in den Speicher liest
	public void readXML(File file){

        Document xmlDoc = null;
        File xmlFile = new File("board.xml");

        try {
        	// SAX := Simple API for XML
            SAXBuilder builder = new SAXBuilder();
            // Das Dokument im Speicher erstellen
            xmlDoc = builder.build(xmlFile);

            // komplettes Dokument ausgeben
            XMLOutputter outStream = new XMLOutputter();
            outStream.output(xmlDoc, System.out);

            // Wurzelelement ausgeben
            Element rootElement = xmlDoc.getRootElement();
            
            this.readColumnsFromXML(rootElement);            

        } catch (JDOMException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//Methode, um Spalten eines Boards aus einer XML-Datei auszulesen
	public void readColumnsFromXML(Element root) {
	
		// Eine Liste aller Spalten erstellen
		List<Element> columns = (List<Element>) root.getChildren();
		String name;
		int limit;
		LinkedList<Card> cards;
        
        //Alle Spalten erstellen
        int i = 0;
        for (i = 0; i < columns.size(); i++) {
        	
        	//System.out.println("\n" + (i+1) + ". Spalte: " + columns.get(i).getAttributeValue("name"));
        	
        	name = columns.get(i).getAttributeValue("name");
        	limit = 8;
        	//limit = Integer.valueOf(columns.get(i).getAttributeValue("limit"));
        	
        	// Liste der Karten, die zu dieser Spalte gehören, erstellen
        	cards = this.readCardsFromXML(columns.get(i));
        	
        	this.columnList.add(new Column(name, limit, cards));
        }
        
        // Spalten an das Board übergeben
        board.setColumnList(columnList);
	}
	
	//Methode, um Karten, die einer Spalte angehören, in eine Datenstruktur zu schreiben 
	public LinkedList<Card> readCardsFromXML(Element column) {
		
    	// Alle Karten innerhalb einer Spalte in eine Liste lesen
		LinkedList<Card> cardList = new LinkedList<Card>();
    	int i = 0;
    	for (i = 0; i < column.getChildren().size(); i++){
    		
    		int id = Integer.valueOf(column.getChildren().get(i).getAttributeValue("id"));
    		int workload = Integer.valueOf(column.getChildren().get(i).getAttributeValue("workload"));
    		int value = Integer.valueOf(column.getChildren().get(i).getAttributeValue("value"));
    		String description = column.getChildren().get(i).getAttributeValue("description");
    		boolean blocker = Boolean.valueOf(column.getChildren().get(i).getAttributeValue("blocker"));
    		int size = Integer.valueOf(column.getChildren().get(i).getAttributeValue("size"));
    		String headline = column.getChildren().get(i).getAttributeValue("headline");
    		Color backGround = Color.BLUE;
    		
    		cardList.add(new Card(id, workload, value, description, blocker, size, headline, backGround));
    	}
    	
    	if (cardList.isEmpty())
    		return null;
    	else
    		return cardList;
	}
	
	
	public void writeXML(){
		
	}
	
	public void writePDF(){
		
	}
	
	public LinkedList<Column> getColumns() {
		return this.columnList;
	}
	
}
