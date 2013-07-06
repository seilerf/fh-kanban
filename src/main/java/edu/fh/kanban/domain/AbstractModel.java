package edu.fh.kanban.domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel {
	
	protected PropertyChangeSupport changes;
		
		//Konstruktur erzeugt für jedes Model ein Änderungsobjekt
	    public AbstractModel() {
	        changes = new PropertyChangeSupport(this);
	    }
	    //Methode zum Hinzufügen von Hörern
	    public void addPropertyChangeListener(PropertyChangeListener listener) {
	        changes.addPropertyChangeListener(listener);
	    }
	    //Methode zum Entfernen von Hörern
	    public void removePropertyChangeListener(PropertyChangeListener listener) {
	        changes.removePropertyChangeListener(listener);
	    }
	    //Methode zum Auslösen eines Änderungs-Events
	    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	        changes.firePropertyChange(propertyName, oldValue, newValue);
	    }
	    
	    public static void callModelMethod(String name){
	    	
	    }
}
