package edu.fh.kanban.domain;

import java.util.LinkedList;

public class Column extends AbstractModel{
	
	private String name;
	private int limit;
	private LinkedList<Card> cardList;
	

	//Constructor
	public Column(String name, int limit) {
		
		this.name = name;
		this.limit = limit;
	
	}
	public void addCard(Card card){
		
		if(cardList!=null){
			cardList.add(card);
		}
		else{
			cardList = new LinkedList<Card>();
		}
		
	}

	public LinkedList<Card> getCardList() {
		return cardList;
	}
	public void setCardList(LinkedList<Card> cardList) {
		this.cardList = cardList;
	}
	public void setName(String name) {
		this.name = name;
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
