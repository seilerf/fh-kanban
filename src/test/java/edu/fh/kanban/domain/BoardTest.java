package edu.fh.kanban.domain;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Column;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class BoardTest {
	private static Board board;
	private static LinkedList<Column> columnList;
	private static String boardName = "TestBoard";
	
	@BeforeClass
	public static void setBeforeTesting() {
		board = new Board();
	}
	
	@Test
	public void testName() {
		board.setName(boardName);
		assertEquals("Name des Boards geändert?", boardName, board.getName());
	}
	
	@Test
	public void testColumnList() {
		columnList = new LinkedList<Column>();
		
		String name = "TestSpalte ";
		int limit = 3;
		for (int i = 1; i < 4; i++)
			columnList.add(new Column(name+i, limit));
		
		assertTrue(board.getColumnList() == null);
		board.setColumnList(columnList);
		assertFalse(board.getColumnList() == null);
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(BoardTest.class);
	}
}