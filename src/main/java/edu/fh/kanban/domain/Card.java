package edu.fh.kanban.domain;

import java.util.Date;

public class Card {
	
	private int id;
	private int workload;
	private int value;
	private String description;
	private Blocker blocker;
	private Date created;
	private Date started;
	private Date done;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkload() {
		return workload;
	}
	public void setWorkload(int workload) {
		this.workload = workload;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blocker getBlocker() {
		return blocker;
	}
	public void setBlocker(Blocker blocker) {
		this.blocker = blocker;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Date getDone() {
		return done;
	}
	public void setDone(Date done) {
		this.done = done;
	}
	
	
	
	 
	
}
