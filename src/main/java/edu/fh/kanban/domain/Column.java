package edu.fh.kanban.domain;

import java.util.LinkedList;

public class Column {
	
	private String name;
	private int limit;
	private LinkedList<Card> cardList;
	
	//Constructor
	public Column(String name, int limit, LinkedList<Card> cardList) {
		
		this.name = name;
		this.limit = limit;
		this.cardList = cardList;
	}
	public void addCard(Card card){
		cardList.add(card);
	}
	/**
	 * @return the cards
	 */
	public LinkedList<Card> getCards() {
		return cardList;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
			
		this.limit= limit;		
	}
	
}
