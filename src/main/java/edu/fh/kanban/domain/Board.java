package edu.fh.kanban.domain;

import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

import edu.fh.kanban.ui.controller.BoardController;

public class Board extends AbstractModel {


	private String name;
	private int id;
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

}
