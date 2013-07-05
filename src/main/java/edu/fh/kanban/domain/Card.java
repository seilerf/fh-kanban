package edu.fh.kanban.domain;

import java.util.Date;

public class Card extends AbstractModel{
	
	private int id;
	private int workload;
	private int value;
	private String description;
	private Blocker blocker;
	private Date created;
	private Date started;
	private Date done;
	
	
	//Constuctor
	public Card() {
		
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
	//Getter Blocker
	public Blocker getBlocker() {
		return blocker;
	}
	//Setter Blocker
	public void setBlocker(Blocker blocker) {
		this.blocker = blocker;
	}
	//Getter Erstellungsdatum
	public Date getCreated() {
		return created;
	}
	//Setter Erstellungsdatum
	public void setCreated(Date created) {
		this.created = created;
	}
	//Getter Startdatum
	public Date getStarted() {
		return started;
	}
	//Setter Startdatum
	public void setStarted(Date started) {
		this.started = started;
	}
	//Getter Datum der Fertigstellung
	public Date getDone() {
		return done;
	}
	//Setter Datum der Fertigstellung
	public void setDone(Date done) {
		this.done = done;
	}

}
