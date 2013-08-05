package edu.fh.kanban.ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.ButtonBarBuilder2;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

import edu.fh.kanban.domain.Card;

import edu.fh.kanban.ui.controller.BacklogController;

public class BacklogView extends AbstractView implements View{
	
	private JButton headlineButton;
	private JButton workloadButton;
	private JButton sizeButton;
	private JButton createButton;
	private JButton searchButton;
	private JTextField search;
	private DefaultFormBuilder cardbuilder;
	private JScrollPane scrollpane;
	private JPanel cardpanel;
	

	
	
	
	private BacklogController backlogController;
	
	@Override
	public JComponent getComponent() {
		return this;
	}
	
	public BacklogView(BacklogController backlogController){
		
		this.backlogController = backlogController;
		
		this.setVisible(true);
	}
	
	public void showBacklog(){
		
		cardpanel = new JPanel();
		JPanel buttonpanel = new JPanel();
		
		scrollpane = new JScrollPane(cardpanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		
		//FormLayout cardLayout = new FormLayout("160dlu,10dlu,160dlu,10dlu,160dlu", "130dlu,130dlu,130dlu");
		FormLayout cardLayout = new FormLayout("160dlu,10dlu,160dlu,10dlu,160dlu", getRows(backlogController.getCardList()));
		//FormLayout cardLayout = new FormLayout("160dlu,10dlu,160dlu,10dlu,160dlu");
		FormLayout searchLayout = new FormLayout("200dlu,10dlu,p");
        
		cardbuilder = new DefaultFormBuilder(cardLayout);
        DefaultFormBuilder searchbuilder = new DefaultFormBuilder(searchLayout); 
        
        searchbuilder.setDefaultDialogBorder();
        cardbuilder.setDefaultDialogBorder();
        
		ButtonBarBuilder2 buttons = ButtonBarBuilder2.createLeftToRightBuilder();
		
		headlineButton = new JButton("Überschrift");
		workloadButton = new JButton("Aufwand");
		sizeButton = new JButton("Größe");
		createButton = new JButton("Erstellung");
		searchButton = new JButton("suche");
		
		
		buttons.addButton(createButton);
	    buttons.addButton(headlineButton);
	    buttons.addButton(workloadButton);
	    buttons.addButton(sizeButton);
	  
	    
	    search = new JTextField();
	    
	    searchbuilder.append(search);
	    searchbuilder.add(searchButton);
	    
	    
	   
	    this.setLayout(new BorderLayout());
	    this.add(scrollpane);
	    scrollpane.setViewportView(cardpanel);
	    
	    buttonpanel.add(buttons.getPanel());
	    buttonpanel.add(searchbuilder.getPanel());
	    this.add(buttonpanel, BorderLayout.NORTH);
	    
	    cardpanel.add(cardbuilder.getPanel());
	    this.add(cardpanel, BorderLayout.CENTER);
		
		/* ******************************************************************************** */
		//ActionListener
		headlineButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	removeAll();
            	showBacklog();
            	showCardsSortedByHeadline(backlogController.getCardList());
            	//System.out.println("HeadlineButton gedrückt");
            	updateUI();
            }
        });
		workloadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	removeAll();
            	showBacklog();
            	showCardsSortedByValue(backlogController.getCardList());
            	//System.out.println("ValueButton gedrückt");
            	updateUI();
            }
        });
		sizeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	removeAll();
            	showBacklog();
            	showCardsSortedbySize(backlogController.getCardList());
            	//System.out.println("SizeButton gedrückt");
            	updateUI();
            }
        });
		createButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	removeAll();
            	showBacklog();
            	showCardsSortedByCreationTime(backlogController.getCardList());
            	//System.out.println("CreateButton gedrückt");
            	updateUI();
            }
        });
		searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
            	String searchString = search.getText();
            	if(!searchString.equals("")){
            		removeAll();
                	showBacklog();
                	showResultCards(searchString, backlogController.getCardList());
                	updateUI();
            	}
            	
            	
            }
        });
	}
	
	public String getRows(LinkedList<Card> list){
		String s = "130dlu";
		int anzahl=list.size();
		//System.out.println("Elemente "+ anzahl);
		if(anzahl%3==0){
			anzahl=anzahl/3;
		}else{
			anzahl=anzahl/3+1;
			//System.out.println("Anzahl reihen: "+anzahl);
		}
		if(anzahl>1){
			for(int i=2; i<=anzahl; i++){
				s= s.concat(",130dlu");
			}
		}
		//System.out.println(s);
		return s;
	}
	
	public void showResultCards(String s, LinkedList<Card> list){
		
		LinkedList<Card> result = backlogController.search(s);
		
		Iterator<Card> test = result.iterator();
  		while(test.hasNext()){
  			Card c = test.next();
  			
  			CardViewBoard cardView = new CardViewBoard(c);
  			cardView.setBackground(c.getBackGround());
  			cardbuilder.append(cardView);
  		}
	}
	
	
	public void showCardsSortedByCreationTime(LinkedList<Card> list){
		
		if(list == null){
			System.out.println("Cardliste leer!");
		}
		else{
			Iterator<Card> test = list.iterator();
	  		while(test.hasNext()){
	  			Card c = test.next();
	  			
	  			CardViewBoard cardView = new CardViewBoard(c);
	  			cardView.setBackground(c.getBackGround());
	  			cardbuilder.append(cardView);
	  		}
		}
		
		
		
	}
	public void showCardsSortedByHeadline(LinkedList<Card> list){
		
		if(list == null){
			System.out.println("Cardliste leer!");
		}else{
			
			List cards = backlogController.sortByHeadline();
			
			Iterator<Card> test3 = cards.iterator();
	  		while(test3.hasNext()){
	  			Card c = test3.next();
	  			CardViewBoard cardView = new CardViewBoard(c);
	  			cardView.setBackground(c.getBackGround());
	  			cardbuilder.append(cardView);
	  		}
		}
		
		
	}
	public void showCardsSortedByValue(LinkedList<Card>list){
		
		if(list == null){
			System.out.println("Cardliste leer!");
		}else{
			
			List cards = backlogController.sortByValue();
			
			Iterator<Card> test = cards.iterator();
	  		while(test.hasNext()){
	  			Card c = test.next();
	  			CardViewBoard cardView = new CardViewBoard(c);
	  			cardView.setBackground(c.getBackGround());
	  			cardbuilder.append(cardView);
	  		}
		}
		
	}
	public void showCardsSortedbySize(LinkedList<Card> list){
		
		if(list == null){
			System.out.println("Cardliste leer!");
		}else{
			
			List cards = backlogController.sortBySize();
			
			Iterator<Card> test2 = cards.iterator();
	  		while(test2.hasNext()){
	  			Card c = test2.next();
	  			CardViewBoard cardView = new CardViewBoard(c);
	  			cardView.setBackground(c.getBackGround());
	  			cardbuilder.append(cardView);
	  		}
		}
		
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}