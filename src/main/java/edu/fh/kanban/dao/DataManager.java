package edu.fh.kanban.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class DataManager {

	public DataManager(){
		
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
		List spalten = (List) root.getChildren();
        
        //Alle Spalten ausgeben
        int i = 0;
        for (i = 0; i < spalten.size(); i++) {
        	System.out.println("\n" + (i+1) + ". Spalte: " + ((Element) spalten.get(i)).getAttributeValue("name"));
        	
        	this.readCardsFromXML( ((Element)spalten.get(i)));
        	
        }
	}
	
	//Methode, um Karten, die einer Spalte angehören, aus einem XML-Dokument auszulesen 
	public void readCardsFromXML(Element spalte) {
		
		//Eine Liste aller Karten erstellen
    	List karten = (List) spalte.getChildren();
    	// Alle Karten innerhalb einer Spalte ausgeben
    	int j = 0;
    	for (j = 0; j < karten.size(); j++){
    		Element karte = (Element) karten.get(j);
    		System.out.println(j+1 + ". Karte: " + karte.getAttributeValue("description") + " gehört zu " 
    		+ karte.getParentElement().getAttributeValue("name"));
    	}
	}
	
	
	public void writeXML(){
		
	}
	
	public void writePDF(){
		
	}
	
}
