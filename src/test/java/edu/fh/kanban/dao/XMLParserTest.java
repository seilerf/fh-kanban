package edu.fh.kanban.dao;

import java.awt.Color;
import java.io.File;
import java.text.ParseException;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import static org.junit.Assert.*;

public class XMLParserTest {
	
	/**private XMLParser xmlParser = new XMLParser();
	private File file = new File("board.xml");
	private Board board = xmlParser.readBoardFromXML(file);
	
	@Test
	public void testBoardImport() throws ParseException {
		assertEquals("Vom Typ Board", "Board", xmlParser.readBoardFromXML(file).getClass().getSimpleName());
		assertEquals("Boardname.", "TestBoard", this.board.getName());
	}

	@Test
	public void testColumnImport() {
		Column firstColumn = new Column("Next", 3, null);
		assertEquals("Korrekter Datentyp?", firstColumn.getClass(), this.board.getColumnList().getFirst().getClass());
		assertEquals("Erste Spalte \"Next\"", firstColumn.getName(), this.board.getColumnList().getFirst().getName());
	}
	
	@Test
	public void testCardImport() {
		Card firstCard = new Card(1, 2, "Kartenbeschreibung", false, 3, "Karte 1", new Color(2), null, null, null);
		assertEquals("Korrekter Datentyp?", firstCard.getClass(), this.board.getColumnList().getFirst().getCards().getFirst().getClass());
		assertEquals("Erste Karte \"Karte 1\"", firstCard.getHeadline(), this.board.getColumnList().getFirst().getCards().getFirst().getHeadline());
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(XMLParserTest.class);
	}

*/
}