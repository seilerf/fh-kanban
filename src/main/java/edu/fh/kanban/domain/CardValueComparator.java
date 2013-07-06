package edu.fh.kanban.domain;

import java.util.Comparator;

/*
 * Autor: Inna Maier
 * Funktion: Sortiert die Karten nach value in aufsteigender Reihenfolge
 */
public class CardValueComparator implements Comparator<Card>{

	@Override
	  public int compare(Card c1, Card c2) {
	    int x=0;
		if (c1.getValue() == c2.getValue()) {
	      x= 0;										//beide Parameter gleich eingeordnet
	    }
		else if (c1.getValue() < c2.getValue()) {
	      x= -1;									//1. Parameter ist untergeordnet
	    }
		else if (c2.getValue() > c2.getValue()) {
	      x= 1;										//1. Parameter ist übergeordnet
	    }
	    return x;
	    
	  }

}
