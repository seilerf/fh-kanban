package edu.fh.kanban.ui;

import static org.junit.Assert.*;

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
	private static CardController cardController;
	private static PreferencesView prefView;
	
	
	@BeforeClass
	public static void setBeforeTesting() {
		cardView = new CardView(cardController,prefView);
	}
	
	@Test
	public void testSetCardTitel() {
		cardView.setCardTitel("");
		assertEquals("Hat sich der Titel der Kartenansicht verändert?","", cardView.getCardTitel());
	}
	
}
