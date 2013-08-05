package edu.fh.kanban;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.domain.Backlog;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.ui.controller.BacklogController;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.view.BacklogView;
import edu.fh.kanban.ui.view.BoardView;


public class Kanban {

	static Logger LOGGER = Logger.getLogger(Kanban.class.getName());
	
	
	public static void main(String[] args) throws ParseException, InterruptedException {
		final DAOFactory xmlfactory = DAOFactory.getDAOFactory(DAOFactory.XML);
		Board board = null;
		Backlog backlog = null;
		LOGGER.info("Starting kanban app.");
		
		LOGGER.info("Setting look and feel.");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		LOGGER.info("Creating UI components.");

		//Views erstellen

		//View backlogView = new BacklogView(dm,board);
		//Inna: Bitte den Konstruktor anpassen auf:
		//BacklogView(BacklogController) (siehe Links..)
		/** Beispiel DAOs: Objekt in Ressource suchen:
		 * 1. Erzeugung einer speziellen DAOFactory (z.B. xmlFactory), die Board-, Card- und ColumnDAOs erzeugen kann
		 * 2. Erzeugung eines Board-/Card- oder Column DAOs aus der speziellen DAOFactory
		 * 3. Suchedes Objektes (hier Board, Card oder Column) durch DAO
		 */
		
		BoardDAO boardDAO = xmlfactory.getBoardDAO();
		try{
			board = boardDAO.findBoard(new File("Board.xml"));
		}
		catch(NullPointerException e){
			System.out.println("Es lag ein Fehler vor");
		}
		System.out.println("Karten lesen beendet");
		
		
		
		 
		BoardController boardController = new BoardController();
		boardController.addModel(board);
		final BoardView boardView = new BoardView(boardController); 
		boardController.addView(boardView);
	
		boardController.createColumnViews();
		
		BacklogController backlogController = new BacklogController();
		backlog = new Backlog(board.getCards());
		backlogController.addModel(backlog);
		final BacklogView backlogView = new BacklogView(backlogController);
		backlogController.addView(backlogView);
		backlogView.showBacklog();
		
	
		
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
				
				
				
			}
		});
		
		JMenuItem saveMenu = new JMenuItem("Board speichern...");
		filemenu.add(saveMenu);
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dm.saveFile(new SaveFileDialog().getFile());
			}
		});
		
		JMenuItem cardMenu = new JMenuItem("Karte erstellen");
		filemenu.add(cardMenu);
		cardMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				JFrame frame2 = new JFrame();
				//frame2.add(cardView);
				frame2.setSize(420,270);
				frame2.setVisible(true);
			}
		}); 
		
		JMenuItem refreshBoard = new JMenuItem("Board aktualisieren");
		filemenu.add(refreshBoard);
		refreshBoard.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				//boardView.setGUI();
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
                
         
                
            }
        });

		//Controller erstellen
		
		
	
		JTabbedPane pane = new JTabbedPane();
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(boardView.getComponent());
		pane.addTab("Board", jsp);
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

