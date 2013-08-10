package edu.fh.kanban.ui.controller;

import java.util.LinkedList;
import java.util.List;

import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.domain.Backlog;
import edu.fh.kanban.domain.Card;

public class BacklogController extends AbstractController {

	
	public BacklogController(){
		
	}
	
	public void addCard(Card card){
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			b.addCard(card);
		}
	}
	
	public LinkedList<Card> getCardList(){

		LinkedList <Card> cardList = null;
		
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			cardList = b.getCardList();
		}

		return cardList;
	}
	
	public List sortByHeadline(){
		List cardList = null;
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			cardList = b.sortByHeadline();
		}
		
		return cardList;
	}
	
	public List sortByValue(){
		List cardList = null;
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			cardList = b.sortByValue();
		}
		
		return cardList;
	}
	public List sortBySize(){
		List cardList = null;
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			cardList = b.sortBySize();
		}
		
		return cardList;
	}
	public LinkedList<Card> search(String searchString){
		LinkedList<Card> cardList = null;
		for(AbstractModel model: models){
			Backlog b = (Backlog) model;
			cardList = b.search(searchString);
		}
		
		return cardList;
	}
	

	
	
}
