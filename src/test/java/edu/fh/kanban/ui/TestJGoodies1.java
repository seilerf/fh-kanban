/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fh.kanban.ui;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vs
 */
public class TestJGoodies1 {

    public static void main(String[] args) {
        FormLayout formLayout = new FormLayout("p,2dlu,p:grow");
        CellConstraints cc = new CellConstraints();
        
        DefaultFormBuilder builder = new DefaultFormBuilder(formLayout); 
        builder.setDefaultDialogBorder();
        ButtonBarBuilder2 b = ButtonBarBuilder2.createLeftToRightBuilder();
        b.addButton(new JButton("Speichern"));
        b.addButton(new JButton("Abbrehen"));
        b.addButton(new JButton("Hilfe"));
                
        builder.append("Name:");
        builder.append(new JTextField("Name:"));  
        builder.appendSeparator("Colors");
        
                
        
        System.out.println("Adding panel cc");
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(builder.getPanel(), BorderLayout.CENTER);
        frame.add(b.getPanel(), BorderLayout.SOUTH);
        frame.setTitle("Teamproject 2013 - Kanban");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
