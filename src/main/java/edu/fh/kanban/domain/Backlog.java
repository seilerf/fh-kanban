package edu.fh.kanban.domain;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Backlog extends AbstractModel{
	
	private LinkedList<Card> cardList;
	
	public Backlog(LinkedList<Card> cardList){
		
		this.cardList = cardList;
		
		//merkt sich die Listener
		changes = new PropertyChangeSupport(this);

	}

	
	public LinkedList<Card> getCardList(){
		return cardList;
	}
	public void setCardList(LinkedList<Card> cardList){
		this.cardList = cardList;
	}
	
	
	
	public List addIntoList(LinkedList<Card>list){
		
		List cards = new ArrayList();
		
		if(list==null){
			System.out.println("Cardliste leer!");
		}else{
			
			Iterator<Card> lit = list.iterator();
			while(lit.hasNext()){
				Card c = lit.next();
				
				cards.add(new Card(c.getId(),c.getSize(),c.getDescription(),
						c.getBlocker(),c.getSize(),c.getHeadline(),c.getBackGround(), c.getCreated(), c.getStarted(), c.getDone()));
			}
		}
		return cards;
	}
	
	/*
	 * Sortiert die Karten nach value in aufsteigender Reihenfolge
	 */
	public List sortByValue(){
		
		//Comparator Object vom Typ CardValueComparator
		Comparator<Card> comp = new CardValueComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(cardList);
		
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
	}
	
	/*
	 * Sortiert die Karten nach Size in aufsteigender Reihenfolge
	 */
	public List sortBySize(){
		
		//Comparator Object vom Typ CardSizeComparator
		Comparator<Card> comp = new CardSizeComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(cardList);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
		
	}
	
	/*
	 * Sortiert die Karten nach headline in aufsteigender Reihenfolge
	 */
	public List sortByHeadline(){
		
		//Comparator Object vom Typ CardNameComparator
		Comparator<Card> comp = new CardNameComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(cardList);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		
		return cards;
		
	}
	
	
	/**
	 * 
	 * Sucht zuerst in headline nach dem Teilstring, wenn da der String nicht gefunden wird,
	 * sucht die Funktion weiter in description, danach in size und abschließend in value.
	 * Wird in einem der Suchattributen einer Karte der String gefunden, bricht die Suche in der
	 * Karte ab und geht zur Suche in der nächsten Karte über.
	 * z.B. wird bereits im ersten Attribut (headline) ein Suchtreffer gelandet, dann wird in 
	 * description, size und value der Karte nach dem String nicht mehr gesucht.
	 * Suchtreffer werden in einer Liste gespeichert und zurückgegeben.
	 *
	 */
	public LinkedList<Card> search(String searchString){
		
		LinkedList<Card> searchList = new LinkedList<Card>();
		
		Iterator<Card> lit = cardList.iterator();
		while(lit.hasNext()){
			Card c = lit.next();
			//Groß- und Kleinschreibung wird berücksichtigt
			if(c.getHeadline().toLowerCase().contains(searchString) ||
			   c.getHeadline().toUpperCase().contains(searchString)){		//headline
				searchList.add(c);
			}
			else {
				if(c.getDescription().contains(searchString)){	//description
					searchList.add(c);
				
				}
				else{
					Integer size = c.getSize();
					if(size.toString().contains(searchString)){		//size
						searchList.add(c);
					}
					else{
						Integer value = c.getValue();
						if(value.toString().contains(searchString)){	//value = CoS????
							searchList.add(c);
						}
					}
					
				}
				
			}
		
		}
		
		return searchList;
	}
	
	
	
	
}
