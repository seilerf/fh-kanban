package edu.fh.kanban;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.controller.CardController;
import edu.fh.kanban.ui.view.BacklogView;
import edu.fh.kanban.ui.view.BoardView;
import edu.fh.kanban.ui.view.CardView;
import edu.fh.kanban.ui.view.PreferencesView;
import edu.fh.kanban.ui.view.View;

public class Kanban {

	static Logger LOGGER = Logger.getLogger(Kanban.class.getName());
	
	
	public static void main(String[] args) {
		
		LOGGER.info("Starting kanban app.");
		
		LOGGER.info("Setting look and feel.");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		LOGGER.info("Creating UI components.");
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(new JMenu("File"));
		
		DataManager dm = new DataManager();
		
		dm.readXML(null);
		//Views erstellen

		Card emptycard = new Card(0, 0, 0, null, false, 0, null, null);
		CardController cardcontroller = new CardController();
		cardcontroller.addModel(emptycard);
		CardView cardView = new CardView(cardcontroller);
		
		cardcontroller.addView(cardView);
		
		
		
		View boardView = new BoardView(dm);
		View backlogView = new BacklogView();
		
		PreferencesView pv = new PreferencesView();
		
		
		JFrame frame2 = new JFrame();
		frame2.add(cardView);
		frame2.setSize(800,400);
		frame2.setVisible(true);
		
		
		JMenu prefmenu= new JMenu("Einstellungen");
		//prefmenu.add(menuItem)
		menubar.add(prefmenu);
		
		
	
		
		Board board = new Board();
		
		
		
		
		//Controller erstellen
		
		
	
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Board", boardView.getComponent());
		pane.addTab("Backlog", backlogView.getComponent());
	
		
		
		JFrame frame = new JFrame();
		frame.setJMenuBar(menubar);
		frame.setLayout(new BorderLayout());
		frame.setTitle("Teamproject 2013 - Kanban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setLocationByPlatform(true);
		frame.add(pane);
		frame.setVisible(true);
	}

}
