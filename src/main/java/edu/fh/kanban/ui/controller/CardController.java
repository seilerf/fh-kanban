package edu.fh.kanban.ui.controller;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.view.AbstractView;
import edu.fh.kanban.ui.view.CardView;


public class CardController extends AbstractController {
	
	private static Preference preference;
	public static final String CARDID_PROPERTY   = "Id";
	public static final String SIZE_PROPERTY = "Size";
	public static final String DESCRIPTION_PROPERTY = "Description";
	public static final String VALUE_PROPERTY = "Value";
	public static final String BACKGROUND_PROPERTY = "Background";
	public static final String BLOCKER_PROPERTY = "Blocker";
	public static final String JRADIOBUTTON_PROPERTY = "Date";
	public static final String CARDIDTEXTF_PROPERTY = "IdTextField";
	public static final String NEWCARD_PROPERTY = "Changed";
	

	public LinkedList<ColumnController> columnControllers;

	public LinkedList<ColumnController> getColumnControllers() {
		return columnControllers;
	}
	public void setColumnControllers(
			LinkedList<ColumnController> columnControllers) {
		this.columnControllers = columnControllers;
	}

    
    public void addCardbyColumn(PropertyChangeEvent e){
    	int i = Integer.parseInt(e.getOldValue().toString())-1;
    	System.out.println("KARTE HINZUGEFÜGT");
    	columnControllers.get(i).getCardControllerList().addLast(this);
    	columnControllers.get(i).getCardList().add((Card)models.get(0));
    	

    }
	public Card getCard(){
		Card card = null;
		for (AbstractModel model: models) {
			 card = (Card) model;
		 }
		
		return card;
	}
	
	public CardView getCardView(){
		CardView cardView = null;
		for (AbstractView view: views) {
			cardView = (CardView) view;
		 }
		
		return cardView;
	}
	
	
	public void changeCardViewValues(int newCardId, int newWorkload, String newDescription, int newValue, Color backColor, boolean newBlocker, int newJRadio){
		setModelProperty(CARDID_PROPERTY,newCardId);
		setModelProperty(SIZE_PROPERTY, newWorkload);
		setModelProperty(DESCRIPTION_PROPERTY, newDescription);
		setModelProperty(VALUE_PROPERTY, newValue);
		setModelProperty(BLOCKER_PROPERTY, newBlocker);
		setModelProperty(JRADIOBUTTON_PROPERTY, newJRadio);
	}

	public Preference getPreference() {
		return preference;
	}

	public void setPreference(Preference preference) {
		this.preference = preference;
	}

}
