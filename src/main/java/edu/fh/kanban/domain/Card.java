package edu.fh.kanban.domain;

import java.awt.Color;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.fh.kanban.ui.controller.CardController;

public class Card extends AbstractModel {
	
	private int id;
	private int value;
	private String description;
	private boolean blocker;
	private Date created;
	private Date started;
	private Date done;
	private int size;
	private String headline;
	private Color backGround;
	
																												
	public Card(int id, int value, String description, boolean blocker, int size, String headline, Color backGround, Date created, Date started, Date done) {
		this.id = id;
		this.value = value;
		this.description = description;
		this.blocker = blocker;
		this.size = size;
		this.headline = headline;
		this.backGround = backGround;
		this.created = created;
		this.started = started;
		this.done = done;
	}

	//Getter Headline
	public String getHeadline() {
		return headline;
	}
	
	//Setter Headline
	public void setHeadline(String headline) {
		this.headline = headline;
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
	
	public void setDate(Integer i) throws ParseException {
		if(i==0) {
			Date oldCalendarCreated = setCreated();
			firePropertyChange(CardController.JRADIOBUTTON_PROPERTY,oldCalendarCreated,started);
			System.out.println("Mein neues Started-Datum:" + this.getCreated());
			
		}
		if(i==1) {
			Date oldCalendarStarted = setStarted();
			firePropertyChange(CardController.JRADIOBUTTON_PROPERTY,oldCalendarStarted,started);
			System.out.println("Mein neues Started-Datum:" + this.getStarted());
		}
		if(i==2) {
			Date oldCalendarDone = setDone();
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
	//Getter Erstellungsdatum
	public Date getCreated() {
			return created;
	}
	//Setter Erstellungsdatum
	public Date setStarted() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		this.started = sdf.parse(date);
		return started;
	}
	//Getter Startdatum
	public Date getStarted() {
		return started;
	}
	//Setter Startdatum
	public Date setCreated() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		this.created = sdf.parse(date);
		return created;
	}
	//Getter Datum der Fertigstellung
	public Date getDone() {
		return done;
	}
	//Setter Datum der Fertigstellung
	public Date setDone() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date = sdf.format(new Date());
		this.done = sdf.parse(date);
		return done;
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
