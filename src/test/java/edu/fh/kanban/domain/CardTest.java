package edu.fh.kanban.domain;

import static org.junit.Assert.*;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.JUnit4TestAdapter;

import org.junit.BeforeClass;
import org.junit.Test;

public class CardTest {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static String date = sdf.format(new Date());
	private static Card card;
	private static int id = 5;
	private static int value = 4;
	private static String description = "Testfall Card";
	private static boolean blocker = true;
	private static Date created;
	private static Date started;
	private static Date done;
	private static int size;
	private static String headline = "TestCard";
	private static Color backGround = Color.orange;
	private static Integer testNumber = 1;
	
	
	@BeforeClass
	public static void setBeforeTesting() throws ParseException {
		id = 0;
		value = 0;
		description = null;
		blocker = false;
		created =  null;
		started = null;
		done = null;
		size = 2;
		headline = null;
		backGround = null;
		
		card = new Card(id, value, description, blocker, size, headline, backGround, created, started, done);
	}
	
	@Test
	public void testSetHeadline() {
		card.setHeadline(headline);
		assertEquals("Hat sich der Name der Karte geändert?", headline, card.getHeadline());
	}
	
	@Test
	public void testSetId() {
		card.setId(id);
		assertEquals("Hat sich die Id der Karte geändert?", id, card.getId());
	}
	
	@Test
	public void testSetVale() {
		card.setValue(value);
		assertEquals("Hat sich der Aufwand der Karte geändert?", value, card.getValue());
	}
	
	@Test
	public void testSetDescription() {
		card.setDescription(description);
		assertEquals("Hat sich die Beschreibung der Karte geändert?", description, card.getDescription());
	}
	
	@Test
	public void testSetBackgroundColor() {
		card.setBackground(backGround);
		assertEquals("Hat sich die Hintergrundfarbe der Karte geändert?", backGround, card.getBackGround());
	}
	
	@Test
	public void testSetBlocker() {
		card.setBlocker(blocker);
		assertEquals("Hat sich der Zustand des Blockers der Karte geändert?", blocker, card.getBlocker());
	}
	
	@Test
	public void testGetCreated() throws ParseException { 
		card.setCreated();
		Date referenz = sdf.parse(date);
		assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getCreated());
	}
	
	@Test
	public void testGetStarted() throws ParseException { 
		card.setStarted();
		Date referenz = sdf.parse(date);
		assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getStarted());
	}
	
	@Test
	public void testGetDone() throws ParseException { 
		card.setDone();
		Date referenz = sdf.parse(date);
		assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getDone());
	}
	
	@Test
	public void testSetDate() throws ParseException {
		int i = testNumber;
		
		if(i==0) {
			card.setCreated();
			Date referenz = sdf.parse(date);
			assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getCreated());
		}
		if(i==1) {
			card.setStarted();
			Date referenz = sdf.parse(date);
			assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getStarted());
		}
		if(i==2) {
			card.setDone();
			Date referenz = sdf.parse(date);
			assertEquals("Hat sich das Datum der Karte geändert?", referenz, card.getDone());
		}
	}
}
