package edu.fh.kanban.ui.view;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.LinkedList;
import edu.fh.kanban.ui.controller.ColumnController;

public class ColumnView extends AbstractView {
	
	
	private LinkedList<CardView> cardViews = new LinkedList<CardView>(); 
	private ColumnController columnController;
	public ColumnView(ColumnController columnController){
		this.columnController = columnController;
		initComponents();
	}
	
	private void initComponents(){
		Iterator<CardView> cardIterator = cardViews.iterator();
		while(cardIterator.hasNext()){
			CardView currentCardView = cardIterator.next();
			this.add(currentCardView);
			
		}
	}
	
	
	public LinkedList<CardView> getCardViews() {
		return cardViews;
	}

	public void setCardViews(LinkedList<CardView> cardViews) {
		this.cardViews = cardViews;
	}
	
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}



}
