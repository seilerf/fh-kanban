package edu.fh.kanban.ui;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JRadioButton;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.ui.controller.CardController;
import edu.fh.kanban.ui.view.CardView;
import edu.fh.kanban.ui.view.PreferencesView;
import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


public class CardViewTest {
	private static CardView cardView;
	private static CardView referenz;
	private static CardController cardController;
	private static PreferencesView prefView;
	private static int oldId;
	private static Integer cardId;
	private static Integer size;
	private static String description;
	private static int value;
	private static Color backColor;
	private static boolean blocker;
	private static int jRadio;
	private static int randomNumber;
	private static boolean rdbtnCreated;
	private static boolean rdbtnStarted;
	private static boolean rdbtnDone;
	private static boolean rdbtnReferenz;

	public CardViewTest(){
		
	}
	@BeforeClass
	public static void setBeforeTesting() {
		cardView = new CardView(cardController);
		referenz = new CardView(cardController);
		oldId = 23;
		cardId = 10;
		size = 4;
		description = "TestCardView";
		value = 1;
		backColor = Color.blue;
		blocker = true;
		jRadio = 2;
		randomNumber = 1;
		rdbtnCreated = true;
		rdbtnStarted = false;
		rdbtnDone = false;
		
	}
	
	@Test
	public void testSetCardTitel() {
		cardView.setCardTitel("");
		assertEquals("Hat sich der Titel der Kartenansicht verändert?","", cardView.getCardTitel());
	}
	
	@Test
	public void testSetOldId() {
		cardView.setOldCardId(oldId);
		assertEquals("Hat sich die OldID der Kartenansicht verändert?",oldId,cardView.getOldCardId());
	}
	
	@Test
	public void testSaveAllOldValues() {
		cardView.saveAllOldValues(cardId, size, description, value, backColor, blocker, jRadio);
		referenz.saveAllOldValues(10, 4, "TestCardView", 1, Color.blue, true, 2);
		assertEquals("Wurden die IdWerte passend gespeichert?",cardView.getOldCardId(),referenz.getOldCardId());
		//Für die anderen Werte könnten ebenfalls noch die Abfragen erfolgen
	}
	
	@Test
	public void testSetAllDisabledForBoard() {
		cardView.setAllDisabledForBoard();
		referenz.setAllDisabledForBoard();
		assertEquals("Wurden die Komponenten auf den selben Zustand gesetzt?",cardView.getSizeTextField().isEnabled(),referenz.getSizeTextField().isEnabled());
	}
	
	@Test
	public void testSetAllEnabledForBoard() {
		cardView.setAllEnabledForBoard();
		referenz.setAllEnabledForBoard();
		assertEquals("Wurden die Komponenten auf den selben Zustand gesetzt?",cardView.getSizeTextField().isEnabled(),referenz.getSizeTextField().isEnabled());
	}
	
	@Test
	public void testSetJPanelColor() {

		if(randomNumber == 0||randomNumber==-1) {
			backColor = Color.LIGHT_GRAY;
		}
		if(randomNumber == 1) {
			backColor = Color.blue;
		}
		if(randomNumber == 2) {
			backColor = Color.orange;
		}
		if(randomNumber == 3) {
			backColor = Color.red;
		}
		if(randomNumber == 4) {
			backColor = Color.green;
		}
		cardView.setBackground(backColor);
		assertEquals("Wurde auf die passende HintergrundFarbe gesetzt?",cardView.getBackground(),Color.blue);
	}
	
	@Test
	public void testGetJRadioButton() {
		
		if(rdbtnCreated = true){
			rdbtnReferenz = true;
		}
		else if(rdbtnStarted = true){
			rdbtnReferenz = false;
		}
		else if(rdbtnDone = true) {
			rdbtnReferenz = false;	
		}
		assertEquals("Passt der gesetzte Buttonzustand zum Ausgangszustand",rdbtnCreated,rdbtnReferenz);
	}
	
	@Test
	public void testSetIdTextField() {
		cardView.setIdTextField(cardId.toString());
		assertEquals("Passt der Text im idTextField mit der Beschreibung überein.", cardView.getIdTextField(), cardId.toString());
	}
	
	@Test
	public void testSetSizeTextField() {
		cardView.setSizeTextField(size.toString());
		assertEquals("Passt der Text im idTextField mit der Beschreibung überein.", cardView.getSizeTextFieldValue(), size.toString());
	}
	
}
