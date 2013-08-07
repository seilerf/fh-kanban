package edu.fh.kanban.domain;



import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.domain.Card;

/*
 * Autor: Inna Maier
 */

public class SearchClass {
	
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
	public static LinkedList<Card> search(String searchString, LinkedList<Card> list){
		
		LinkedList<Card> searchList = new LinkedList<Card>();
		
		Iterator<Card> lit = list.iterator();
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
