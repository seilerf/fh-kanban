package edu.fh.kanban.domain;

import java.awt.Color;
import java.awt.SystemColor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.fh.kanban.ui.controller.CardController;

public class Card extends AbstractModel{
	
	private int id;
	private int value;
	private String description;
	private boolean blocker;
	private Calendar created;
	private Calendar started;
	private Calendar done;
	private int size;
	private String headline;
	private Color backGround;
	
		
	public Card(int id, int value, String description,
			boolean blocker, int size, String headline, Color backGround) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.blocker = blocker;
		this.size = size;
		this.headline = headline;
		this.backGround = backGround;
	}


	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	/**
	 * Setzt das CardId-Attribut auf Null.
	 */
	public void cardIdReset() {
		this.id = 0;
	}
	
	/**
	 * Setzt das Beschreibung-Attribut auf Null.
	 */
	public void beschreibungReset() {
		this.description = "";
	}
	
	/**
	 * Setzt das Aufwand-Attribut auf Null.
	 */
	public void aufwandReset() {
		this.value = 0;
	}
	
	/**
	 * Setzt das Wert-Attribut auf Null.
	 */
	public void valueReset() {
		this.value = 0;
	}
	
	
	/**
	 * Setzt das Started-Attribut auf Null.
	 */
	public void startedReset() {
		this.started = null;
	}
	
	/**
	 * Setzt das Created-Attribut auf Null.
	 */
	public void createdReset() {
		this.created = null;
	}
	
	/**
	 * Setzt das Done-Attribut auf Null.
	 */
	public void doneReset() {
		this.done = null;
	}
	
	
	//Getter ID
	public int getId() {
		return id;
	}
	//Setter ID
	public void setId(Integer id) {
		int oldId = this.id;
		this.id = id;
		firePropertyChange(CardController.CARDID_PROPERTY,oldId,id);
		System.out.println("Meine neue ID:" + this.getId());
		
	}

	//Getter Workload
	public int getSize() {
		return size;
	}
	//Setter Workload
	public void setSize(Integer size) {
		int oldSize = this.size;
		this.size = size;
		firePropertyChange(CardController.SIZE_PROPERTY,oldSize,size);
		System.out.println("Mein neuer Workload:" + this.getSize());
	}
	//Getter Wert
	public int getValue() {
		return value;
	}
	//Setter Wert
	public void setValue(Integer value) {
		int oldValue = this.value;
		this.value = value;
		firePropertyChange(CardController.SIZE_PROPERTY,oldValue,description);
		System.out.println("Mein neuer Value:" + this.getValue());
	}
	//Getter Beschreibung
	public String getDescription() {
		return description;
	}
	//Setter Beschreibung
	public void setDescription(String description) {
		String oldDescription = this.description;
		this.description = description;
		firePropertyChange(CardController.DESCRIPTION_PROPERTY,oldDescription,description);
		System.out.println("Meine neue Description:" + this.getDescription());
		
	}
	
	//Getter Erstellungsdatum
	public Calendar getCreated() {
		return created.getInstance();
	}
	
	/**
	 * Setzt das Blocker-Attribut auf False.
	 */
	public void blockerReset() {
		this.blocker = false;
	}
	/**
	 * Setter für das BackGround-Attribut.
	 * @return
	 */
	public void setBackground(Color c) {
		Color oldBackground = this.backGround;
		this.backGround = c;
		firePropertyChange(CardController.BACKGROUND_PROPERTY,oldBackground,backGround);
		System.out.println("Mein neuer Background:" + this.getBackGround());
	}
	
	public void setDate(Integer i) {
		if(i==0) {
			Calendar oldCalendarCreated = this.created;
			this.created.getInstance();
			firePropertyChange(CardController.JRADIOBUTTON_PROPERTY,oldCalendarCreated,started);
			System.out.println("Mein neues Started-Datum:" + this.getCreated());
			
		}
		if(i==1) {
			Calendar oldCalendarStarted = this.started;
			this.started.getInstance();
			firePropertyChange(CardController.JRADIOBUTTON_PROPERTY,oldCalendarStarted,started);
			System.out.println("Mein neues Started-Datum:" + this.getStarted());
		}
		if(i==2) {
			Calendar oldCalendarDone = this.done;
			this.done.getInstance();
			firePropertyChange(CardController.JRADIOBUTTON_PROPERTY,oldCalendarDone,done);
			System.out.println("Mein neues Done-Datum:" + this.getDone());
		}
	}
	
	/**
	 * Getter für das BackGround-Attribut.
	 * @return
	 */
	public Color getBackGround() {
		return this.backGround;
	}
	//Getter Startdatum
	public Calendar getStarted() {
		return started.getInstance();
	}
	
	//Getter Datum der Fertigstellung
	public Calendar getDone() {
		return done.getInstance();
	}
	
	public boolean getBlocker() {
		return this.blocker;
	}
	/**
	 * Setter für das Blocker-Attribut
	 * @param b
	 * @return 
	 * @return
	 */
	public void setBlocker(Boolean b) {
		boolean oldBlocker = this.blocker;
		this.blocker = b;
		firePropertyChange(CardController.BLOCKER_PROPERTY,oldBlocker,blocker);
		System.out.println("Mein neuer Blockerzustand:" + this.getBlocker());
	}

}
