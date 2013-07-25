package edu.fh.kanban.dao.XML;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.PreferenceDAO;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;

public class XMLBoardDAO implements BoardDAO{

	private Board currentBoard;
	private File file;
	private PreferenceDAO prefDAO;
	
	
	@Override
	public Board findBoard(File file) throws ParseException, InterruptedException, NullPointerException {
		
			this.file = file;
			openFile(this.file);
		
		return currentBoard;
		
	}
	
	public void openFile(File file) throws ParseException, InterruptedException, NullPointerException  {
		if (file.getPath().endsWith(".xml")) {
			this.importFromXML(file);
		}
	}
	
	public void importFromXML(File xmlFile) throws ParseException, InterruptedException, NullPointerException  {
		//xmlFile = new File("board.xml");
		this.currentBoard =  readBoardFromXML(xmlFile);
	}
	
	
	
	//Methode, die eine XML-Datei verarbeitet und in den Speicher liest
	public Board readBoardFromXML(File xmlFile) throws ParseException, InterruptedException, NullPointerException {

		        Document xmlDoc = null;
		        
		        
		        /**
		         * 
		         */

		        currentBoard = new Board();

		        try {
		        	// SAX := Simple API for XML
		            SAXBuilder builder = new SAXBuilder();
		            
		            // Das Dokument im Speicher erstellen
		            xmlDoc = builder.build(xmlFile);
		          
		            // Wurzelelement aus der Datei lesen
		            Element rootElement = xmlDoc.getRootElement();
		           
		            currentBoard.setName(rootElement.getAttributeValue("name"));
		            System.out.println(rootElement.getAttributeValue("name"));
		            
		            // Spalten in das Board einlesen
		            System.out.println("Jetzt werden die Spalten eingelesen");
		             currentBoard.setColumnList(readColumnsFromXML(rootElement));

		        } catch (JDOMException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        return currentBoard;
		    }
			
			
			
			//Methode, um Spalten eines Boards aus einer XML-Datei auszulesen
			private LinkedList<Column> readColumnsFromXML(Element root) throws ParseException, InterruptedException, NullPointerException {
			
				// Eine Liste aller Spalten erstellen
				List<Element> columns = (List<Element>) root.getChildren();
				LinkedList<Column> columnList = new LinkedList<Column>();
		        
				
				
		        //Alle Spalten erstellen
		        for (int i = 0; i < columns.size(); i++) {
		        		
		        	String name = columns.get(i).getAttributeValue("name");
		        	
		        	int limit = Integer.valueOf((columns.get(i).getAttributeValue("limit")));
		        	System.out.println("Name: " + name + " Limit: " + limit);
		        	
		        	// Liste der Karten, die zu dieser Spalte gehören, erstellen
		        	Column currentColumn = new Column(name, limit);	
		        	columnList.add(currentColumn);
		        	System.out.println("Aufruf: readCardsFromXML(): ");
		        	try{
		        		currentColumn.setCardList(readCardsFromXML(columns.get(i)));
		        	}
		        	catch(NullPointerException e1){
		        		System.out.println("Keine Spalten hinzugefügt");
		        	}
		        	
		        	
		        	
		        	
		        }
		        
		        // Spalten an das Board übergeben
		        
		        
		        if (columnList.isEmpty()){
		        	
		        	throw new NullPointerException();
		        }
		        else
		        	return columnList;
			}
			
			//Methode, um Karten, die einer Spalte angehören, in eine Datenstruktur zu schreiben 
			private LinkedList<Card> readCardsFromXML(Element column) throws ParseException, InterruptedException, NullPointerException {
				
		    	// Alle Karten innerhalb einer Spalte in eine Liste lesen
				LinkedList<Card> cardList = new LinkedList<Card>();
		    	for (int i = 0; i < column.getChildren().size(); i++){
		    		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		Date created = null;
		    		Date started = null;
		    		Date done = null;
		  
		    		String createDate = ((column.getChildren().get(i).getAttributeValue("created")));
		    		if(createDate.isEmpty()==false) {
		    		created = sdf.parse(createDate);
		    		}
		    		
		    		String starteDate = ((column.getChildren().get(i).getAttributeValue("started")));
		    		if(starteDate.isEmpty()==false) {
		    		started = sdf.parse(starteDate);
		    		}
		    		
		    		String doneDate = ((column.getChildren().get(i).getAttributeValue("done")));
		    		if(doneDate.isEmpty()==false) {
		    		done = sdf.parse(doneDate);
		    		}
		    			
		    	
		    		int id = Integer.valueOf(column.getChildren().get(i).getAttributeValue("id"));
		    		int value = Integer.valueOf(column.getChildren().get(i).getAttributeValue("value"));
		    		String description = column.getChildren().get(i).getAttributeValue("description");
		    		boolean blocker = Boolean.valueOf(column.getChildren().get(i).getAttributeValue("blocker"));
		    		int size = Integer.valueOf(column.getChildren().get(i).getAttributeValue("size"));
		    		String headline = column.getChildren().get(i).getAttributeValue("headline");
		    		
		    		
		    		int rgb = 0;
		    		Color backGround=null;
		    		if (column.getChildren().get(i).getAttributeValue("backGround") != ""){
		    			rgb = Integer.valueOf(column.getChildren().get(i).getAttributeValue("backGround"));
		    			if(rgb == 1) {
		    				backGround = Color.blue;
		    			}
		    			if(rgb == 2) {
		    				backGround = Color.orange;
		    			}
		    			if(rgb == 3) {
		    				backGround = Color.red;
		    			}
		    			if(rgb == 4) {
		    				backGround = Color.green;
		    			}
		    		} else {
		    			// Wenn keine Farbe in der XML-Datei hinterlegt ist, wird die Karte mit einem leicht grauen Hintergrund versehen
		    			backGround = Color.LIGHT_GRAY;
		    			}
		    	System.out.println("ID: " +id + " Value: " +  value + " Beschreibung" + description);
		    	cardList.add(new Card(id, value, description, blocker, size, headline, backGround, created, started, done));
		    	
		    	}
		    	
		    	if (cardList.isEmpty()){
		    		System.out.println("Keine Karten hinzugefügt");
		    		NullPointerException e1 = new NullPointerException();
		    		throw e1;
		    	}
		    	else{
		    		System.out.println("Kartenliste wurder zurückgegeben!");
		    		return cardList;
		    	}
			}

	@Override
	public int insertBoard() {
		
		this.writeXML(currentBoard, file);
		
		return 0;
	}
	
	public void writeXML(Board board, File file) {
		 
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
				
				LinkedList<Card> cardList = column.getCardList();
				if (cardList != null) {
					for (Iterator<Card> iCard = cardList.iterator(); iCard.hasNext();) {
						Card card = iCard.next();
						// Kartenelemente
						org.w3c.dom.Element xmlCard = xmlDoc.createElement("Card");
						xmlCard.setAttribute("id", String.valueOf(card.getId()));
						//xmlCard.setAttribute("workload", String.valueOf(card.getSize()));
						xmlCard.setAttribute("value", String.valueOf(card.getValue()));
						xmlCard.setAttribute("headline", card.getHeadline());
						xmlCard.setAttribute("description", card.getDescription());
						xmlCard.setAttribute("blocker", String.valueOf(card.getBlocker()));
						xmlCard.setAttribute("backGround", String.valueOf(card.getBackGround()));
						xmlCard.setAttribute("created", String.valueOf(card.getCreated()));
						xmlCard.setAttribute("started", String.valueOf(card.getStarted()) );//"1"
						xmlCard.setAttribute("done", String.valueOf(card.getDone()));//"1"
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
		StreamResult outstream = new StreamResult(file);
	 
		transformer.transform(source, outstream);
		
		System.out.println("File saved!");
	 
		} catch (ParserConfigurationException pce) {
		pce.printStackTrace();
		} catch (TransformerException tfe) {
		tfe.printStackTrace();
		}
	}

	@Override
	public boolean deleteBoard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBoard() {
		// TODO Auto-generated method stub
		return false;
	}

}
