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
	    /*LinkedList<Card> list = new LinkedList<Card>();
	    list.add(new CardModel(1, "ich", 2, 10));
	    list.add(new CardModel(1, "du", 1, 6));
	    list.add(new CardModel(1, "er", 5, 9));
	    list.add(new CardModel(1, "er", 1, 3));
	    list.add(new CardModel(1, "ich", 2, 1));
	    list.add(new CardModel(1, "sie", 4, 10));
	    
	    testSortalgorithmen(list);*/
	   
		
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
