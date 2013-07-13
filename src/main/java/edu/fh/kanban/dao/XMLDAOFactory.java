package edu.fh.kanban.dao;

import java.io.File;

public class XMLDAOFactory extends DAOFactory {
	
			//Variablen für Verbindung/Datei
		private File file;
		  
		  public static File openFile() {
		   	return null;
		  }
		  
		  public static File closeFILE(){
			 return null; 
		  }
		  
		  public static File saveFILE(){
			 return null; 
		  }
	

		@Override
		public BoardDAO getBoardDAO() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ColumnDAO getColumnDAO() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CardDAO getCardDAO() {
			// TODO Auto-generated method stub
			return null;
		}
}
