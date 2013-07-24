package edu.fh.kanban.ui.controller;

import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.PreferenceDAO;
import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.view.CardView;
import edu.fh.kanban.ui.controller.CardController;

public class ColumnController extends AbstractController {
	
	private LinkedList<CardController> cardControllers = new LinkedList<>();
	private Preference pref;
	public ColumnController(){
		 
	}
	
	public void addModel(AbstractModel model) {
		models.add(model);
	    model.addPropertyChangeListener(this);
	    this.createCardControllers();
	}
	
	public void getPreference(){
		  DAOFactory xmlfactory = DAOFactory.getDAOFactory(DAOFactory.XML);
	      PreferenceDAO prefDAO = xmlfactory.getPreferenceDAO();
	     pref = prefDAO.findPreference();
		
	}


	private void createCardControllers() {
		
		this.getPreference();
		
		for (AbstractModel model: models) {

	         Column column = (Column) model;
	         LinkedList <Card> cardList = column.getCardList();
	         if(cardList!=null){
	        	 Iterator<Card> cardIterator = cardList.iterator();
		         while(cardIterator.hasNext()){
		        	 Card currentCard = cardIterator.next();
		        	
		        	 CardController currentCardController = new CardController();
		        	 CardView currentCardView = new CardView(currentCardController);
		        	 //Einstellungen der CardView werden gesetzt
		        	 currentCardView.setPreference(pref);
		        	 
		        	 
		        	 System.out.println("Spalte" + column.getName() + " Karte:" +currentCard.getHeadline());
		        	 currentCardController.addModel(currentCard);
		        	 
		        	 //currentCardController.setPreference(preference);
		        	 
		        	 currentCardController.addView(currentCardView);
		        	 cardControllers.add(currentCardController);
		        	 this.addView(currentCardView);
		         }
	         }
	         else{
	        	 throw new NullPointerException();
	         }
	          
	    }
		
	}
	
	public LinkedList<CardController> getCardControllerList(){
		return cardControllers;
	}
	
	public Column getColumn(){
		Column column = null;
		for (AbstractModel model: models) {
			 column = (Column) model;
		 }
		
		return column;
	}
	
	public LinkedList<Card> getCardList(){
		 
		LinkedList <Card> cardList = null;
		for (AbstractModel model: models) {
			Column column = (Column) model;
			cardList = column.getCardList();
		 }
		return cardList;
	 }
	
}
