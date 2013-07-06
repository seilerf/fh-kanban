package edu.fh.kanban.domain;

import java.util.Comparator;


/*
 * Autor: Inna Maier
 * Funktion: Sortiert die Karten alphatisch nach Überschrift in aufsteigender Reihenfolge
 * 
 */
public class CardNameComparator implements Comparator<Card>{
	
	@Override
	  public int compare(Card c1, Card c2) {
	    if (c1.getHeadline() == null && c2.getHeadline() == null) {
	      return 0;							//beide Parameter gleich eingeordnet
	    }
	    if (c1.getHeadline() == null) {
	      return 1;							//1. Parameter ist übergeordnet
	    }
	    if (c2.getHeadline() == null) {
	      return -1;						//1. Parameter ist untergeordnet
	    }
	    return c1.getHeadline().compareTo(c2.getHeadline());
	  }
}
