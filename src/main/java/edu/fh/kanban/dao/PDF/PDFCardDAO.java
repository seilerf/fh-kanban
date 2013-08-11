package edu.fh.kanban.dao.PDF;

import java.io.IOException;
import java.text.ParseException;
import org.jdom2.Element;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.domain.Card;

public class PDFCardDAO implements CardDAO{

	public PDFCardDAO() {}
	
	@Override
	public Card findCard(Element column, int i) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int insertCard(Card card) {
		
		try{			           
			        		 PDFUtil.document.newPage(); 
			        		 PDFUtil.cb = PDFUtil.writer.getDirectContent();
			        		 try {
								PDFUtil.bf = BaseFont.createFont();
							} catch (IOException e) {
								e.printStackTrace();
							}
			        	     
			        		 PDFUtil.cb.beginText();
			     	        PDFUtil.cb.setFontAndSize(PDFUtil.bf, 12);
			     	        PDFUtil.cb.moveText(20, 805); 
			     	        PDFUtil.cb.showText("Backlog Export");
			        	   
			        		 writeCardintoPDF(card);

			        PDFUtil.cb.endText();
  
				} catch (DocumentException e) {
					e.printStackTrace();
				}
		         
		        return 1;
	}

	public void writeCardintoPDF(Card card){
		
		PDFUtil.cb.moveText(0, -32);
	       
		String first = "ID: "+card.getId()+"      Name: "+card.getHeadline();
		PDFUtil.cb.newlineShowText(first);
		PDFUtil.cb.moveText(0, -16);
		
		String desc = card.getDescription();
		PDFUtil.cb.newlineShowText(desc);
		PDFUtil.cb.moveText(0, -16);
		
		String next = "value: "+card.getValue()+"   size: "+card.getSize()+"   blocker: "+card.getBlocker()+"   color: "+card.getBackGround();
		PDFUtil.cb.newlineShowText(next);
		PDFUtil.cb.moveText(0, -16);
		
		String created = "created: "+card.getCreated();
		PDFUtil.cb.newlineShowText(created);
		PDFUtil.cb.moveText(0, -16);
		
		String started = "started: "+card.getStarted();
		PDFUtil.cb.newlineShowText(started);
		PDFUtil.cb.moveText(0, -16);
		
		String done = "done: "+card.getDone();
		PDFUtil.cb.newlineShowText(done);
		PDFUtil.cb.moveText(0, -16);
		
	}




	@Override
	public boolean deleteCard() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateCard() {
		// TODO Auto-generated method stub
		return false;
	}

}
