package edu.fh.kanban.ui.view;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.Kanban;
import edu.fh.kanban.domain.Board;
import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.ColorBox;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.controller.AbstractController;
import edu.fh.kanban.ui.controller.BoardController;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * PreferencesView stellt ein Einstellungsfenster zur Verfügung
 * Es ist möglich den Namen des Boards zu ändern, die Kartenfarben bei 4 verschiedenen Zeitbeschränkungen
 * und die Anzahl der Tickets pro Spalte zu limitieren.
 * @author Anton
 *
 */
public class PreferencesView extends AbstractController implements View {
	LinkedList<Card> cardList;
	LinkedList<Column> columnList;
	private int limits;
	private String spalte;

	private final ColorBox cb1;
	private final ColorBox cb2;
	private final ColorBox cb3;
	private final ColorBox cb4;
	private final JFrame frame;
	private BoardController boardController;
	private Board board;
	
    public PreferencesView(final Board board, final BoardController boardController) {
    	
    	this.board= board;
    	this.boardController = boardController;
		frame = new JFrame();
        FormLayout formLayout = new FormLayout("p,2dlu,p:grow");
        CellConstraints cc = new CellConstraints();
        
        DefaultFormBuilder builder = new DefaultFormBuilder(formLayout); 
        builder.setDefaultDialogBorder();
        
        DefaultFormBuilder builder2 = new DefaultFormBuilder(formLayout); 
        builder2.setDefaultDialogBorder();
        
        DefaultFormBuilder builder3 = new DefaultFormBuilder(formLayout); 
        builder3.setDefaultDialogBorder();
        
        DefaultFormBuilder builder4 = new DefaultFormBuilder(formLayout); 
        builder4.setDefaultDialogBorder();
        
        DefaultFormBuilder builder5 = new DefaultFormBuilder(formLayout); 
        builder5.setDefaultDialogBorder();
        
        DefaultFormBuilder builder6 = new DefaultFormBuilder(formLayout); 
        builder6.setDefaultDialogBorder();
           
        ButtonBarBuilder2 b = ButtonBarBuilder2.createLeftToRightBuilder();
        
        JLabel label  = new JLabel ("Einstellungen");
        label.setForeground( Color.BLUE );
      
        builder.append("Name:");
        
        
        /**
         * Standard farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder3.append("Standard");
        
         cb1= new ColorBox();
         cb1.setSelectedIndex(8);
         builder3.append(cb1);
        
        cb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int selectedIndex = cb1.getSelectedIndex();
                Color cc= cb1.COLORS[selectedIndex];
                cb1.setBackground(cc);               
                
                String farbe = cb1.LABELS[selectedIndex];

                LinkedList<Card>cardLists = board.getCards();
				Iterator<Card> icard = cardLists.iterator();
		
                while (icard.hasNext()) {
                	Card card = (Card) icard.next();
                	System.out.println(card.getBackGround());
                	if(card.getValue()==1 ) {	
                	
                		Color color = cb1.COLORS[selectedIndex];
                		int[] colorArray =  new int[3];
                		colorArray[0] = color.getRed();
                		colorArray[1] = color.getGreen();
             
                		colorArray[2] = color.getBlue();
                		card.addPropertyChangeListener(boardController);
                		Preference.setColorStandard(colorArray);
                		card.setChanged();
                	}
                }
            }
        });      
        
        /**
         * Expedite farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder4.append("Expedite");
        cb2= new ColorBox();
        cb2.setSelectedIndex(10);
        builder4.append(cb2);
        
        cb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int selectedIndex = cb2.getSelectedIndex();
                Color cc= cb2.COLORS[selectedIndex];
                cb2.setBackground(cc);               
            
                String farbe2 = cb2.LABELS[selectedIndex];
                LinkedList<Card>cardLists = board.getCards();
				Iterator<Card> icard = cardLists.iterator();
				
                while (icard.hasNext()) {
                	Card card = (Card) icard.next();
                	if(card.getValue()==2 ) {
                		Color color = cb2.COLORS[selectedIndex];
                		int[] colorArray =  new int[3];
                		colorArray[0] = color.getRed();
                		colorArray[1] = color.getGreen();
             
                		colorArray[2] = color.getBlue();
                		card.addPropertyChangeListener(boardController);
                		Preference.setColorExpedite(colorArray);
                		card.setChanged();
                	}
                }
            }
        }); 
        
        /**
         * Fixed Date farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder5.append("Fixed Date");
         cb3= new ColorBox();
        cb3.setSelectedIndex(5);
        builder5.append(cb3);
        
        cb3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	
            	int selectedIndex = cb3.getSelectedIndex();
                Color cc= cb3.COLORS[selectedIndex];
                cb3.setBackground(cc);               
                
                String farbe3 = cb3.LABELS[selectedIndex];
                
                LinkedList<Card>cardLists = board.getCards();
				Iterator<Card> icard = cardLists.iterator();
                while (icard.hasNext()) {
                	Card card = (Card) icard.next();
                	System.out.println(card.getBackGround());
                	if(card.getValue()==3 ) {
                		Color color = cb3.COLORS[selectedIndex];
                		int[] colorArray =  new int[3];
                		colorArray[0] = color.getRed();
                		colorArray[1] = color.getGreen();
             
                		colorArray[2] = color.getBlue();
                		card.addPropertyChangeListener(boardController);
                		Preference.setColorFixed(colorArray);
                		card.setChanged();
                	}
                }
            }
        }); 
    
        /**
         * Intangible farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder6.append("Intangible");
        cb4= new ColorBox();
        cb4.setSelectedIndex(1);
        builder6.append(cb4);
        
        cb4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {       	
            	int selectedIndex = cb4.getSelectedIndex();

                Color cc= cb4.COLORS[selectedIndex];
                cb4.setBackground(cc);               

                String farbe4 = cb4.LABELS[selectedIndex];
              
                LinkedList<Card>cardLists = board.getCards();
				Iterator<Card> icard = cardLists.iterator();
	
                while (icard.hasNext()) {
                	Card card = (Card) icard.next();
                	System.out.println(card.getBackGround());
                	if(card.getValue()==4 ) {
                		Color color = cb4.COLORS[selectedIndex];
                		int[] colorArray =  new int[3];
                		colorArray[0] = color.getRed();
                		colorArray[1] = color.getGreen();
             
                		colorArray[2] = color.getBlue();
                		card.addPropertyChangeListener(boardController);
                		Preference.setColorIntagible(colorArray);
                		card.setChanged();
                	}
                }
            }
        }); 
        
        final JTextField name = new JTextField();
        builder.append(name); 
        
        /**
         * Schließen Button mit Listener
         */
        
        JButton beenden= new JButton("Beenden");
        b.addButton(beenden);
        beenden.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
        }
    }); 
        
        /**
         * Speichern Button mit Listener und ändert den Titel
         * aber änderung wird erst beim speichern und neuladen des Boards 
         * wirksam
         */
        
        JButton speichern= new JButton("Namen Speichern");
        b.addButton(speichern);
        speichern.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                System.out.println("You saved the Name");
                String boardname = name.getText();
                Kanban.frame.setTitle(boardname);
            
            }     
        }); 
        
        /**
         * Durch klicken des Limit Speichern Buttons wird das Limit 
         * in der jeweiligen Spale eingestellt
         */
        
        JButton spaltspeichern = new JButton("Limit Speichern");
        b.addButton(spaltspeichern);
        spaltspeichern.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	limitchanger(e);
            }
        }); 
        
       /**
        * Auswahl der Spalte mit Listener gibt die auswahl an setter Methode weiter 
        */
        
        //Die BoardSpalten aus dem Board auslesen
        LinkedList <Column> columnl= boardController.getColumnList();
         Iterator<Column> icolumn = columnl.iterator();
        String[] lang ;
        lang = new String[4];
         int i=0;
         while (icolumn.hasNext())  {
    	   Column column = (Column) icolumn.next();
			String spaltname = column.getName();
			
         lang[i] = spaltname;
        i++;
  		    
         }
        
        final JComboBox combo1 = new JComboBox();
		   for ( String s : lang )
		      combo1.addItem( s );
		    	combo1.addItemListener( new ItemListener() {
		        public void itemStateChanged( ItemEvent e ) {
		        	JComboBox selectedChoice = (JComboBox)e.getSource();
  		        	String spalte = selectedChoice.getSelectedItem().toString();
  		        	setSelectedColumn(spalte);
		        }
		      } );
                
		    ButtonBarBuilder2 b3 = ButtonBarBuilder2.createLeftToRightBuilder();
        
        /**
         * Combobox Anzahl der Karten in der jeweiligen Spalte
         * 
         */
        String[] anzahl = {
    		      "1", "2", "3", "4","5","6","7","8","9","10","11"
    		    };
          
          final JComboBox combo2 = new JComboBox();
  		   for ( String s : anzahl )
  		    
  		      combo2.addItem( s );
  		    
  		    combo2.addItemListener( new ItemListener() {
  		        public void itemStateChanged( ItemEvent e ) {
  		        	JComboBox selectedChoice = (JComboBox)e.getSource();
  		        	int ii = selectedChoice.getSelectedIndex();
  		        	setselectedLimit(ii);

  		        }
  		      } );
          
  		builder2.append(combo2);
  		builder2.append(combo1);
       
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);
        
        GridBagConstraints gbc_seperator1 = new GridBagConstraints();
        gbc_seperator1.fill = GridBagConstraints.HORIZONTAL;
        gbc_seperator1.gridx = 0;
        gbc_seperator1.gridy = 3;
        frame.getContentPane().add(builder.appendSeparator("Ticketfarbe"), gbc_seperator1);
        
        GridBagConstraints gbc_builder3 = new GridBagConstraints();
        gbc_builder3.fill = GridBagConstraints.HORIZONTAL;
        gbc_builder3.gridx = 0;
        gbc_builder3.gridy = 4;
        frame.getContentPane().add(builder3.getPanel(),gbc_builder3);
        
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 5;
        frame.getContentPane().add(builder4.getPanel(), gbc_comboBox);
        
        GridBagConstraints gbc_5 = new GridBagConstraints();
        gbc_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_5.gridx = 0;
        gbc_5.gridy = 6;
        frame.getContentPane().add(builder5.getPanel(), gbc_5);
        
        GridBagConstraints gbc_6 = new GridBagConstraints();
        gbc_6.fill = GridBagConstraints.HORIZONTAL;
        gbc_6.gridx = 0;
        gbc_6.gridy = 7;
        frame.getContentPane().add(builder6.getPanel(), gbc_6);
        
        GridBagConstraints gbc_builder = new GridBagConstraints();
        gbc_builder.fill = GridBagConstraints.HORIZONTAL;
        gbc_builder.gridx = 0;
        gbc_builder.gridy = 2;
        frame.getContentPane().add(builder.getPanel(),gbc_builder);
        
        GridBagConstraints gbc_seperator2 = new GridBagConstraints();
        gbc_seperator2.fill = GridBagConstraints.HORIZONTAL;
        gbc_seperator2.gridx = 0;
        gbc_seperator2.gridy = 8;
        frame.getContentPane().add(builder.appendSeparator("Anzahl der Tickets pro Spalte"), gbc_seperator2);
        
        GridBagConstraints spalten_ComboBox = new GridBagConstraints();
        spalten_ComboBox.fill = GridBagConstraints.HORIZONTAL;
        spalten_ComboBox.gridx = 0;
        spalten_ComboBox.gridy = 10;
       
        GridBagConstraints gbc_b = new GridBagConstraints();
        gbc_b.fill = GridBagConstraints.HORIZONTAL;
        gbc_b.gridx = 0;
        gbc_b.gridy = 11;
        frame.getContentPane().add(b.getPanel(),gbc_b);
        
        frame.setTitle("BOARD - Einstellungen");
       
        GridBagConstraints gbc_d = new GridBagConstraints();
        gbc_d.fill = GridBagConstraints.HORIZONTAL;
        gbc_d.gridx = 0;
        gbc_d.gridy = 9;
        frame.getContentPane().add(builder2.getPanel(),gbc_d);
        
        frame.setSize(330, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
    }
    
    public JFrame getJComponent() {
    	return this.frame;
    }


    protected void setSelectedColumn(String spalt) {
    	this.spalte= spalt ;
    	
    	System.out.println(spalte);

	}

    public String getselectedColumn() {
		return spalte;	
	}
    
    public Color getSelectedColorFromBox1() {
    	Color cBox1 = this.cb1.COLORS[cb1.getSelectedIndex()];
    	return cBox1;
    }
    public Color getSelectedColorFromBox2() {
    	Color cBox2 = this.cb2.COLORS[cb2.getSelectedIndex()];
    	return cBox2;
    }
    public Color getSelectedColorFromBox3() {
    	Color cBox1 = this.cb3.COLORS[cb3.getSelectedIndex()];
    	return cBox1;
    }
    public Color getSelectedColorFromBox4() {
    	Color cBox1 = this.cb4.COLORS[cb4.getSelectedIndex()];
    	return cBox1;
    }

    /**
     * Geht die Spaltenliste durch und sucht die ausgewählte spalte um dann das Limit zu setzen
     * @param e
     */

	protected void limitchanger(ActionEvent e) {
    	
		 LinkedList <Column> columnl= boardController.getColumnList();
         Iterator<Column> icolumn = columnl.iterator();
		
	      while (icolumn.hasNext())    {	
	       	 Column column = (Column) icolumn.next(); 
			String name = column.getName();
			String ausgewähltespalte = getselectedColumn() ;

			if (ausgewähltespalte.equals(name)) {		
				
				int ii = getselectedLimit();
				ii+=1;
				column.setLimit(ii);
			}
        }
	}

	public void setselectedLimit(int ii) {
		limits = ii;	
	}

	public int getselectedLimit() {
		return limits;
	}

	@Override
	public JComponent getComponent() {
		// TODO Auto-generated method stub
		return null;
	}


}