package edu.fh.kanban.dao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import edu.fh.kanban.domain.XMLReader;

public class DataManager {

	public DataManager(){
		
	}
	
	public void readXML(){

        Document xmlDoc = null;
        File f = new File("board.xml");

        try {
            // Das Dokument erstellen
            SAXBuilder builder = new SAXBuilder();
            xmlDoc = builder.build(f);
            XMLOutputter fmt = new XMLOutputter();

            // komplettes Dokument ausgeben
            fmt.output(xmlDoc, System.out);

            // Wurzelelement ausgeben
            Element rootElement = xmlDoc.getRootElement();
            System.out.println("\nWurzelelement: " + rootElement);

            // Wurzelelementnamen ausgeben
            System.out.println("Wurzelelementname: " + rootElement.getName());
            
            //Attribut (Name) des Boards ausgeben
            System.out.println("Name des Boards:" + rootElement.getAttributeValue("name"));

            // Eine Liste aller Spalten erstellen
            List spalten = (List) rootElement.getChildren();
            
            //Alle Spalten ausgeben
            int i = 0;
            for (i = 0; i < spalten.size(); i++) {
            	System.out.println("\n" + (i+1) + ". Spalte: " + ((Element) spalten.get(i)).getAttributeValue("name"));
            	
            	
            }

        } catch (JDOMException e) { 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void readCardsFromXML(Element spalte, int size) {
		
    	List karten = (List) spalte.getChildren();
    	// Alle Karten innerhalb einer Spalte ausgeben
    	int j = 0;
    	for (j = 0; j < karten.size(); j++){
    		Element karte = (Element) karten.get(j);
    		System.out.println(j+1 + ". Karte: " + karte.getAttributeValue("name") + " gehört zu " 
    		+ karte.getParentElement().getAttributeValue("name"));
    	}
	}
	
	
	public void writeXML(){
		
	}
	
	public void writePDF(){
		
	}
	
}
