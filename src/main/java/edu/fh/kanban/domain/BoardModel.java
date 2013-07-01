package edu.fh.kanban.domain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BoardModel {

	private PropertyChangeSupport support ;
	private String name;
	private int id;
	
	public BoardModel(){
		//merkt sich die Listener
		support = new PropertyChangeSupport(this);

	}
	
	 public void addPropertyChangeListener(PropertyChangeListener listener) {
		 support.addPropertyChangeListener(listener);
	 }


}
