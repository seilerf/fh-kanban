package edu.fh.kanban.dao;

import edu.fh.kanban.dao.XML.XMLDAOFactory;

public abstract class DAOFactory {
	
	public static final int XML = 1;
	
	
	public abstract BoardDAO getBoardDAO();
	public abstract ColumnDAO getColumnDAO();
	public abstract CardDAO getCardDAO();
	public abstract PreferenceDAO getPreferenceDAO();
	
	public static DAOFactory getDAOFactory(
		      int whichFactory) {
		  
		    switch (whichFactory) {
		      case XML: 
		          return new XMLDAOFactory();    
		      default: 
		          return null;
		    }
		 }
}
