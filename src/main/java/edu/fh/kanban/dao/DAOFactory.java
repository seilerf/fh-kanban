package edu.fh.kanban.dao;

public abstract class DAOFactory {
	
	public static final int XML = 1;
	public static final int HTML = 2;
	
	public abstract BoardDAO getBoardDAO();
	public abstract ColumnDAO getColumnDAO();
	public abstract CardDAO getCardDAO();
	
	public static DAOFactory getDAOFactory(
		      int whichFactory) {
		  
		    switch (whichFactory) {
		      case XML: 
		          return new XMLDAOFactory();
		      case HTML: 
		          return new HTMLDAOFactory();      
		      default: 
		          return null;
		    }
		 }
}
