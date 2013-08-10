package edu.fh.kanban.ui.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.controller.BacklogController;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.controller.CardController;

public class CreateCardFrame extends JFrame {
	public CreateCardFrame(BoardController boardController, BacklogController backlogController){
		Preference pref = boardController.getColumnControllerList().get(0).getPreference();
		Card card = new Card(0,0,null,false,0,null,null,null,null);
		CardController cardController = new CardController();
		CardView cardView = new CardView(cardController);
		cardView.setNewCard(card);
		cardView.setPreference(pref);
		cardController.setColumnControllers(boardController.getColumnControllerList());
		card.addPropertyChangeListener(boardController);
		this.add(cardView);
		backlogController.addCard(card);
	}
}
