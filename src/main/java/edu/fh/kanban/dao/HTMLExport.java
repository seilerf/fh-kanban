package edu.fh.kanban.dao;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
 
public class HTMLExport {
 
	public void htmlOutput(Board board, File file){
 
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbfactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		Document doc = builder.newDocument();
		DOMSource source = new DOMSource(doc);
 
		Element html = doc.createElement("html");
		Element head = doc.createElement("head");
		Element title = doc.createElement("title");
		title.setTextContent(board.getName());
		Element body = doc.createElement("body");
		Element h1 = doc.createElement("h1");
		Element table = doc.createElement("table");
		
		int maxRows = 0;
		for(Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
			Column column = colIterator.next();
			if(column.getCards() != null && column.getCards().size() > maxRows)
				maxRows = column.getCards().size();
		}
		
		table.setAttribute("rows", String.valueOf(maxRows));
		table.setAttribute("cols", String.valueOf(board.getColumnList().size()));
		table.setAttribute("style", "border:1px solid lightgrey; ");
		//Element tr = doc.createElement("tr");
		//Element th = doc.createElement("th");
		//Element td = doc.createElement("td");
		Element hr = doc.createElement("hr");
		
		doc.appendChild(html);
 		html.appendChild(head);
 		head.appendChild(title);
 		html.appendChild(body);
 		
 		body.appendChild(h1);
 		body.appendChild(hr);
 		body.appendChild(table);
 		
 		Element headerRow = doc.createElement("tr");
 		table.appendChild(headerRow);
 		
 		for(Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
 			Column column = colIterator.next();
 			Element th = doc.createElement("th");
 			th.setTextContent(column.getName());
 			headerRow.appendChild(th);
 		}
 		
 		Element tr = doc.createElement("tr");
 		table.appendChild(tr);
 		for (Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
 			Column column = colIterator.next();
 			Element td = doc.createElement("td");
 			td.setAttribute("padding", "3px");
 			Element ul = doc.createElement("ul");
 			td.appendChild(ul);
 			if (column.getCards() != null){
 				for(Iterator<Card> cardIterator = column.getCards().iterator(); cardIterator.hasNext();){
 					Card card = cardIterator.next();
 					Element li = doc.createElement("li");
 					li.setAttribute("style", "padding: 3px; background-color: rgb(" 
 							+ card.getBackGround().getRed() + ","
 							+ card.getBackGround().getGreen() + ","
 							+ card.getBackGround().getBlue() + ")");
 					li.setTextContent(card.getHeadline());
 					td.appendChild(li);
 					Element div = doc.createElement("div");
 					div.setAttribute("style", "max-width: 200px");
 					div.setTextContent("Beschreibung: " + card.getDescription());
 					li.appendChild(div);
 				}
 			}
 			tr.appendChild(td);
 		}
 		
 		h1.setTextContent("Boardname: " + board.getName());
 
		StreamResult result = new StreamResult(file);
		System.out.println("File saved!");
 
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		//	transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
