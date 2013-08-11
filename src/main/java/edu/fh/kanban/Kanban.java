package edu.fh.kanban;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.CardDAO;
import edu.fh.kanban.dao.DAOFactory;
import edu.fh.kanban.dao.CSV.CSVUtil;
import edu.fh.kanban.dao.PDF.PDFUtil;
import edu.fh.kanban.dao.XML.XMLUtil;
import edu.fh.kanban.domain.Backlog;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.controller.BacklogController;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.dialog.OpenFileDialog;
import edu.fh.kanban.ui.dialog.SaveFileDialog;
import edu.fh.kanban.ui.view.BacklogView;
import edu.fh.kanban.ui.view.BoardView;
import edu.fh.kanban.ui.view.CreateCardFrame;
import edu.fh.kanban.ui.view.CreatePrefFrame;
import edu.fh.kanban.ui.view.PreferencesView;


public class Kanban {

	static Logger LOGGER = Logger.getLogger(Kanban.class.getName());
	public static JFrame frame = new JFrame();


	
	public static void main(String[] args) throws ParseException, InterruptedException {
		

		LOGGER.info("Setting look and feel.");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		LOGGER.info("Starting kanban app.");
		
		LOGGER.info("Loading data.");
		try{
			BoardDAO boardDAO;
			Board board;
			BoardView boardView;
			
			boardDAO = XMLUtil.getXmlFactory().getBoardDAO();
			board = boardDAO.findBoard();

			
			BoardController boardController;
			Backlog backlog;
			BacklogView backlogView;
			BacklogController backlogController;
			
			boardController = new BoardController();
			boardController.addModel(board);
			boardView = new BoardView(boardController); 
			boardController.addView(boardView);
		
			boardController.createColumnViews();

			backlogController = new BacklogController();
			backlog = new Backlog(board.getCards());
			backlogController.addModel(backlog);
			backlogView = new BacklogView(backlogController);
			backlogController.addView(backlogView);
			backlogView.showBacklog();

			initComponents(board,boardView,boardDAO,backlogController,backlogView,boardController);
			
			boardView.createContextMenu();
			
			
		}
		catch(NullPointerException e){
			System.out.println("Fehler beim GUI erstellen");
			e.printStackTrace();
		}
		System.out.println("Karten lesen beendet");

	}

	
	public static void initComponents(final Board board, final BoardView boardView, final BoardDAO boardDAO, final BacklogController backlogController,
						final BacklogView backlogView, final BoardController boardController){

		JMenuBar jMenuBar;
		JMenuItem openMenu;
		JMenuItem saveMenu;
		JMenuItem cardMenu;
		JMenuItem refreshBoard;
		JMenu prefmenu;
		JMenu filemenu;
		final SaveFileDialog saveFileDialog = new SaveFileDialog();
		final OpenFileDialog openFileDialog = new OpenFileDialog();
		
		jMenuBar = new JMenuBar();
		prefmenu = new JMenu("Einstellungen");
		filemenu = new JMenu("Datei");

		/**
		 * Zu der Menüoption -> File wurde eine Menü-Optionen hinzugefügt:
		 * 1: Karte erstellen (==> ermöglicht das Anlegen einer neuen Karte)
		 */
		
		openMenu = new JMenuItem("Board öffnen...");
		filemenu.add(openMenu);
		openMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				openFileDialog.openFileDialog();
			}
		});
		
		saveMenu = new JMenuItem("Board speichern...");
		filemenu.add(saveMenu);
		saveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileDialog.showSaveDialog();
				boardDAO.insertBoard(board);
				
			}
		});
		
		cardMenu = new JMenuItem("Karte erstellen");
		filemenu.add(cardMenu);
		cardMenu.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				CreateCardFrame createCardFrame = new CreateCardFrame(boardController, backlogController);
				
				createCardFrame.setSize(470,300);
				createCardFrame.setVisible(true);
			}
		}); 
		
		refreshBoard = new JMenuItem("Board aktualisieren");
		filemenu.add(refreshBoard);
		refreshBoard.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				//boardView.setGUI();
			}
		});
		

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
            	CreatePrefFrame cpf = new CreatePrefFrame(board,boardController);
            }
        });
        
        JMenu export= new JMenu("Export");
		
		
		JMenuItem pdfExport = new JMenuItem("Export in PDF");
		export.add(pdfExport);
		pdfExport.addActionListener(new ActionListener() {
		
		DAOFactory pdfFactory = DAOFactory.getDAOFactory(DAOFactory.PDF);
		CardDAO pdfCardDAO = pdfFactory.getCardDAO();
		
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					LinkedList<Card> cards = board.getCards();
					Iterator<Card> cIt = cards.iterator();
					PDFUtil.setBoardName(board.getName());
					PDFUtil.document = new Document();
					PDFUtil.document.addTitle(PDFUtil.boardName.concat("_Export"));
					PDFUtil.writer = PdfWriter.getInstance(PDFUtil.document, new FileOutputStream(PDFUtil.boardName.concat(".pdf")));
					PDFUtil.document.open();
				        try {
				        	PDFUtil.bf = BaseFont.createFont();
						} catch (IOException e1) {
						
							e1.printStackTrace();
						}
				        
					PDFUtil.cb = PDFUtil.writer.getDirectContent();
					PDFUtil.cb.beginText();
					PDFUtil.cb.setFontAndSize(PDFUtil.bf, 30);
					PDFUtil.cb.moveText(200, 505);
					PDFUtil.cb.showText("Backlog Export");
					PDFUtil.cb.endText();
					
					while(cIt.hasNext()){
							pdfCardDAO.insertCard(cIt.next());
					}
					
					PDFUtil.document.close();
					
				} catch (FileNotFoundException | DocumentException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("PDFREADER:");
				try {
					Desktop.getDesktop().open( new File("TestBoard.pdf") );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		JMenuItem csvExport = new JMenuItem("Export in CSV");
		export.add(csvExport);
		csvExport.addActionListener(new ActionListener() {
		
			DAOFactory csvFactory = DAOFactory.getDAOFactory(DAOFactory.CSV);
			CardDAO csvCardDAO = csvFactory.getCardDAO();
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				
				try {
					LinkedList<Card> cards = board.getCards();
					Iterator<Card> cIt = cards.iterator();
					System.out.println("Test");
					CSVUtil.setBw(new BufferedWriter(new FileWriter(CSVUtil.boardName.concat(".csv"))));
					
					while(cIt.hasNext()){
						System.out.println("Exportiere nächste Karte:\n");
						csvCardDAO.insertCard(cIt.next());
						
					}
					CSVUtil.bw.close();//BufferWriter schliessen
					
					 
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				System.out.println("PDFREADER:");
				try {
					Desktop.getDesktop().open( new File("TestBoard.csv") );
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}

	});
        
        jMenuBar.add(filemenu);
        jMenuBar.add(export);
		//prefmenu.add(menuItem)
		jMenuBar.add(prefmenu);
		
		JTabbedPane pane = new JTabbedPane();
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(boardView.getComponent());
		pane.addTab("Board", jsp);
		pane.addTab("Backlog", backlogView.getComponent());
		
		
		frame.setJMenuBar(jMenuBar);
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




