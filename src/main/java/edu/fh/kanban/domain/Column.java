package edu.fh.kanban.domain;

import java.util.LinkedList;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.ui.controller.BoardController;

public class Column extends AbstractModel{
	
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
		LinkedList<Card> oldList = cardList;
		
		if(cardList!=null){
			cardList.add(card);
		}
		else{
			cardList = new LinkedList<Card>();
		}
		
		DataManager.getBoard();
		
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
