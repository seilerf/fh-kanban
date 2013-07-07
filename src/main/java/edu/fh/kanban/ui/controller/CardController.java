package edu.fh.kanban.ui.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Callable;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.view.CardView;
/**
 *  Der CardController ke
 * 
 *
 */
public class CardController extends AbstractController {
	
	
	public static final String CARDID_PROPERTY   = "Id";
	public static final String WORKLOAD_PROPERTY = "Workload";
	public static final String DESCRIPTION_PROPERTY = "Description";
	public static final String VALUE_PROPERTY = "Value";
	public static final String BACKGROUND_PROPERTY = "Background";
	public static final String BLOCKER_PROPERTY = "Blocker";
	public static final String JRADIOBUTTON_PROPERTY = "Date";

	public void changeCardId(int newCardId, int newWorkload, String newDescription, int newValue, Color backColor, boolean newBlocker, int newJRadio){
		setModelProperty(CARDID_PROPERTY,newCardId);
		setModelProperty(WORKLOAD_PROPERTY, newWorkload);
		setModelProperty(DESCRIPTION_PROPERTY, newDescription);
		setModelProperty(VALUE_PROPERTY, newValue);
		setModelProperty(BACKGROUND_PROPERTY, backColor);
		setModelProperty(BLOCKER_PROPERTY, newBlocker);
		setModelProperty(JRADIOBUTTON_PROPERTY, newJRadio);
	}
	
	public void deleteCardViewValues(int newCardId, int newWorkload, String newDescription, int newValue, Color backColor, boolean newBlocker, int newJRadio) {
		setModelProperty(CARDID_PROPERTY,newCardId);
		setModelProperty(WORKLOAD_PROPERTY, newWorkload);
		setModelProperty(DESCRIPTION_PROPERTY, newDescription);
		setModelProperty(VALUE_PROPERTY, newValue);
		setModelProperty(BACKGROUND_PROPERTY, backColor);
		setModelProperty(BLOCKER_PROPERTY, newBlocker);
		setModelProperty(JRADIOBUTTON_PROPERTY, newJRadio);
	}

}
