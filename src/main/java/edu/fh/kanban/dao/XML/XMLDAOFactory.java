package edu.fh.kanban.dao.XML;

import java.io.File;

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.dao.ColumnDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.PreferenceDAO;

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
			
			return new XMLBoardDAO();
		}

		@Override
		public ColumnDAO getColumnDAO() {
			
			return new XMLColumnDAO();
		}

		@Override
		public CardDAO getCardDAO() {
			
			return new XMLCardDAO();
		}

		@Override
		public PreferenceDAO getPreferenceDAO() {
			return new XMLPreferenceDAO();
		}
}
