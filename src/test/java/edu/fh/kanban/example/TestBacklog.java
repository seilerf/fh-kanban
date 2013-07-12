package edu.fh.kanban.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.SortClass;

/*
 * Autor: Inna Maier
 */
public class TestBacklog {
	
	public static void main(String[] args){
		
		//Test
	    LinkedList<Card> list = new LinkedList<Card>();
	    //3. Parameter Value, 6. Parameter Size
	    list.add(new Card(1,  1, "a", false, 2, "Ich", null));
	    list.add(new Card(2, 10, "b", false, 5, "du", null));
	    list.add(new Card(3, 7, "c", false, 3, "er", null));
	    list.add(new Card(4, 4, "d", false, 8, "sie", null));
	    list.add(new Card(5, 1, "e", false, 8, "es", null));
	    list.add(new Card(6, 5, "f", false, 1, "Bla", null));
	    
	    testSortalgorithmen(list);
	   
		
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
	
	

}
