package edu.fh.kanban.dao.PDF;

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.dao.ColumnDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.PreferenceDAO;

public class PDFDAOFactory extends DAOFactory {

	@Override
	public BoardDAO getBoardDAO() {
		
		return null;
	}

	@Override
	public ColumnDAO getColumnDAO() {
		
		return null;
	}

	@Override
	public CardDAO getCardDAO() {
		CardDAO pdfCardDAO = new PDFCardDAO();
		return pdfCardDAO;
	}

	@Override
	public PreferenceDAO getPreferenceDAO() {
	
		return null;
	}

}
