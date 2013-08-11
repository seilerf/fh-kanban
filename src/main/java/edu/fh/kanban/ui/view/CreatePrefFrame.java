package edu.fh.kanban.ui.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.controller.CardController;

public class CreatePrefFrame extends JFrame {
	public CreatePrefFrame(Board board,BoardController boardController){
		Preference pref = boardController.getColumnControllerList().get(0).getPreference();
		//Card card = new Card(0,0,null,false,0,null,null,null,null);
		PreferencesView pv = new PreferencesView(board,boardController);
		
		//cardView.setPreference(pref);
		//cardController.setParentColumnControllers(boardController.getColumnControllerList());
		//card.addPropertyChangeListener(boardController);
		//this.add(pv);
		
	}
}
