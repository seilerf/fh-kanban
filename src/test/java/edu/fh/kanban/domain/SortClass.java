package edu.fh.kanban.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.CardNameComparator;
import edu.fh.kanban.domain.CardSizeComparator;
import edu.fh.kanban.domain.CardValueComparator;

/*
 * Autor: Inna Maier
 */
public class SortClass{
	
	public static List<Card> addIntoList(LinkedList<Card>list){
		
		List<Card> cards = new ArrayList<Card>();
		
		if(list==null){
			System.out.println("Cardliste leer!");
		}else{
			
			Iterator<Card> lit = list.iterator();
			while(lit.hasNext()){
				Card c = lit.next();
				
				cards.add(new Card(c.getId(),c.getValue(),c.getDescription(),
						c.getBlocker(),c.getSize(),c.getHeadline(), c.getCreated(), c.getStarted(), c.getDone()));
			}
		}
		return cards;
	}
	
	/*
	 * Sortiert die Karten nach value in aufsteigender Reihenfolge
	 */
	public static List<Card> sortByValue(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardValueComparator
		Comparator<Card> comp = new CardValueComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List<Card> cards = addIntoList(list);
		
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
	}
	
	/*
	 * Sortiert die Karten nach Size in aufsteigender Reihenfolge
	 */
	public static List<Card> sortBySize(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardSizeComparator
		Comparator<Card> comp = new CardSizeComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List<Card> cards = addIntoList(list);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
		
	}
	
	/*
	 * Sortiert die Karten nach headline in aufsteigender Reihenfolge
	 */
	public static List<Card> sortByHeadline(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardNameComparator
		Comparator<Card> comp = new CardNameComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List<Card> cards = addIntoList(list);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		
		return cards;
		
	}

}
