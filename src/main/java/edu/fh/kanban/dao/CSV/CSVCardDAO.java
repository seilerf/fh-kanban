package edu.fh.kanban.dao.CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;

import org.jdom2.Element;

import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.domain.Card;

public class CSVCardDAO implements CardDAO {


	@Override
	public Card findCard(Element column, int i) throws ParseException {

		return null;
	}

	@Override
	public int insertCard(Card c) {
		boolean status = false; //der Status ist zunächst auf false setzt
	    String SpeicherString ="wenn Sie dies in der Datei lesen ist ein Fehler passiert";

	        try{

	            	
	                SpeicherString=c.getId()+";"+c.getValue()+";"+c.getDescription()+";"
	                    +c.getBlocker()+";"+c.getSize()+";"+c.getHeadline()+";"
	                    +c.getBackGround()+";"+c.getCreated()+";"+c.getStarted()+";"+c.getDone()+"\n"; // ; ist Trennzeichen
	                
	                CSVUtil.bw.write(SpeicherString); //String schreiben
	                CSVUtil.bw.flush();//Puffer leeren
	            
	             
	            status=true;
	            
	            System.out.println("Schreiben erfolgreich!");
	        }catch (Exception e){}
	        return 1;
	}

	@Override
	public boolean deleteCard() {
		
		return false;
	}

	@Override
	public boolean updateCard() {
	
		return false;
	}

}
