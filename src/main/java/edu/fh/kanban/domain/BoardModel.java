package edu.fh.kanban.domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BoardModel {

	private PropertyChangeSupport changes ;
	private String name;
	private int id;
	
	public BoardModel(){
		//merkt sich die Listener
		changes = new PropertyChangeSupport(this);

	}
	
	public void setName(){
		String oldName = this.name;
		this.name = name;
		changes.firePropertyChange("name", oldName, name);
	}
	
	 public void addPropertyChangeListener(PropertyChangeListener l) {
		 changes.addPropertyChangeListener(l);
	 }


}
