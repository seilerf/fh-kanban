package edu.fh.kanban.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * Autor: Inna Maier
 */
public class SortClass {
	
	
	public static List addIntoList(LinkedList<Card>list){
		
		List cards = new ArrayList();
		Iterator<Card> lit = list.iterator();
		while(lit.hasNext()){
			Card c = lit.next();
			//muss geändert werden
			//cards.add(new Card(c.getCardid(), c.getHeadline(), c.getSize(), c.getValue()));
		}
		return cards;
	}
	
	/*
	 * Sortiert die Karten nach value in aufsteigender Reihenfolge
	 */
	public static List sortByValue(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardValueComparator
		Comparator<Card> comp = new CardValueComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(list);
		
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
	}
	
	/*
	 * Sortiert die Karten nach Size in aufsteigender Reihenfolge
	 */
	public static List sortBySize(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardSizeComparator
		Comparator<Card> comp = new CardSizeComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(list);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		return cards;
		
		
	}
	
	/*
	 * Sortiert die Karten nach headline in aufsteigender Reihenfolge
	 */
	public static List sortByHeadline(LinkedList<Card> list){
		
		//Comparator Object vom Typ CardNameComparator
		Comparator<Card> comp = new CardNameComparator();
		
		//Hilfsliste zum Sortieren der Karten
		List cards = addIntoList(list);
		//Funktionsaufruf zum Sortieren
		Collections.sort(cards,comp);
		
		
		return cards;
		
	}

}
