package edu.fh.kanban.ui.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.controller.CardController;

public class CreateCardFrame extends JFrame {
	public CreateCardFrame(Preference pref){
		
		CardController cardController = new CardController();
		
		CardView cardView = new CardView(cardController);
		cardController.addView(cardView);
		cardView.setPreference(pref);
		this.add(cardView);
		
	}
}
