package edu.fh.kanban.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.dao.BoardDAO;
import edu.fh.kanban.dao.XML.XMLUtil;
import edu.fh.kanban.domain.Backlog;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.ColorBox;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.ui.controller.BacklogController;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.view.BacklogView;
import edu.fh.kanban.ui.view.BoardView;

public class PreferencesTest {
	
	public static void main(String[] args) throws ParseException, InterruptedException{
		
		
		
		
		//Board board = dm.getBoard();
		
		final JFrame frame = new JFrame();
        FormLayout formLayout = new FormLayout("p,2dlu,p:grow");
     
        
        DefaultFormBuilder builder = new DefaultFormBuilder(formLayout); 
        builder.setDefaultDialogBorder();
        
        DefaultFormBuilder builder2 = new DefaultFormBuilder(formLayout); 
        builder2.setDefaultDialogBorder();
         
        
        ButtonBarBuilder2 b = ButtonBarBuilder2.createLeftToRightBuilder();
         
        builder.append("Name:");  
                   
        final JTextField name = new JTextField();
        
      
        
        builder.append(name); 
        
             
        
        JButton speichern= new JButton("Namen Speichern");
        
        speichern.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                
            	String boardname = name.getText();
            	//board.setName(boardname);
            	try {
					ändereBoardNamen(e,boardname);
				} catch (NullPointerException | ParseException
						| InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }); 
        
        b.addButton(speichern);
      
                    
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);
        
        
        
        
        
        GridBagConstraints gbc_builder = new GridBagConstraints();
        gbc_builder.fill = GridBagConstraints.HORIZONTAL;
        gbc_builder.gridx = 0;
        gbc_builder.gridy = 2;
        frame.getContentPane().add(builder.getPanel(),gbc_builder);
        
        
        
        
       
        
        GridBagConstraints gbc_b = new GridBagConstraints();
        gbc_b.fill = GridBagConstraints.HORIZONTAL;
        gbc_b.gridx = 0;
        gbc_b.gridy = 11;
        frame.getContentPane().add(b.getPanel(),gbc_b);
        
        frame.setTitle("BOARD - Einstellungen");
       
       
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
               
	}
	/**
	 * Test1 zum ändern des Boardnamens
	 * setted den Neuen Boardnamen und gibt vorher den alten und dann den neuen Boardnamen als Beweis zurück
	 * @param e
	 * @param name
	 * @throws InterruptedException 
	 * @throws ParseException 
	 * @throws NullPointerException 
	 */
	
	public static void ändereBoardNamen(ActionEvent e, String name) throws NullPointerException, ParseException, InterruptedException {
		
		BoardDAO boardDAO;
		final Board board;
		BoardView boardView;
		
		boardDAO = XMLUtil.getXmlFactory().getBoardDAO();
		board = boardDAO.findBoard();

		
		BoardController boardController;
		Backlog backlog;
		BacklogView backlogView;
		BacklogController backlogController;
		
		//boardController = new BoardController();
		//boardController.addModel(board);
        System.out.println(board.getName());
        
       board.setName(name);
       System.out.println(board.getName());
	}
}
