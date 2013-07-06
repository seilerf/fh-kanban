package edu.fh.kanban.domain;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

public class Card extends AbstractModel{
	
	private int id;
	private int workload;
	private int value;
	private String description;
	private boolean blocker;
	private Calendar created;
	private Calendar started;
	private Calendar done;
	private int size;
	private String headline;
	private Color backGround;
	
		
	public Card(int id, int workload, int value, String description,
			boolean blocker, int size, String headline, Color backGround) {
		this.id = id;
		this.workload = workload;
		this.value = value;
		this.description = description;
		this.blocker = blocker;
		this.size = size;
		this.headline = headline;
		this.backGround = backGround;
	}


	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	
	
	//Getter ID
	public int getId() {
		return id;
	}
	//Setter ID
	public void setId(int id) {
		this.id = id;
	}
	//Getter Workload
	public int getWorkload() {
		return workload;
	}
	//Setter Workload
	public void setWorkload(int workload) {
		this.workload = workload;
	}
	//Getter Wert
	public int getValue() {
		return value;
	}
	//Setter Wert
	public void setValue(int value) {
		this.value = value;
	}
	//Getter Beschreibung
	public String getDescription() {
		return description;
	}
	//Setter Beschreibung
	public void setDescription(String description) {
		this.description = description;
	}
	
	//Getter Erstellungsdatum
	public Calendar getCreated() {
		return created;
	}
	//Setter Erstellungsdatum
	public void setCreated(Calendar created) {
		this.created = created;
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
	public Color setBackground(Color c) {
		this.backGround = c;
		return this.backGround;
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
		return started;
	}
	//Setter Startdatum
	public void setStarted(Calendar started) {
		this.started = started;
	}
	//Getter Datum der Fertigstellung
	public Calendar getDone() {
		return done;
	}
	//Setter Datum der Fertigstellung
	public void setDone(Calendar done) {
		this.done = done;
	}
	
	

}
