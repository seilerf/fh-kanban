package edu.fh.kanban.ui.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import edu.fh.kanban.domain.AbstractModel;
import edu.fh.kanban.ui.view.AbstractView;

/**
 * Abstrakter Controller, der 
 */
public abstract class AbstractController implements PropertyChangeListener {

	
	//Eine Arrayliste die sich die Views merken kann (bzw. die Referenzen)
	protected ArrayList <AbstractView>views;
	//Eine Arrayliste die sich die Modelle merken kann (bzw. die Referenzen)
    protected ArrayList <AbstractModel>models;
  

    public AbstractController() {
        views = new ArrayList<AbstractView>();
        models = new ArrayList<AbstractModel>();
    }

    
    public void addModel(AbstractModel model) {
        models.add(model);
        model.addPropertyChangeListener(this);
    }

    public void removeModel(AbstractModel model) {
        models.remove(model);
        model.removePropertyChangeListener(this);
    }

    public void addView(AbstractView view) {
        views.add(view);
    }

    public void removeView(AbstractView view) {
        views.remove(view);
    }


    //Aufgerufen nach firePropertyChange()
    public void propertyChange(PropertyChangeEvent evt) {

        for (AbstractView view: views) {
            view.modelPropertyChange(evt);
        }
    }



    
    /** Richtung: Eingaben aus View nach Model
     *  Methode, die für  die übergebene Eigenschaft(Propertie)
     *  den Setter sucht, der den Rückgabewert von newValue hat
     *  setModelProperty(CARDID_PROPERTY,12) sucht z.B.
     *  	setCardId(int);
     *  Daraufhin wird per
     *  method.invoke(Card,12) die Methode auf dem aktuellen
     *  Modell gestartet
    */

    protected void setModelProperty(String propertyName, Object newValue) {
    	System.out.println("neuer Wert:" + newValue);
    	
    	//Durchlaufe alle Modelle
        for (AbstractModel model: models) {
        	System.out.println("Duchlaufe Modelle");
            try {
            	//Speichere Referenz auf Setter
            	//aus model, ermittle Klasse, rufe getMethod (Methodenerstellung)
            	//set+propertyName+Rückgabetyp von newValue
            	//(Der Rückgabetyp wird der neue Rückgabewert des erstellten
            	// Setters)
            	System.out.println("Suche...");
            	Method method;
				try {
					method = model.getClass().getMethod("set"+propertyName, new Class[] {
							newValue.getClass()
					   });
				
					System.out.println("Gefunden.");
                
					method.invoke(model, newValue);
				}
				 catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
            } catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
                System.out.println("gestartet");

            } catch (NullPointerException e) {
            	 System.err.println(e.getMessage());
            	 System.out.println("Konnte nicht gefunden werden!");
            }
        }
    }
    /**
    protected void resetCardViewProperty(String propertyName1, String propertyName2) {
    	Method oldValue = null;
    	Object newValue = null;
    	
        for (AbstractModel model: models) {
        	System.out.println("Duchlaufe Modelle");
            try {
            	System.out.println("Suche...");
            	//Method method = model.getClass().getMethod("get"+propertyName1);
            	oldValue = model.getClass().getMethod("get"+propertyName1);
            	System.out.println("Gefunden.");
                oldValue.invoke(model);
                System.out.println("gestartet");
                System.out.println((oldValue.toString()));

            } catch (Exception ex) {
            	 System.err.println(ex.getMessage());
            	 System.out.println("Konnte nicht gefunden werden!");
            }
           newValue = oldValue;
        }
        for(AbstractView view: views) {
    		System.out.println("Durchlaufe Views");
    		try {
    			System.out.println("Suche...");
    			Method method = view.getClass().getMethod("set"+propertyName2, new Class[] {
    					newValue.getClass()
    			});
    			System.out.println("Gefunden.");
                method.invoke(view, newValue);
                System.out.println("gestartet");
    		}  catch (Exception ex) {
    			System.err.println(ex.getMessage());
    			System.out.println("Konnte nicht gefunden werden");
    			
    		}
    	}
        
    } */

}