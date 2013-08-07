package edu.fh.kanban.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fh.kanban.domain.Card;


/*
 * Autor: Inna Maier
 */
public class TestBacklog {
	
	public static void main(String[] args){
		
		//Test
	    LinkedList<Card> list = new LinkedList<Card>();
	    //3. Parameter Value, 6. Parameter Size
	    list.add(new Card(1,  1, "a", false, 2, "Ich",  null, null, null));
	    list.add(new Card(2, 10, "b", false, 5, "du",  null, null, null));
	    list.add(new Card(3, 7, "c", false, 3, "er",  null, null, null));
	    list.add(new Card(4, 4, "d", false, 8, "sie",  null, null, null));
	    list.add(new Card(5, 1, "e", false, 8, "es",  null, null, null));
	    list.add(new Card(6, 5, "f", false, 1, "Bla", null, null, null));
	    
	    testSortalgorithmen(list);
	    
	    testSearch(list);
		
	}
	
	
	
	public static void testSortalgorithmen(LinkedList<Card> list){
		
		 List cards = SortClass.sortByValue(list);
		    System.out.println("Sortiert nach Value:");
		  		Iterator<Card> test = cards.iterator();
		  		while(test.hasNext()){
		  			Card c = test.next();
		  			System.out.println(c.getValue()+" "+c.getHeadline());
		  		}
		    List cards2 = SortClass.sortBySize(list);
		    System.out.println("Sortiert nach size:");
		  		Iterator<Card> test2 = cards2.iterator();
		  		while(test2.hasNext()){
		  			Card c = test2.next();
		  			System.out.println(c.getSize()+" "+c.getHeadline());
		  		}
		    List cards3 = SortClass.sortByHeadline(list);
		    System.out.println("Sortiert nach headline:");
		  		Iterator<Card> test3 = cards3.iterator();
		  		while(test3.hasNext()){
		  			Card c = test3.next();
		  			System.out.println(c.getHeadline());
		  		}
		
	}
	
public static void testSearch(LinkedList<Card> list){
		
		String s = "ie";
		
		LinkedList<Card> cards =SearchClass.search(s, list);
		System.out.println("ie wurde in der Karte mit der ID: "+cards.getFirst().getId()+" gefunden.");
		
		String s1 = "e";
		
		LinkedList<Card> cards1 = SearchClass.search(s1, list);
		System.out.println("e wurde in den Karten gefunden mit der ID: ");
		Iterator<Card> it = cards1.iterator();
		while(it.hasNext()){
			Card c = it.next();
			System.out.println(c.getId());
		}
		
		String s2 = "1";
		
		LinkedList<Card> cards2 = SearchClass.search(s2, list);
		System.out.println("1 wurde in den Karten gefunden mit der ID: ");
		Iterator<Card> it2 = cards2.iterator();
		while(it2.hasNext()){
			Card c = it2.next();
			System.out.println(c.getId());
		}
	
	
	}
	
	

}
