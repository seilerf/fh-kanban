package edu.fh.kanban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import edu.fh.kanban.dao.DataManager;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.controller.CardController;
import edu.fh.kanban.ui.dialog.OpenFileDialog;
import edu.fh.kanban.ui.dialog.SaveFileDialog;
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
		
		
		
		
		final DataManager dm = new DataManager();
		
		dm.openFile(new File("board.xml"));
		
		Board board = dm.getBoard();
		//Views erstellen
		

		Card emptycard = new Card(0, 0, 0, null, false, 0, null, null);
		CardController cardcontroller = new CardController();
		cardcontroller.addModel(emptycard);
		final CardView cardView = new CardView(cardcontroller);
		cardcontroller.addView(cardView);
		
		
		 final BoardView boardView = new BoardView(board,cardcontroller); //view boardView
		View backlogView = new BacklogView();
		
		
		
		JMenuBar menubar = new JMenuBar();
		JMenu filemenu = new JMenu("Datei");
		menubar.add(filemenu);
		
		/**
		 * Zu der Menüoption -> File wurde eine Menü-Optionen hinzugefügt:
		 * 1: Karte erstellen (==> ermöglicht das Anlegen einer neuen Karte)
		 */
		
		JMenuItem openMenu = new JMenuItem("Board öffnen...");
		filemenu.add(openMenu);
		openMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				dm.openFile(new OpenFileDialog().openFile());
				//Update BoardView
			}
		});
		
		JMenuItem saveMenu = new JMenuItem("Board speichern...");
		filemenu.add(saveMenu);
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm.saveFile(new SaveFileDialog().getFile());
			}
		});
		
		JMenuItem cardMenu = new JMenuItem("Karte erstellen");
		filemenu.add(cardMenu);
		cardMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFrame frame2 = new JFrame();
				frame2.add(cardView);
				frame2.setSize(410,240);
				frame2.setVisible(true);
			}
		}); 
		
		
		JMenu prefmenu= new JMenu("Einstellungen");
		//prefmenu.add(menuItem)
		menubar.add(prefmenu);
		
		/**
		 * Zu der Menüoption -> Einstellugen wurden 2 Menü-Optionen hizugefügt:
		 * 1: Boardkarten bearbeiten (=> ermöglicht das Bearbeiten der Karten auf dem Board.
		 * 2: Boardkarten bearbeiten beenden (=> setzt nach Beendigung der Bearbeitung alle Inhalte wieder auf Disabled)
		 */
		JMenuItem cardViewBoardWorkModusYes = new JMenuItem("Boardkarten bearbeiten");
		prefmenu.add(cardViewBoardWorkModusYes);
		cardViewBoardWorkModusYes.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boardView.setAllCardViewsEnabled();	
			}
		});
		
		JMenuItem cardViewBoardWorkModusNo = new JMenuItem("BordKarten bearbeiten beenden");
		prefmenu.add(cardViewBoardWorkModusNo);
		cardViewBoardWorkModusNo.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boardView.setAllCardViewsDisabled();
			}
		});
	
		
		JMenuItem item = new JMenuItem("Einstellungen des Boards");
        prefmenu.add(item);		
        item.addActionListener(new ActionListener() {        	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                PreferencesView pv= new PreferencesView();
            }
        });

		//Controller erstellen
		
		
	
		JTabbedPane pane = new JTabbedPane();
		pane.addTab("Board", boardView.getComponent());
		pane.addTab("Backlog", backlogView.getComponent());
	
		
		
		JFrame frame = new JFrame();
		frame.setJMenuBar(menubar);
		frame.setLayout(new BorderLayout());
		frame.setTitle("Teamproject 2013 - Kanban");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		//frame.setSize(700, 500);
		frame.setLocationByPlatform(true);
		frame.add(pane);
		//Toolkit.getDefaultToolkit().setDynamicLayout(true);
		frame.setVisible(true);
		
		
		
	}
	

}

