package edu.fh.kanban.dao.XML;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.domain.Card;

public class XMLCardDAO implements CardDAO {
	

	public XMLCardDAO(){
		
	}
	@Override
	public Card findCard(Element column, int i) throws ParseException {
		Card currentCard;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date created = null;
		Date started = null;
		Date done = null;

		String createdDate = ((column.getChildren().get(i).getAttributeValue("created")));
		System.out.println("created (String):" + createdDate);
		if(createdDate.isEmpty()==false) {
		
		created = formatter.parse(createdDate);
		System.out.println("created (in Card vor Formatierung):" + created);
		
		}
		
		String starteDate = ((column.getChildren().get(i).getAttributeValue("started")));
		if(starteDate.isEmpty()==false) {
		started = formatter.parse(starteDate);
		}
		
		String doneDate = ((column.getChildren().get(i).getAttributeValue("done")));
		if(doneDate.isEmpty()==false) {
		done = formatter.parse(doneDate);
		}
			
	
		int id = Integer.valueOf(column.getChildren().get(i).getAttributeValue("id"));
		int value = Integer.valueOf(column.getChildren().get(i).getAttributeValue("value"));
		String description = column.getChildren().get(i).getAttributeValue("description");
		boolean blocker = Boolean.valueOf(column.getChildren().get(i).getAttributeValue("blocker"));
		int size = Integer.valueOf(column.getChildren().get(i).getAttributeValue("size"));
		String headline = column.getChildren().get(i).getAttributeValue("headline");
		
		
		int rgb = 0;
		Color backGround=null;
		/*if (column.getChildren().get(i).getAttributeValue("backGround") != ""){
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
			}*/
		System.out.println("ID: " +id + " Value: " +  value + " Beschreibung" + description);
		currentCard  = new Card(id, value, description, blocker, size, headline, created, started, done);
		return currentCard;
	}

	@Override
	public int insertCard(Card card) {
		NodeList columnNodes;
		
		org.w3c.dom.Element xmlCard = XMLDAOProperties.xmlDoc.createElement("Card");
		xmlCard.setAttribute("id", String.valueOf(card.getId()));
		xmlCard.setAttribute("value", String.valueOf(card.getValue()));
		xmlCard.setAttribute("headline", card.getHeadline());
		xmlCard.setAttribute("description", card.getDescription());
		xmlCard.setAttribute("blocker", String.valueOf(card.getBlocker()));
		xmlCard.setAttribute("created", card.getCreatedString());
		System.out.println("created:" + card.getCreatedString());
		xmlCard.setAttribute("started", card.getStartedString() );//"1"
		xmlCard.setAttribute("done", card.getDoneString());//"1"
		xmlCard.setAttribute("size", String.valueOf(card.getSize()));	
		XMLDAOProperties.xmlDoc.getFirstChild().getLastChild().appendChild(xmlCard);
		
		

		return 1;
	}

	@Override
	public boolean deleteCard() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCard() {
		// TODO Auto-generated method stub
		return false;
	}

}
