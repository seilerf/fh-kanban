package edu.fh.kanban.ui.controller;

import java.awt.Color;


public class CardController extends AbstractController {
	
	
	public static final String CARDID_PROPERTY   = "Id";
	public static final String SIZE_PROPERTY = "Size";
	public static final String DESCRIPTION_PROPERTY = "Description";
	public static final String VALUE_PROPERTY = "Value";
	public static final String BACKGROUND_PROPERTY = "Background";
	public static final String BLOCKER_PROPERTY = "Blocker";
	public static final String JRADIOBUTTON_PROPERTY = "Date";
	public static final String CARDIDTEXTF_PROPERTY = "IdTextField";

	public void changeCardViewValues(int newCardId, int newWorkload, String newDescription, int newValue, Color backColor, boolean newBlocker, int newJRadio){
		setModelProperty(CARDID_PROPERTY,newCardId);
		setModelProperty(SIZE_PROPERTY, newWorkload);
		setModelProperty(DESCRIPTION_PROPERTY, newDescription);
		setModelProperty(VALUE_PROPERTY, newValue);
		setModelProperty(BACKGROUND_PROPERTY, backColor);
		setModelProperty(BLOCKER_PROPERTY, newBlocker);
		setModelProperty(JRADIOBUTTON_PROPERTY, newJRadio);
	}

}
