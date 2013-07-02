/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fh.kanban.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author vs
 */
public class TestJGoodies2 {

    public static void main(String[] args) {
        FormLayout formLayout = new FormLayout("p,2dlu,p:grow","p");
        CellConstraints cc = new CellConstraints();
        
        JPanel panel = new JPanel(formLayout);
        panel.add(new JLabel("Name:"), cc.xy(1,1));
        panel.add(new JTextField(), cc.xy(3,1)); 
        
        
        
        System.out.println("Adding panel cc");
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.setTitle("Teamproject 2013 - Kanban");
        //frame.add(new JComboBox());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
