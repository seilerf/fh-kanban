package edu.fh.kanban.ui.view;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 *
 * @author vs
 */
public class PreferencesModel {

    public static void main(String[] args) {
        FormLayout formLayout = new FormLayout("p,2dlu,p:grow");
        CellConstraints cc = new CellConstraints();
        
        DefaultFormBuilder builder = new DefaultFormBuilder(formLayout); 
        builder.setDefaultDialogBorder();
        ButtonBarBuilder2 b = ButtonBarBuilder2.createLeftToRightBuilder();
        b.addButton(new JButton("Speichern"));
        b.addButton(new JButton("Abbrechen"));
        b.addButton(new JButton("Hilfe"));
        
        ButtonBarBuilder2 b2 = ButtonBarBuilder2.createLeftToRightBuilder();
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
        b2.addButton(new ColorBox());
        
        
       // JComboBox comboBox2 = new JComboBox();
        //b2.addButton( new ColorBox());

        
        JLabel label  = new JLabel ("Einstellungen");
        //label.setName("Einstellungen");
        label.setForeground( Color.BLUE );
       // frame.add(label,BorderLayout.NORTH);
        //builder.append(label);
        builder.append("Name:");
        builder.append(new JTextField());  
       // builder.appendSeparator("Colors");
       // builder.appendSeparator("Columns");
        
                
        
        System.out.println("Adding panel cc");
        JFrame frame = new JFrame();
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
        gbc_seperator1.gridy = 5;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder.appendSeparator("Colors"), gbc_seperator1);
        
        
        //JComboBox comboBox = new JComboBox();
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 0;
        gbc_comboBox.gridy = 6;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(b2.getPanel(), gbc_comboBox);
        
        GridBagConstraints gbc_builder = new GridBagConstraints();
        gbc_builder.fill = GridBagConstraints.HORIZONTAL;
        gbc_builder.gridx = 0;
        gbc_builder.gridy = 4;
        frame.getContentPane().add(builder.getPanel(),gbc_builder);
        
        
        GridBagConstraints gbc_seperator2 = new GridBagConstraints();
        gbc_seperator2.fill = GridBagConstraints.HORIZONTAL;
        gbc_seperator2.gridx = 0;
        gbc_seperator2.gridy = 7;
        //frame.getContentPane().add(comboBox, gbc_comboBox);
        frame.getContentPane().add(builder.appendSeparator("Columns"), gbc_seperator2);
        
        
        GridBagConstraints gbc_b = new GridBagConstraints();
        gbc_b.fill = GridBagConstraints.HORIZONTAL;
        gbc_b.gridx = 0;
        gbc_b.gridy = 9;
        frame.getContentPane().add(b.getPanel(),gbc_b);
        
        frame.setTitle("BOARD - Einstellungen");
       
        //frame.add(new JComboBox());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
