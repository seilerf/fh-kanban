package edu.fh.kanban.domain;

import java.beans.PropertyChangeSupport;

public class Board extends AbstractModel {

	private PropertyChangeSupport changes ;
	private String name;
	private int id;
	
	public Board(){
		//merkt sich die Listener
		changes = new PropertyChangeSupport(this);

	}
	
	public void setName(){
		String oldName = this.name;
		this.name = name;
		changes.firePropertyChange("name", oldName, name);
	}
	

}
