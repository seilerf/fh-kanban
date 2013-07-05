package edu.fh.kanban.dao;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;

public class DataManager {
	
	private Board board;
	private LinkedList<Column> columnList;
	private LinkedList<Card> cardList;

	public DataManager(){
		
		this.board = new Board();						// prüfen...
		this.columnList = new LinkedList <Column>();
		this.cardList = new LinkedList <Card>();
		
	}
	
	//Methode, die eine XML-Datei verarbeitet und in den Speicher liest
	public void readXML(File file){

        Document xmlDoc = null;
        File xmlFile = new File("board.xml");

        try {
        	// SAX := Simple API for XML
            SAXBuilder builder = new SAXBuilder();
            // Das Dokument erstellen
            xmlDoc = builder.build(xmlFile);

            // komplettes Dokument ausgeben
            XMLOutputter outStream = new XMLOutputter();
            outStream.output(xmlDoc, System.out);

            // Wurzelelement ausgeben
            Element rootElement = xmlDoc.getRootElement();
            System.out.println("\nWurzelelement: " + rootElement);

            // Wurzelelementnamen ausgeben
            System.out.println("Wurzelelementname: " + rootElement.getName());
            
            //Attribut (Name) des Boards ausgeben
            System.out.println("Name des Boards:" + rootElement.getAttributeValue("name"));
            
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
        
        //Alle Spalten ausgeben
        int i = 0;
        for (i = 0; i < columns.size(); i++) {
        	
        	System.out.println("\n" + (i+1) + ". Spalte: " + columns.get(i).getAttributeValue("name"));
        	
        	name = (columns.get(i)).getAttributeValue("name");
        	this.columnList.add(new Column(name));
        	
        	System.out.println("Anzahl Spalten: " + this.columnList.size());
        	
        	this.readCardsFromXML(columns.get(i));
        	
        }
	}
	
	//Methode, um Karten, die einer Spalte angehören, aus einem XML-Dokument auszulesen 
	public void readCardsFromXML(Element spalte) {
		
		//Eine Liste aller Karten erstellen
    	List<Element> karten = (List<Element>) spalte.getChildren();
    	// Alle Karten innerhalb einer Spalte ausgeben
    	int j = 0;
    	for (j = 0; j < karten.size(); j++){
    		Element karte = karten.get(j);
    		System.out.println(j+1 + ". Karte: " + karte.getAttributeValue("description"));
    	}
	}
	
	
	public void writeXML(){
		
	}
	
	public void writePDF(){
		
	}
	
	public LinkedList<Column> getCols() {
		return this.columnList;
	}
	
}
