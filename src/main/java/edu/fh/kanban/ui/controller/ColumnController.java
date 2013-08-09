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
	
	public Preference getPreference(){
		  DAOFactory xmlfactory = DAOFactory.getDAOFactory(DAOFactory.XML);
	      PreferenceDAO prefDAO = xmlfactory.getPreferenceDAO();
	     return pref = prefDAO.findPreference();
		
	}


	private void createCardControllers() {
		
		this.getPreference();
		
		for (AbstractModel model: models) {

	         Column column = (Column) model;
	         LinkedList <Card> cardList = column.getCardList();
	         if(cardList!=null){
	        	 Iterator<Card> cardIterator = cardList.iterator();
	        	 
	        	 //Durchlaufe die Karten
			         while(cardIterator.hasNext()){
			        	 Card currentCard = cardIterator.next();
			        	 //Erstelle CardController
			        	 CardController currentCardController = new CardController();
			        	 //Erstelle CardView
			        	 CardView currentCardView = new CardView(currentCardController);//-->kritisch!!!
			       
			        	 System.out.println("aktuelle Karte:" + currentCard.getId());
			        	 currentCardView.setPreference(this.pref);
			        	 
			        	 //Einstellungen der CardView werden gesetzt (u.a. die Farben)
			        	 //currentCardView.setPreference(pref);
			        	 System.out.println("Hallo" + pref.getColorExpedite()[2] + "\n");
			        	 
			        	 
			        	 
			        	 System.out.println("Spalte" + column.getName() + " Karte:" +currentCard.getHeadline());
			        	 
			        	 //Das Model (Column) wird dem Controller (currentCardController) hinzugefügt
			        	 currentCardController.addModel(currentCard);
			        	 //Der CardView wird dem CardController zugeordnet
			        	 currentCardController.addView(currentCardView);
			        	 currentCard.addPropertyChangeListener(currentCardController);
			        	 
			        	 //Der CardController wird der List von CardController hinzugefügt
			        	 cardControllers.add(currentCardController);
			         }
		         this.setCardControllers(cardControllers);
		       
	         }
	         else{
	        	 System.out.println("Es gab eine NullPointerException!!");
	        	 throw new NullPointerException();
	        	 
	         }
	          
	    }
		
	}
	
	public LinkedList<CardController> getCardControllerList(){
		return cardControllers;
	}
	
	public LinkedList<CardController> getCardControllers() {
		return cardControllers;
	}

	public void setCardControllers(LinkedList<CardController> cardControllers) {
		this.cardControllers = cardControllers;
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
