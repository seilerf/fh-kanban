package edu.fh.kanban.domain;

import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.ui.controller.BoardController;

public class Board extends AbstractModel {


	private String name;
	private int id;
	private Preference preference;

	private LinkedList<Column> columnList;
	
	public Board(){
		//merkt sich die Listener
		changes = new PropertyChangeSupport(this);

	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	public void setChanged() {
		firePropertyChange(BoardController.BOARDCHANGED_PROPERTY,null,null);		
	}
	

	
	

	/**
	 * @param columnList the columnList to set
	 */
	public void setColumnList(LinkedList<Column> columnList) {
		this.columnList = columnList;
	}

	public void setName(String name){
		String oldName = this.name;
		this.name = name;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * @return the columnList
	 */
	public LinkedList<Column> getColumnList() {
		return columnList;
	}
	
	
	
	public LinkedList<Card> getCards(){
		//Kartenliste anlegen
		LinkedList<Card> cards = new LinkedList<>();
		Column currentColumn;
		
		try{
			//Iterator über die Spalten
			Iterator<Column> colIt = columnList.iterator();
			
			while(colIt.hasNext()){
				System.out.println("Spalte ...\n");
				//Läuft über die Spalten
				currentColumn = colIt.next();
				//erstellt pro Spalte einen neuen Iterator für die Karten
				Iterator<Card> cardIt;
				try{
					if((cardIt = currentColumn.getCardList().iterator())!=null){
					Card currentCard;
					while(cardIt.hasNext()){
						System.out.println("Lese Karte...\n");
						currentCard = cardIt.next();
						cards.add(currentCard);
						}
					}
				}
				catch(NullPointerException e){
					System.out.println("Diese Spalte enthält keine Karten");
					e.printStackTrace();
				}

			}
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		
		return cards;
		
	}
	
	

}
