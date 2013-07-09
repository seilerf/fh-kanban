package edu.fh.kanban.dao;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;

public class XMLParser{
	
	//Methode, die eine XML-Datei verarbeitet und in den Speicher liest
	public Board readBoardFromXML(File xmlFile){

        Document xmlDoc = null;
        Board board = new Board();

        try {
        	// SAX := Simple API for XML
            SAXBuilder builder = new SAXBuilder();
            // Das Dokument im Speicher erstellen
            xmlDoc = builder.build(xmlFile);
            // Wurzelelement aus der Datei lesen
            Element rootElement = xmlDoc.getRootElement();
            board.setName(rootElement.getAttributeValue("name"));
            
            // Spalten in das Board einlesen
            this.readColumnsFromXML(rootElement, board);

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return board;
    }
	
	//Methode, um Spalten eines Boards aus einer XML-Datei auszulesen
	private LinkedList<Column> readColumnsFromXML(Element root, Board board) {
	
		// Eine Liste aller Spalten erstellen
		List<Element> columns = (List<Element>) root.getChildren();
		LinkedList<Column> columnList = new LinkedList<Column>();
        
        //Alle Spalten erstellen
        for (int i = 0; i < columns.size(); i++) {
        			
        	String name = columns.get(i).getAttributeValue("name");
        	int limit = Integer.valueOf((columns.get(i).getAttributeValue("limit")));
        	
        	// Liste der Karten, die zu dieser Spalte gehören, erstellen
        	LinkedList<Card> cards = this.readCardsFromXML(columns.get(i));
        	
        	columnList.add(new Column(name, limit, cards));
        }
        
        // Spalten an das Board übergeben
        board.setColumnList(columnList);
        
        if (columnList.isEmpty())
        	return null;
        else
        	return columnList;
	}
	
	//Methode, um Karten, die einer Spalte angehören, in eine Datenstruktur zu schreiben 
	private LinkedList<Card> readCardsFromXML(Element column) {
		
    	// Alle Karten innerhalb einer Spalte in eine Liste lesen
		LinkedList<Card> cardList = new LinkedList<Card>();
    	for (int i = 0; i < column.getChildren().size(); i++){
    		
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
	
	public void writeXML(Board board){
		 
		try {
	 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
		// root element = Board
		org.w3c.dom.Document xmlDoc = docBuilder.newDocument();
		org.w3c.dom.Element rootElement = xmlDoc.createElement("Board");
		rootElement.setAttribute("name", board.getName());
		xmlDoc.appendChild(rootElement);
	 
		// Spaltenelemente
		if (!board.getColumnList().isEmpty()){
			for (Iterator<Column> iColumn = board.getColumnList().iterator(); iColumn.hasNext();) {
				Column column = iColumn.next();
				org.w3c.dom.Element columnElement = xmlDoc.createElement("Column");
				rootElement.appendChild(columnElement);
		 
				// Attribute der Spalten ändern
				columnElement.setAttribute("name", column.getName());
				columnElement.setAttribute("limit", String.valueOf(column.getLimit()));
				columnElement.setAttribute("maxCol", "2");
				
				LinkedList<Card> cardList = column.getCards();
				if (cardList != null) {
					for (Iterator<Card> iCard = cardList.iterator(); iCard.hasNext();) {
						Card card = iCard.next();
						// Kartenelemente
						org.w3c.dom.Element xmlCard = xmlDoc.createElement("Card");
						xmlCard.setAttribute("id", String.valueOf(card.getId()));
						xmlCard.setAttribute("workload", String.valueOf(card.getWorkload()));
						xmlCard.setAttribute("value", String.valueOf(card.getValue()));
						xmlCard.setAttribute("headline", card.getHeadline());
						xmlCard.setAttribute("description", card.getDescription());
						xmlCard.setAttribute("blocker", String.valueOf(card.getBlocker()));
						xmlCard.setAttribute("backGround", "1");
						xmlCard.setAttribute("created", "1");
						xmlCard.setAttribute("started", "1");
						xmlCard.setAttribute("done", "1");
						xmlCard.setAttribute("size", String.valueOf(card.getSize()));	
						columnElement.appendChild(xmlCard);
					}
				}
			}
		}
		// Inhalt in eine XML-Datei schreiben
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(xmlDoc);
		StreamResult outstream = new StreamResult(new File("boardOutput.xml"));
	 
		transformer.transform(source, outstream);
		
		System.out.println("File saved!");
	 
		} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
		} catch (TransformerException tfe) {
		tfe.printStackTrace();
		}
	}
}
