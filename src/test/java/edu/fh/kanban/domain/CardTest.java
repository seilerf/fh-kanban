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
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
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
		
		card = new Card(id, value, description, blocker, size, headline, created, started, done);
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
		String testString = card.setCreated(new Date());
		assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getCreatedString());
		
	}
	
	@Test
	public void testGetStarted() throws ParseException { 
	
		String testString = card.setStarted(new Date());
		assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getStartedString());
	}
	
	@Test
	public void testGetDone() throws ParseException { 
		
		String testString = card.setDone(new Date());
		System.out.println(testString);
		System.out.println(card.getDone());
		assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getDoneString());
	}
	
	@Test
	public void testSetDate() throws ParseException {
		int i = testNumber;
		String testString;
		if(i==0) {
			testString = card.setCreated(new Date());
		
			assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getCreatedString());
		}
		if(i==1) {
			testString = card.setStarted(new Date());
		
			assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getStartedString());
		}
		if(i==2) {
			testString = card.setDone(new Date());
		
			assertEquals("Hat sich das Datum der Karte geändert?", testString, card.getDone());
		}
	}
}
