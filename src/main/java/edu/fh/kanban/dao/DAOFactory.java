package edu.fh.kanban.dao;

import edu.fh.kanban.dao.CSV.CSVDAOFactory;
import edu.fh.kanban.dao.PDF.PDFDAOFactory;
import edu.fh.kanban.dao.XML.XMLDAOFactory;

public abstract class DAOFactory {
	
	public static final int XML = 1;
	public static final int PDF = 2;
	public static final int CSV = 3;
	
	
	public abstract BoardDAO getBoardDAO();
	public abstract ColumnDAO getColumnDAO();
	public abstract CardDAO getCardDAO();
	public abstract PreferenceDAO getPreferenceDAO();
	
	public static DAOFactory getDAOFactory(
		      int whichFactory) {
		  
		    switch (whichFactory) {
		      case XML: 
		          return new XMLDAOFactory();
		      case PDF:
		    	  return new PDFDAOFactory();
		      case CSV:
		    	  return new CSVDAOFactory();
		      default: 
		          return null;
		    }
		 }
}
