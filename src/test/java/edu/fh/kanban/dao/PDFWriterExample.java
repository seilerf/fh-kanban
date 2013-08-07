package edu.fh.kanban.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import edu.fh.kanban.domain.Card;

public class PDFWriterExample {
	
	public static void main(String[] args) throws DocumentException, IOException {
		
		LinkedList<Card> list = new LinkedList<Card>();
	    //3. Parameter Value, 6. Parameter Size
	    list.add(new Card(1,  1, "a", false, 2, "Ich", null, null, null));
	    list.add(new Card(2, 10, "b", false, 5, "du",  null, null, null));
	    list.add(new Card(3, 7, "c", false, 3, "er",  null, null, null));
	    list.add(new Card(4, 4, "d", false, 8, "sie", null, null, null));
	    list.add(new Card(5, 1, "e", false, 8, "es", null, null, null));
	    list.add(new Card(6, 5, "f", false, 1, "Bla", null, null, null));
	    
	    String test = "PDFtest.pdf";
        createPdf(test, list);
        
        System.out.println("done"); 
	    
	}
	
	

    public static void createPdf(String filename, LinkedList<Card> list) throws DocumentException, IOException {
        
    	Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();

        PdfContentByte cb = writer.getDirectContent();
        BaseFont bf = BaseFont.createFont();
        
        cb.beginText();
        cb.setFontAndSize(bf, 12);
        cb.moveText(20, 805); 
        cb.showText("Backlog Export");
        cb.moveText(0, -16);
        cb.showText("");
        
       
        
        Iterator<Card> it = list.iterator();
        while(it.hasNext()){
        	
        	cb.moveText(0, -16);
        	
        	Card c = it.next();
        	
        	String writeString = c.getId()+";"+c.getValue()+";"+c.getDescription()+";"
                    +c.getBlocker()+";"+c.getSize()+";"+c.getHeadline()+";"
                    +c.getBackGround()+";"+c.getCreated()+";"+c.getStartedString()+";"+c.getDone()+"\n";
        	
        	cb.newlineShowText(writeString);
        
        }
        
        
        cb.endText();

        document.close(); 
    }
	
}
	
	


