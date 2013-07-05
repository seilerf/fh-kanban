package edu.fh.kanban;

import java.awt.BorderLayout;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.ui.view.BacklogView;
import edu.fh.kanban.ui.view.BoardView;
import edu.fh.kanban.ui.view.View;

public class Kanban {

	static Logger LOGGER = Logger.getLogger(Kanban.class.getName());
	
	/**
	 * @param args
	 */
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
		
		View backlogView = new BacklogView();
		
		DataManager dm = new DataManager();
		dm.readXML(null);
		
		Board board = new Board();
		View boardView = new BoardView(dm);
	
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Backlog", backlogView.getComponent());
		pane.addTab("Board", boardView.getComponent());
		
		
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
