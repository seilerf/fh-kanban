package edu.fh.kanban.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;

import edu.fh.kanban.domain.Card;




public class CsvWriterAppendExample {

	public static void main(String[] args) {
		
		LinkedList<Card> list = new LinkedList<Card>();
	    //3. Parameter Value, 6. Parameter Size
	    list.add(new Card(1,  1, "a", false, 2, "Ich", null, null, null));
	    list.add(new Card(2, 10, "b", false, 5, "du", null, null, null));
	    list.add(new Card(3, 7, "c", false, 3, "er", null, null, null));
	    list.add(new Card(4, 4, "d", false, 8, "sie", null, null, null));
	    list.add(new Card(5, 1, "e", false, 8, "es", null, null, null));
	    list.add(new Card(6, 5, "f", false, 1, "Bla", null, null, null));
	    
	    writeListIntoCSV(list);
		
		 
		
	}
	
	public static void writeListIntoCSV(LinkedList<Card> list){
		
		boolean status = false; //Rückgabewert wird true falls geschrieben werden konnte
	    String SpeicherString ="wenn Sie dies in der Datei lesen ist ein Fehler passiert";
	        
	    Iterator<Card> it = list.iterator();
	    BufferedWriter bw = null;
	    
	        try{
	            bw = new BufferedWriter(new FileWriter("test.csv")); 
	        
	            while(it.hasNext()){
	                
	            	Card c = it.next();
	            	
	                SpeicherString=c.getId()+";"+c.getValue()+";"+c.getDescription()+";"
	                    +c.getBlocker()+";"+c.getSize()+";"+c.getHeadline()+";"
	                    +c.getBackGround()+";"+c.getCreated()+";"+c.getStartedString()+";"+c.getDone()+"\n"; // ; ist Trennzeichen, evtl durch globale Variable ersetzen
	            
	                bw.write(SpeicherString); //String schreiben
	                bw.flush();//Puffer leeren
	            }
	            bw.close();//BufferWriter schliessen
	            status=true;
	            
	            System.out.println("Schreiben erfolgreich!");
	        }catch (Exception e){}
	       
		
	}
}
