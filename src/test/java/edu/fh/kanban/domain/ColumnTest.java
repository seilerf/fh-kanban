package edu.fh.kanban.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;
import junit.framework.JUnit4TestAdapter;

public class ColumnTest {
	
	private Column column;
	private LinkedList<Card> cardList; 
	
	@Test
	public void testSetterGetter() {
		String name = "TestColumn";
		int limit = 4;
		this.cardList = null;
		
		this.column = new Column(name, limit);
		
		assertEquals("Name der Spalte. ", name, this.column.getName());
		assertEquals("Limit der Spalte", limit, this.column.getLimit());
	}
	
	@Test
	public void testCardList(){
		String colName = "TestSpalte";
		int limit = 4;
		this.cardList = new LinkedList<Card>();
		
		for (int i = 1; i < 5; i++)
			this.cardList.add(new Card(2, 5, "Kartenbeschreibung", true, 2, "Karte "+i, null, null, null));
		
		this.column = new Column(colName, limit);
		assertFalse(cardList.isEmpty());
	}
	
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(ColumnTest.class);
	}

}
