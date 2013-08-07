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
import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.dao.ColumnDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.PreferenceDAO;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;

public class XMLBoardDAO implements BoardDAO{

	private Board currentBoard;
	private PreferenceDAO prefDAO;
	private ColumnDAO xmlColumnDAO;
	
	
	public XMLBoardDAO(){
		
		xmlColumnDAO = XMLDAOProperties.xmlFactory.getColumnDAO();

	}
	
	@Override
	public Board findBoard() throws ParseException, InterruptedException, NullPointerException {
		
			importFromXML(XMLDAOProperties.file);
		
		return currentBoard;
		
	}
	
	
	public void importFromXML(File file) throws ParseException, InterruptedException, NullPointerException  {

		if (file.getPath().endsWith(".xml")) {
			this.currentBoard =  readBoardFromXML(file);
		}
		
	}
	
	
	
	//Methode, die eine XML-Datei verarbeitet und in den Speicher liest
	public Board readBoardFromXML(File xmlFile) throws ParseException, InterruptedException, NullPointerException {

		        Document xmlDoc = null;

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
				
				
				Column currentColumn;
				
				//Erzeugen der DAO-Factory und des ColumnDAOs
				try{
					
		    		//Alle Spalten erstellen
			        for (int i = 0; i < columns.size(); i++) {
			        	try{
			        		
			    			currentColumn = xmlColumnDAO.findColumn(columns,i);
			    			columnList.add(currentColumn);
			    			System.out.println("currentColumn: " + currentColumn.getName() + "\n");
				        	try{
				        		currentColumn.setCardList(readCardsFromXML(columns.get(i)));
				        	 
				        	}
				        	catch(NullPointerException e1){
				        		System.out.println("Keine Spalten hinzugefügt");
				        	}
			    			
			    		}
			    		catch(NullPointerException e){
			    			System.out.println("Fehler beim Einlesen der Daten");
			    			e.printStackTrace();
			    		}
			        }
				}
				catch(NullPointerException e){
					e.printStackTrace();
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
		    		Card currentCard = XMLDAOProperties.xmlFactory.getCardDAO().findCard(column,i);
		    		cardList.add(currentCard);
		    		//new Card(id, value, description, blocker, size, headline, backGround, created, started, done)
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
	public int insertBoard(Board board) {
		
		this.writeXML(board);
		
		return 0;
	}
	
	public void writeXML(Board board) {
		
		try {

		org.w3c.dom.Element rootElement = XMLDAOProperties.xmlDoc.createElement("Board");
		rootElement.setAttribute("name", currentBoard.getName());
		//Der Baum wird dem xmlDoc per appendChild() hinzugefügt (Es werden "Elemente" übergeben)
		XMLDAOProperties.xmlDoc.appendChild(rootElement);
		System.out.println("XMLDoc wurde rootElement hinzugefügt\n");
	 
		
		// Spaltenelemente
		if (!currentBoard.getColumnList().isEmpty()){
			for (Iterator<Column> iColumn = currentBoard.getColumnList().iterator(); iColumn.hasNext();) {
				Column column = iColumn.next();
				xmlColumnDAO.insertColumn(column);
				
			}
			
		}
		// Inhalt in eine XML-Datei schreiben
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(XMLDAOProperties.xmlDoc);
		StreamResult outstream = new StreamResult(XMLDAOProperties.file);
	 
		transformer.transform(source, outstream);
		
		System.out.println("File saved!");
	 
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
