package edu.fh.kanban.ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.CardNameComparator;
import edu.fh.kanban.domain.CardValueComparator;
import edu.fh.kanban.domain.SortClass;
import edu.fh.kanban.ui.controller.BacklogController;

public class BacklogView extends JPanel implements View{
	
	private JButton headlineButton;
	private JButton valueButton;
	private JButton sizeButton;
	private JButton createButton;
	private JButton searchButton;
	private JTextField search;
	private DefaultFormBuilder cardbuilder;
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BacklogView(){
		
		showBacklog();	
		//showCardsSortedByCreationTime();
		this.setVisible(true);
	}
	
	public void showBacklog(){
		
		JScrollPane scrollpane = new JScrollPane();
		
		FormLayout cardLayout = new FormLayout("p,10dlu,p,10dlu,p");
		FormLayout searchLayout = new FormLayout("p:grow,10dlu,p");
        
		cardbuilder = new DefaultFormBuilder(cardLayout);
        DefaultFormBuilder searchbuilder = new DefaultFormBuilder(searchLayout); 
        
        searchbuilder.setDefaultDialogBorder();
        cardbuilder.setDefaultDialogBorder();
        
		ButtonBarBuilder2 buttons = ButtonBarBuilder2.createLeftToRightBuilder();
		
		headlineButton = new JButton("Überschrift");
		valueButton = new JButton("Wert");
		sizeButton = new JButton("Größe");
		createButton = new JButton("Erstellung");
		searchButton = new JButton("suche");
		
		headlineButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	showCardsSortedByHeadline(null);
            }
        });
        
        
       
		buttons.addButton(createButton);
	    buttons.addButton(headlineButton);
	    buttons.addButton(valueButton);
	    buttons.addButton(sizeButton);
	  
	    
	    search = new JTextField();
	    
	    searchbuilder.append(search);
	    searchbuilder.add(searchButton);
	    
	    //Test
	    /*cardbuilder.append("Karte1");
	    cardbuilder.append("Karte2");
	    cardbuilder.append("Karte3");
	    cardbuilder.append("Karte4");
	    cardbuilder.append("Karte5");
	    cardbuilder.append("Karte6");
	    cardbuilder.append("Karte7");
	    cardbuilder.append("Karte8");
	    cardbuilder.append("Karte9");
	    cardbuilder.append("Karte10");
	    cardbuilder.append("Karte11");
	    cardbuilder.append("Karte12");
	    cardbuilder.append("Karte13");
	    cardbuilder.append("Karte14");
	    cardbuilder.append("Karte15");
	    cardbuilder.append("Karte16");
	    cardbuilder.append("Karte17");*/

	    
	   
	    this.setLayout(new BorderLayout());
	    this.add(scrollpane);
	    this.add(cardbuilder.getPanel(), BorderLayout.CENTER);
	    this.add(searchbuilder.getPanel(), BorderLayout.AFTER_LAST_LINE);
	    this.add(buttons.getPanel(), BorderLayout.NORTH);
	    
	    
	}
	
	private void headlineSortActionPerformed(ActionEvent event) {
        
    };
	
	public void showCardsSortedByCreationTime(){
		
		//Test
		/*LinkedList<Card> list = new LinkedList<Card>();
	    list.add(new Card(1, "ich", 2, 10));
	    list.add(new Card(1, "du", 1, 6));
	    list.add(new Card(1, "er", 5, 9));
	    list.add(new Card(1, "er", 1, 3));
	    list.add(new Card(1, "ich", 2, 1));
	    list.add(new Card(1, "sie", 4, 10));
	    
	    Iterator<Card> cit = list.iterator();
	    while(cit.hasNext()){
	    	Card c = cit.next();
	    	cardbuilder.append(c.getHeadline());
	    }*/
	}
	public void showCardsSortedByHeadline(LinkedList<Card> list){
		List cards = SortClass.sortByHeadline(list);
		
		Iterator<Card> test3 = cards.iterator();
  		while(test3.hasNext()){
  			Card c = test3.next();
  			//fehlender Code für die Darstellung der Karte
  		}
	}
	public void showCardsSortedByValue(LinkedList<Card>list){
		List cards = SortClass.sortByValue(list);
		
		Iterator<Card> test = cards.iterator();
  		while(test.hasNext()){
  			Card c = test.next();
  			//fehlender Code für die Darstellung der Karte
  		}
	}
	public void showCardsSortedbySize(LinkedList<Card> list){
		List cards = SortClass.sortBySize(list);
		
		Iterator<Card> test2 = cards.iterator();
  		while(test2.hasNext()){
  			Card c = test2.next();
  			//fehlender Code für die Darstellung der Karte
  		}
	}

}
