package edu.fh.kanban.ui.view;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author vs
 */
public class PreferencesView {

	
	
    public static void main(String[] args) {
    	final JFrame frame = new JFrame();
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
       
        
        
        
        
        
     /*   
      //  b.addButton(new JButton("Hilfe"));
        
        ButtonBarBuilder2 b2 = ButtonBarBuilder2.createLeftToRightBuilder();
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
       // b2.addButton(new ColorCombo());
       // b2.addButton(new ColorCombo());
            
        */
        
        
       
      
        
        
       // JComboBox comboBox2 = new JComboBox();
        //b2.addButton( new ColorBox());

        
        JLabel label  = new JLabel ("Einstellungen");
        //label.setName("Einstellungen");
        label.setForeground( Color.BLUE );
       // frame.add(label,BorderLayout.NORTH);
       // builder.append(label);
        builder.append("Name:");
        
       // builder2.append("Anzahl der Tickets pro Spalte");
        
        /**
         * Standard farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder3.append("Standard");
        
         final ColorBox cb1= new ColorBox();
        builder3.append(cb1);
        
        cb1.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                //colorBoxItemStateChanged(e);
                int selectedIndex = cb1.getSelectedIndex();
                System.out.println(cb1.LABELS[selectedIndex]/*+" "+cb1.COLORS[selectedIndex]*/);
            }
       
        });
        
        
        /**
         * Expedite farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder4.append("Expedite");
        
        final ColorBox cb2= new ColorBox();
        builder4.append(cb2);
        
        cb2.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                //colorBoxItemStateChanged(e);
                int selectedIndex = cb2.getSelectedIndex();
                System.out.println(cb2.LABELS[selectedIndex]/*+" "+cb1.COLORS[selectedIndex]*/);
            }
       
        });
        
        
        /**
         * Fixed Date farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder5.append("Fixed Date");
        final ColorBox cb3= new ColorBox();
        builder5.append(cb3);
        
        cb3.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                //colorBoxItemStateChanged(e);
                int selectedIndex = cb3.getSelectedIndex();
                System.out.println(cb3.LABELS[selectedIndex]/*+" "+cb1.COLORS[selectedIndex]*/);
            }
       
        });
        
        
        
        /**
         * Intangible farbauswahl mit Listener der dann dem Board die Farbe übergibt
         */
        
        builder6.append("Intangible");
        final ColorBox cb4= new ColorBox();
        builder6.append(cb4);
        
        cb4.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                //colorBoxItemStateChanged(e);
                int selectedIndex = cb4.getSelectedIndex();
                System.out.println(cb4.LABELS[selectedIndex]/*+" "+cb1.COLORS[selectedIndex]*/);
            }
       
        });
        
        
        
        
        
        
        
        final JTextField name = new JTextField();
        
       // JTextField spaltenzahl= new JTextField();
      
        
        builder.append(name); 
      //  builder2.append(spaltenzahl);
        
        /**
         * Schließen Button mit Listener
         */
        
        JButton beenden= new JButton("Beenden");
        b.addButton(beenden);
        beenden.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            //Execute when button is pressed
            System.out.println("Einstellungen wird geschlossen");
            frame.setVisible(false);

        }
    }); 
        
        /**
         * Speichern Button mit Listener und ändert den Titel
         */
        
        JButton speichern= new JButton("Namen Speichern");
        b.addButton(speichern);
        speichern.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You saved the Name");
                name.getText();
                frame.setTitle(name.getText());

            }
        }); 
        
        /**
         * Schließen Button mit Listener
         */
        
        String[] lang = {
  		      "Next", "Dev", "Test", "Done"
  		    };
        
        final JComboBox combo1 = new JComboBox();
		   for ( String s : lang )
		    
		      combo1.addItem( s );
		    
		   // setModel(new DefaultComboBoxModel(lang));
		   
		    combo1.addItemListener( new ItemListener() {
		        public void itemStateChanged( ItemEvent e ) {
		          JComboBox selectedChoice = (JComboBox)e.getSource();
		         int selectedIndex = combo1.getSelectedIndex();
		         // System.out.println(e.toString());
		          System.out.println(selectedChoice.getSelectedItem());
		          
		       
		        }
		      } );
                
		    ButtonBarBuilder2 b3 = ButtonBarBuilder2.createLeftToRightBuilder();
	        //b3.addButton(combo1);
	        
	        builder2.append(combo1);
	       // b3.addButton(spaltenzahl);
        System.out.println("Adding panel cc");
        
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
  		    
  		   // setModel(new DefaultComboBoxModel(lang));
  		   
  		    combo2.addItemListener( new ItemListener() {
  		        public void itemStateChanged( ItemEvent e ) {
  		          JComboBox selectedChoice = (JComboBox)e.getSource();
  		         int selectedIndex = combo2.getSelectedIndex();
  		         // System.out.println(e.toString());
  		          System.out.println(selectedChoice.getSelectedItem());
  		          
  		       
  		        }
  		      } );
                 // b3.appendRow("Ticket");
  		  //b3.addButton(combo2);
  		builder2.append(combo2);
        //frame.add(builder.getPanel(),BorderLayout.NORTH);
      //  frame.getContentPane().add(b2.getPanel(), BorderLayout);
       // frame.getContentPane().add(builder.getPanel(), BorderLayout.CENTER);
        //frame.add(b2.getPanel(), BorderLayout.CENTER);
        //frame.getContentPane().add(b.getPanel(), BorderLayout.SOUTH);
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
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder.appendSeparator("Ticketfarbe"), gbc_seperator1);
        
        
        GridBagConstraints gbc_builder3 = new GridBagConstraints();
        gbc_builder3.fill = GridBagConstraints.HORIZONTAL;
        gbc_builder3.gridx = 0;
        gbc_builder3.gridy = 4;
        frame.getContentPane().add(builder3.getPanel(),gbc_builder3);
        
        
        //JComboBox comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 5;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder4.getPanel(), gbc_comboBox);
        
        
        GridBagConstraints gbc_5 = new GridBagConstraints();
        gbc_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_5.gridx = 0;
        gbc_5.gridy = 6;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder5.getPanel(), gbc_5);
        
        GridBagConstraints gbc_6 = new GridBagConstraints();
        gbc_6.fill = GridBagConstraints.HORIZONTAL;
        gbc_6.gridx = 0;
        gbc_6.gridy = 7;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
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
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder.appendSeparator("Anzahl der Tickets pro Spalte"), gbc_seperator2);
        
        
        GridBagConstraints spalten_ComboBox = new GridBagConstraints();
        spalten_ComboBox.fill = GridBagConstraints.HORIZONTAL;
        spalten_ComboBox.gridx = 0;
        spalten_ComboBox.gridy = 10;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        //frame.getContentPane().add(b3.getPanel(), spalten_ComboBox);
        
        
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
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
        
        
        
        
       
        
        
    }

	


 
   
 

	
		
	}

