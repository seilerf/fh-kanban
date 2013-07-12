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
			e.printStackTrace();
		}
 
		Document doc = builder.newDocument();
		DOMSource source = new DOMSource(doc);
 
		// Algorithmus für die Berechnung der Anzahl Zeilen, die die Tabelle besitzen soll
		int maxRows = 0;
		for(Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
			Column column = colIterator.next();
			if(column.getCards() != null && column.getCards().size() > maxRows)
				maxRows = column.getCards().size();
		}
		
		Element html = doc.createElement("html");
		Element head = doc.createElement("head");
		// Titel des HTML-Dokuments
		Element title = doc.createElement("title");
		title.setTextContent(board.getName());
		
		Element body = doc.createElement("body");
		body.setAttribute("style", "font-family:calibri, sans-serif;");
		// Überschrift und Trennung dieser vom Inhalt mit einer horizontalen Linie
		Element h1 = doc.createElement("h1");
		h1.setAttribute("style", "margin-left: 50px");
 		h1.setTextContent("Boardname: " + board.getName());
		Element hr = doc.createElement("hr");
		// Tabelle für den Inhalt des Boards
		Element table = doc.createElement("table");
		table.setAttribute("rows", String.valueOf(maxRows));
		table.setAttribute("cols", String.valueOf(board.getColumnList().size()));
		table.setAttribute("style", "border:1px solid lightgrey; margin-left: 50px; margin-top: 20px;");
		// Zeile für die Spaltenüberschriften
		Element headerRow = doc.createElement("tr");
		// "Normale" Tabellenzeile für die Kartenelemente
		Element tr = doc.createElement("tr");
		
		// Aufbau der Hierarchie des Dokuments
		doc.appendChild(html);
 		html.appendChild(head);
 		head.appendChild(title);
 		html.appendChild(body);
 		body.appendChild(h1);
 		body.appendChild(hr);
		body.appendChild(table);
 		table.appendChild(headerRow);
 		
 		// Spaltenüberschriften erstellen 
 		for(Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
 			Column column = colIterator.next();
 			Element th = doc.createElement("th");
 			th.setAttribute("style", "text-align: center; font-size: 1.5em");
 			th.setTextContent(column.getName());
 			headerRow.appendChild(th);
 		}

 		table.appendChild(tr);
 		String tdWidth = "100px";
 		// Auslesen der Karten jeder Spalte
 		for (Iterator<Column> colIterator = board.getColumnList().iterator(); colIterator.hasNext();){
 			Column column = colIterator.next();
 			Element td = doc.createElement("td");
 			td.setAttribute("padding", "3px");
 			Element ul = doc.createElement("ul");
 			ul.setAttribute("style", "list-style-type:none");
 			td.appendChild(ul);
 			if (column.getCards() != null){
 				// Für jede Karte wird ein Listenelement angelegt, das dann weitere Details der Karte enthält
 				for(Iterator<Card> cardIterator = column.getCards().iterator(); cardIterator.hasNext();){
 					Card card = cardIterator.next();
 					Element li = doc.createElement("li");
 					li.setAttribute("style", "padding: 3px; text-align: center; font-weight: bold; background-color: rgb(" 
 							+ card.getBackGround().getRed() + ","
 							+ card.getBackGround().getGreen() + ","
 							+ card.getBackGround().getBlue() + ")");
 					li.setTextContent(card.getHeadline() + "\n");
 					ul.appendChild(li);
 					
 					Element div = doc.createElement("div");
 					div.setAttribute("style", "max-width: 300px; text-align: left; font-weight: normal; border: 1px dotted black");
 					Element cardTable = doc.createElement("table");
 					Element cardTableFirstRow = doc.createElement("tr");
 					Element cardTableSecondRow = doc.createElement("tr");
 					Element cardTableThirdRow = doc.createElement("tr");
 					Element cardID = doc.createElement("td");
 					cardID.setAttribute("style", "width: " + tdWidth);
 					cardID.setTextContent("ID: " + String.valueOf(card.getId()));
 					Element cardBlocker = doc.createElement("td");
 					if (card.getBlocker())
 						cardBlocker.setAttribute("style", "text-align: center; background-color: red; width: " + tdWidth);
 					else
 						cardBlocker.setAttribute("style", "text-align: center; background-color: green; width: " + tdWidth);
 					cardBlocker.setTextContent(" ");
 					Element cardWorkload = doc.createElement("td");
 					cardWorkload.setAttribute("style", "text-align: right; width: " + tdWidth);
 					cardWorkload.setTextContent("Aufwand: " + String.valueOf(card.getWorkload()));
 					Element cardValue = doc.createElement("td");
 					cardValue.setTextContent(String.valueOf("Wert: " + card.getValue()));
 					Element cardDescription = doc.createElement("td");
 					cardDescription.setAttribute("style", "max-width: 300px");
 					cardDescription.setAttribute("colspan", "3");
 					cardDescription.setTextContent("Beschreibung: " + card.getDescription());
 					Element cardCreated = doc.createElement("td");
 					cardCreated.setAttribute("style", "width: " + tdWidth);
 					cardCreated.setTextContent("11.07.2013");
 					Element cardStarted = doc.createElement("td");
 					cardStarted.setAttribute("style", "text-align: center; width: " + tdWidth);
 					Element cardDone = doc.createElement("td");
 					cardDone.setAttribute("style", "text-align: center; width: " + tdWidth);
 					
 					li.appendChild(div);
 					div.appendChild(cardTable);
 					
 					cardTable.appendChild(cardTableFirstRow);
 					cardTableFirstRow.appendChild(cardID);
 					cardTableFirstRow.appendChild(cardBlocker);
 					cardTableFirstRow.appendChild(cardWorkload);
 					
 					cardTable.appendChild(cardTableSecondRow);
 					cardTableSecondRow.appendChild(cardDescription);
 					
 					cardTable.appendChild(cardTableThirdRow);
 					cardTableThirdRow.appendChild(cardCreated);
 					cardTableThirdRow.appendChild(cardStarted);
 					cardTableThirdRow.appendChild(cardDone);
 					
 					ul.appendChild(doc.createElement("br"));
 				}
 			}
 			tr.appendChild(td);
 		}
 
 		// Ausgabe des HTML-Codes in die übergebene Datei
		StreamResult result = new StreamResult(file);
		System.out.println("File saved!");
 
		Transformer transformer;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		//	transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC);
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
