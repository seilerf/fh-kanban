package edu.fh.kanban.dao.XML;

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

import org.jdom2.Element;
import org.w3c.dom.Node;

import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.dao.ColumnDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;

public class XMLColumnDAO  implements ColumnDAO {
	
	private CardDAO xmlCardDAO;
	
	public XMLColumnDAO(){
		
		xmlCardDAO = XMLDAOProperties.xmlFactory.getCardDAO();
	}
	
	@Override
	public Column findColumn(List<Element> columns, int i) {
		Column column = null;
		String name = columns.get(i).getAttributeValue("name");
    	
    	int limit = Integer.valueOf((columns.get(i).getAttributeValue("limit")));
    	System.out.println("Name: " + name + " Limit: " + limit);
    	// Liste der Karten, die zu dieser Spalte gehören, erstellen
    	column = new Column(name, limit);	
		
		return column;
	}
	@Override
	public int insertColumn(Column column) {

			org.w3c.dom.Element columnElement = XMLDAOProperties.xmlDoc.createElement("Column");
		 
				// Attribute der Spalten ändern
				columnElement.setAttribute("name", column.getName());
				columnElement.setAttribute("limit", String.valueOf(column.getLimit()));
				columnElement.setAttribute("maxCol", "2");
				Node rootNode = XMLDAOProperties.xmlDoc.getFirstChild();
				rootNode.appendChild(columnElement);
				
				LinkedList<Card> cardList = column.getCardList();
				if (cardList != null) {
					for (Iterator<Card> iCard = cardList.iterator(); iCard.hasNext();) {
						Card card = iCard.next();
						// Kartenelemente
						xmlCardDAO.insertCard(card);
						
					}
				}

		return 1;
	}

	@Override
	public boolean deleteColumn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateColumn() {
		// TODO Auto-generated method stub
		return false;
	}

	





}
