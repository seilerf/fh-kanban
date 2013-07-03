package edu.fh.kanban.dao;

import edu.fh.kanban.domain.XMLReader;

public class DataManager {

	public DataManager(){
		
	}
	
	public void readXML(){
		XMLReader xmlreader = new XMLReader();
		xmlreader.read();
	}
	
	
	public void writeXML(){
		
	}
	
	public void writePDF(){
		
	}
	
}
