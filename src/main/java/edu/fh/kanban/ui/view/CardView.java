package edu.fh.kanban.ui.view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.domain.Column;
import edu.fh.kanban.domain.Preference;
import edu.fh.kanban.ui.controller.BoardController;
import edu.fh.kanban.ui.controller.CardController;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;

import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource.TitledBorderUIResource;
import javax.swing.ButtonModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import org.apache.commons.lang3.RandomStringUtils;

import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.SwingConstants;

public class CardView extends AbstractView implements View {
	private CardController cardController;

	private Preference pref;
	private JTextField idTextField;
	private JTextField sizeTextField;
	private JToggleButton blockerToggleButton;
	private JRadioButton rdbtnCreated;
	private JRadioButton rdbtnStarted;
	private JRadioButton rdbtnDone;
	private Color background;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnDeleteAll;
	private JButton btnSaveAll;
	private JComboBox valueComboBox;
	private JTextPane descriptionTextPane;
	private JLabel blockerLabel;
	private JLabel lblStatus;
	private String cardTitel = "";
	private TitledBorder tb;
	private JButton btnAddTitel;
	private JButton btnResetAll;
	private String id;
	public static final int  ID_LENGHT = 3;
	private JButton saveNewCardButton;
	private JComboBox columnComboBox;
	private Card currentCard;

	private int oldValue;
	private boolean oldBlocker;
	private Color oldBackColor;
	private String oldDescription;
	private Integer oldCardId;
	private Integer oldSize;
	private int oldJRadio;
	private int count = 0;
	private JLabel lblSpalte;
	
	/**
	 * Create the panel.
	 */
	public CardView(CardController cardController) {
		
		this.cardController = cardController;
		
		System.out.println("Konstruktoraufruf!:");
		
		this.background = Color.LIGHT_GRAY;
		this.tb = new TitledBorder(new LineBorder(new Color(0, 0, 0)), cardTitel, TitledBorder.CENTER, TitledBorder.TOP, null, null);
		setBorder(tb);
		FormLayout formLayout = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default):grow"),
				ColumnSpec.decode("max(4dlu;default)"),
				ColumnSpec.decode("max(45dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(1dlu;default)"),},
			new RowSpec[] {
				RowSpec.decode("6dlu"),
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,});
		setLayout(formLayout);
		
		JLabel idLabel = DefaultComponentFactory.getInstance().createLabel("ID:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(idLabel, "2, 2, right, fill");
		
		this.idTextField = new JTextField();
		add(this.idTextField, "4, 2, fill, fill");
		this.idTextField.setColumns(1);
		JLabel aufwandLabel = DefaultComponentFactory.getInstance().createLabel("Aufwand:");
		aufwandLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(aufwandLabel, "6, 2, right, default");
		
		this.id = RandomStringUtils.randomNumeric(ID_LENGHT);
		this.setIdTextField(id);
		this.idTextField.setEnabled(false);
		
		this.sizeTextField = new JTextField();
		add(this.sizeTextField, "8, 2, fill, fill");
		this.sizeTextField.setColumns(10);
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(wertLabel, "2, 4, right, default");
		
		this.valueComboBox = new JComboBox();
		this.valueComboBox.setToolTipText("DefaultSetUp \r\n1:Blau = Intangible|\r\n 2:Orange = Standard|\r\n 3:Rot = Expedite|\r\n 4:Grün = Fixed date\r\n");
		this.valueComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus", "1: Intangible", "2: Standard", "3: Expedite", "4: Fixed Date"}));
		add(this.valueComboBox, "4, 4, fill, default");
		
		this.blockerLabel = DefaultComponentFactory.getInstance().createLabel("Blocker:");
		this.blockerLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.blockerLabel, "6, 4, right, top");
		
		this.blockerToggleButton = new JToggleButton("Blocker");
		this.blockerToggleButton.setForeground(Color.RED);
		this.blockerToggleButton.setBackground(Color.BLACK);
		this.blockerToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.blockerToggleButton, "8, 4, 1, 3, fill, fill");
		
		lblStatus = DefaultComponentFactory.getInstance().createLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblStatus, "2, 6");
		
		this.rdbtnCreated = new JRadioButton("Created");
		this.buttonGroup.add(rdbtnCreated);
		this.rdbtnCreated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnCreated, "4, 6, left, fill");
		
		this.rdbtnStarted = new JRadioButton("Started");
		this.buttonGroup.add(rdbtnStarted);
		this.rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnStarted, "4, 8, left, fill");
		
		this.rdbtnDone = new JRadioButton("Done");
		;
		this.buttonGroup.add(rdbtnDone);
		this.rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnDone, "4, 10, left, fill");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(beschreibungLabel, "6, 8, right, top");
		
		this.descriptionTextPane = new JTextPane();
		add(this.descriptionTextPane, "8, 8, 3, 3, fill, fill");
		
		
		
		this.btnAddTitel = new JButton("Titel");
		add(this.btnAddTitel, "4, 12, fill, fill");
		
		this.btnAddTitel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnTitelActionPerformed(e);
			}
		});
		
		this.btnSaveAll = new JButton("Save");
		
		add(this.btnSaveAll, "6, 12, fill, fill");
		if(getCardTitel().isEmpty()==true) {
			this.btnSaveAll.setEnabled(false);
		}
		btnSaveAll.setVisible(false);
		
		
		this.saveNewCardButton = new JButton("Save");
		add(this.saveNewCardButton, "6, 12, fill, fill");
		if(getCardTitel().isEmpty()==true) {
			this.saveNewCardButton.setEnabled(false);
		}
		
		this.btnSaveAll.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveAllActionPerformed(e);
				} catch (NumberFormatException ex) { 
					Component parent = getJPanel();
					JOptionPane.showMessageDialog(parent,"Keine Nullwerte/ Ungütlige Werte(ID/Size/Description)!","ERROR!" , JOptionPane.ERROR_MESSAGE);
					btnDeleteAllActionPerformed(e);
				} catch (cardViewException ex) {
					
				}				
			}
		});
		
		this.saveNewCardButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
					btnSaveNewCardActionPerformed(e);
					Component parent = getJPanel();
					JOptionPane.showMessageDialog(parent,"Keine Nullwerte/ Ungütlige Werte(ID/Size/Description)!","ERROR!" , JOptionPane.ERROR_MESSAGE);		
			}

			
		});
		
		this.btnDeleteAll = new JButton("Delete");
		add(this.btnDeleteAll, "8, 12, fill, fill");
		
		this.btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteAllActionPerformed(e);
			}
		});
		
		this.btnResetAll = new JButton("Reset");
		add(this.btnResetAll, "10, 12");
		
		lblSpalte = new JLabel("Spalte: ");
		lblSpalte.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpalte.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSpalte, "8, 14");
		
		columnComboBox = new JComboBox();
		columnComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		columnComboBox.setAlignmentX(SwingConstants.RIGHT);
		this.columnComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus", "1: Next", "2: Development", "3: Test","4:Done"}));
		add(columnComboBox, "10, 14");
		
		
		
		
	
	
		if(getCardTitel().isEmpty()==true) {
			this.btnResetAll.setEnabled(false);
		}
		
		this.btnResetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnResetAllActionPerformed(e);
			}
		});
		
	}

	public void setOldCardId(int i) {
		this.oldCardId = i;
	}
	
	public int getOldCardId() {
		return this.oldCardId;
	}
	
	
	public String getCardTitel() {
		return this.cardTitel;
	}
	
	public void setCardTitel(String n) {
		this.tb.setTitle(n);
		this.setName(n);
		this.saveNewCardButton.setEnabled(true);
		
	}
	
	/**
	 * 
	 * @param e
	 */
	private void btnTitelActionPerformed(ActionEvent e) {
		this.cardTitel = JOptionPane.showInputDialog("Eingabe des Titels:",this.cardTitel);
		setCardTitel(cardTitel);
		this.btnSaveAll.setEnabled(true);
	}
	/**
	 * Ursprungszustand (beim ersten Anlegen) wird gespeichert um die Möglichkeit zu haben auf diesen Zustand zurücksetzen zu können.
	 * Fängt Fehler bei falscher/fehlender Eingabe des Wertes, des Zeitpunkts(JRadioButtons) oder der Beschreibung ab.
	 * @param e
	 * @throws cardViewException
	 */
	private void btnSaveAllActionPerformed(ActionEvent e) throws cardViewException {
		
		
		if( valueComboBox.getSelectedIndex() == -1 || valueComboBox.getSelectedIndex() == 0  || getJRadioButton() == -1 || descriptionTextPane.getText().isEmpty()==true){
			throw new cardViewException();
		
		}
		else {
			if(count <= 0) { 
				saveAllOldValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(sizeTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackground(), blockerToggleButton.isSelected(), getJRadioButton());count+=1;
			}
			cardController.changeCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(sizeTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackground(), blockerToggleButton.isSelected(), getJRadioButton());
			setJPanelColorForCreatingANewCard();
			this.btnResetAll.setEnabled(true);
		 }
	}
	
	/**
	 * Setzt alle Werte in der View und im Modell auf den Grundzustand.
	 * @param e
	 */
	private void btnDeleteAllActionPerformed(ActionEvent e) {
		resetAllValues();
		setJPanelColorForCreatingANewCard();
	}
	
	/**
	 * 
	 * @param e
	 */
	private void btnResetAllActionPerformed(ActionEvent e) {
		setAllToOldValues();
	}
	
	private void btnSaveNewCardActionPerformed(ActionEvent e) {
		Date created;
		Date started;
		Date done;
		int i = getJRadioButton();
		if(i==0){
			created = new Date();
			started = null;
			done = null;
			System.out.println(created);
		}
		else if(i==1){
			created = null;
			started = new Date();
			done = null;
		}
		else if(i==2){
			created = null;
			started = null;
			done = new Date();
		}
		else{
			created = null;
			started = null;
			done = null;
		}
		
		currentCard.setId(Integer.parseInt(idTextField.getText()));
		currentCard.setValue(valueComboBox.getSelectedIndex());
		currentCard.setBlocker(blockerToggleButton.isSelected());
		currentCard.setDescription(descriptionTextPane.getText());
		currentCard.setSize(Integer.parseInt(sizeTextField.getText()));
		currentCard.setHeadline(this.cardTitel);
		currentCard.setCreated(created);
		currentCard.setDone(done);
		currentCard.setStarted(started);
		
		
		cardController.addModel(currentCard);
		cardController.addView(this);
		currentCard.setChanged(columnComboBox.getSelectedIndex());
	}
	
	/**
	 * Speichert die beim Erstellen gespeicherten Werte um einen Reset zu ermöglichen.
	 * @param oldCardId
	 * @param oldWorkload
	 * @param oldDescription
	 * @param oldValue
	 * @param oldbackColor
	 * @param oldBlocker
	 * @param oldJRadio
	 */

	public void saveAllOldValues(int oldCardId, int oldWorkload, String oldDescription, int oldValue, Color oldbackColor, boolean oldBlocker, int oldJRadio) {
	
		this.oldCardId = oldCardId;
		this.oldSize = oldWorkload;
		this.oldDescription = oldDescription;
		this.oldValue = oldValue;
		this.oldBackColor = oldbackColor;
		this.oldBlocker = oldBlocker;
		this.oldJRadio = oldJRadio;
	}
	
	/**
	 * Setzt die beim Erstellen gespeicherten Werte passend in die Gui ein.
	 */
	public void setAllToOldValues() {
		
		this.setIdTextField(oldCardId.toString());
		this.setSizeTextField(oldSize.toString());
		this.setDescriptionTextPane(oldDescription);
		this.setValueComboBox(oldValue);
		this.setBlockerToggleButton(oldBlocker);
		this.setOldJRadioButton(oldJRadio);
		this.setJPanelColorForCreatingANewCard();
	}
	 
	
	/**
	 * Um alle Felder und Buttons für die Standard-View im Board zu sperren.
	 */
	public void setAllDisabledForBoard() {
		this.idTextField.setEnabled(false);
		this.sizeTextField.setEnabled(false);
		this.descriptionTextPane.setEnabled(false);
		this.rdbtnCreated.setEnabled(false);
		this.rdbtnDone.setEnabled(false);
		this.rdbtnStarted.setEnabled(false);
		this.valueComboBox.setEnabled(false);
		this.blockerToggleButton.setEnabled(false);
		this.btnSaveAll.setEnabled(false);
		this.btnDeleteAll.setEnabled(false);
		this.btnDeleteAll.setVisible(false);
		this.btnSaveAll.setVisible(false);
		this.btnAddTitel.setVisible(false);
		this.btnAddTitel.setEnabled(false);
		this.btnResetAll.setEnabled(false);
		this.btnResetAll.setVisible(false);
		this.saveNewCardButton.setEnabled(false);
		this.saveNewCardButton.setVisible(false);

	}
	
	/**
	 * Um alle Felder und Bottons für die Bearbeitung auf dem Board freizugeben.
	 */
	public void setAllEnabledForBoard() {
		this.sizeTextField.setEnabled(true);
		this.descriptionTextPane.setEnabled(true);
		this.rdbtnCreated.setEnabled(true);
		this.rdbtnDone.setEnabled(true);
		this.rdbtnStarted.setEnabled(true);
		this.valueComboBox.setEnabled(true);
		this.blockerToggleButton.setEnabled(true);
		this.btnSaveAll.setEnabled(true);
		this.btnDeleteAll.setEnabled(true);
		this.btnDeleteAll.setVisible(true);
		this.btnSaveAll.setVisible(true);
		this.btnResetAll.setVisible(true);
		this.btnResetAll.setEnabled(true);
		
		
	}
	
	public JTextField getSizeTextField() {
		return this.idTextField;
	}
	
	/**
	 * Rückgabe des Hintergrundfarbe.
	 * @return
	 */
	@Override
	public Color getBackground() {
		return this.background;
		
	}
	
	/**
	 * Eigene Exceptionklasse zur Fehlerbehandlung der JRadios/ der ComboBox/ des TextPanes.
	 * Bei Fehler wird ein JOptionPane gezeigt der auf die fehlerhafte Eingabe hinweist.
	 * @author AdminMax
	 *
	 */
	class cardViewException extends Exception {
		cardViewException() {
			super();
			Component parent = getJPanel();
			JOptionPane.showMessageDialog(parent,"Fehlerhafte Eingabe(JRadio/ Value/ Description)!","ERROR!" , JOptionPane.ERROR_MESSAGE);
			resetAllValues();
			setJPanelColorForCreatingANewCard();
			//cardController.deleteCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(sizeTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), RadioButton());

		}
	}
	
	public Component getJPanel() {
		return this;
	}
	/** Zum neue Karte anlegen:
	 * Methode zum Einfärben des Panels entsprechen mit der passenden Hintergrundfarbe bezüglich der Wertauswahl.
	 */
	public void setJPanelColorForCreatingANewCard(){
		int x = this.valueComboBox.getSelectedIndex();
		
		//Auswertung der Auswahl aus der ComboBox:
		
		//keine Auswahl("Wähle aus" oder gar keine Auswahl)
		if(x == 0||x==-1) {
			this.background = Color.LIGHT_GRAY;
		}
		//Intengiable
		if(x == 1) {
			int[] intangible = pref.getColorIntagible();
			this.background = new Color(intangible[0],intangible[1],intangible[2]);
		
						
		}
		//Standard
		if(x == 2) {
			int[] standard = pref.getColorStandard();
			this.background = new Color(standard[0],standard[1],standard[2]);
		}
		//Expedite
		if(x == 3) {
			int[] expedite = pref.getColorExpedite();
			this.background = new Color(expedite[0],expedite[1],expedite[2]);
		}
		//Fixed Date
		if(x == 4) {
			int[] fixedDate = pref.getColorFixed();
			this.background = new Color(fixedDate[0],fixedDate[1],fixedDate[2]);
		}
		//this.setBackground(background);
		this.rdbtnCreated.setBackground(background);
		this.rdbtnStarted.setBackground(background);
		this.rdbtnDone.setBackground(background);
		
	}
	
	/** Zum anzeigen einer eingelesenen Karte:
	 * Methode zum Einfärben des Panels entsprechen mit der passenden Hintergrundfarbe bezüglich der Wertauswahl.
	 */
	public void setJPanelColorForCreatingAExistingCard(int x){
		
		
		//Auswertung der Auswahl aus der ComboBox:
		
		//keine Auswahl("Wähle aus" oder gar keine Auswahl)
		if(x == 0||x==-1) {
			this.background = Color.LIGHT_GRAY;
		}
		//Intengiable
		if(x == 1) {
			int[] intangible = pref.getColorIntagible();
			this.background = new Color(intangible[0],intangible[1],intangible[2]);
						
		}
		//Standard
		if(x == 2) {
			int[] standard = pref.getColorStandard();
			this.background = new Color(standard[0],standard[1],standard[2]);

		}
		//Expedite
		if(x == 3) {
			int[] expedite = pref.getColorExpedite();
			this.background = new Color(expedite[0],expedite[1],expedite[2]);
		}
		//Fixed Date
		if(x == 4) {
			int[] fixedDate = pref.getColorFixed();
			this.background = new Color(fixedDate[0],fixedDate[1],fixedDate[2]);
		}
		//this.setBackground(background);
		this.rdbtnCreated.setBackground(background);
		this.rdbtnStarted.setBackground(background);
		this.rdbtnDone.setBackground(background);
		
	}
	
	/**
	 * Methode zur Rückgabe der ViewKomponente.
	 */
	public JComponent getComponent() {	
		return this;
	}
	
	/**
	 * Gibt den jeweiligen aktuellen Bearbeitungszustand zurück.
	 * @return
	 */
	public int getJRadioButton() {
		if(this.rdbtnCreated.isSelected()){
			return 0;
		}
		else if(this.rdbtnStarted.isSelected()){
			return 1;
		}
		else if(this.rdbtnDone.isSelected()) {
			return 2;	
		}
		return -1;
	}
	public Card getNewCard(){
		return currentCard;
	}
	public void setNewCard(Card currentCard){
		this.currentCard = currentCard;
	}
	/**
	 * Zurücksetzen aller Attribut-Werte
	 */
	public void resetAllValues() {
		this.sizeTextField.setText("");
		this.descriptionTextPane.setText("");
		this.valueComboBox.setSelectedIndex(0);
		this.blockerToggleButton.setSelected(false);
		this.buttonGroup.clearSelection();

	}
	
	/**
	 * Setzt das IdTextFeld auf einen bestimmten Wert.
	 * @param n
	 */ 
	public String setIdTextField(String n) {
		this.idTextField.setText(n);	
		return this.idTextField.getText();
		
	}
	
	public String getIdTextField() {
		return this.idTextField.getText();
	}
	
	/**
	 * Setzt das WorkloadTextFeld auf einen bestimmten Wert mit Hilfe des übergebenen Strings.
	 * @param n
	 */
	public String setSizeTextField(String n) {
		this.sizeTextField.setText(n);
		return this.sizeTextField.getText();
	}
	
	public String getSizeTextFieldValue() {
		return this.sizeTextField.getText();
	}
	
	/**
	 * Setzt die ComboBox auf den übergebenen Wert. Und passt die Hintergrundfarbe entsprechend der Auswahl ein
	 * @param i
	 */
	public void setValueComboBox(int i) {
		this.valueComboBox.setSelectedIndex(i);
		
	}
	
	/**
	 * Füllt die Beschreibung mit dem übergebenen String.
	 * @param n
	 */
	public void setDescriptionTextPane(String n) {
		this.descriptionTextPane.setText(n);
	}
	
	/**
	 * Setzt den BlockerButton auf den entsprechenden Zustand.
	 * @param b
	 */
	public void setBlockerToggleButton(boolean b) {
		this.blockerToggleButton.setSelected(b);
	}
	
	/**
	 * Setzt die Hintergrundfarbe auf die übergebene Farbe.
	 * @param b
	 */
	public void setBackgroundColor(Color b) {
		this.background = b;
	}
	
	/**
	 * Setzt mit Hilfe der übergebenen Daten die Auswahl auf den passenden JRadioButton.
	 * @param c
	 * @param s
	 * @param d
	 */
	public void setJRadioButton(Date c, Date s, Date d) {
	
		if(c != null && s == null && d == null) {
			this.rdbtnCreated.setSelected(true);
		}
		if(c != null && s != null && d == null) {
			this.rdbtnStarted.setSelected(true);
			
		}
		if(c != null && s != null && d != null) {
			this.rdbtnDone.setSelected(true);
		}
	}
	
	/**
	 * Set auf den passenden JRadioButton durch den übergebenen Interger-Wert.
	 * @param i
	 */
	public void setOldJRadioButton(int i) {
		if(i==0){
			this.rdbtnCreated.setSelected(true);
		}
		else if(i==1){
			this.rdbtnStarted.setSelected(true);
		}
		else if(i==2) {
			this.rdbtnDone.setSelected(true);
		}
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		System.out.println("in mPC() ankegekommen");
		switch (event.getPropertyName()) {
		case CardController.NEWCARD_PROPERTY: {
			System.out.println("neue Karte hinzugefügt");
	         cardController.addCardbyColumn(event);
            break;
		}
		
		
		case CardController.CARDID_PROPERTY: {
			 String newStringValue = event.getNewValue().toString();
             if (!idTextField.getText().equals(newStringValue)) {
                 idTextField.setText(newStringValue);
             }
             break;
		}
		case CardController.BACKGROUND_PROPERTY: {
			
           
            break;
		}
		
		case CardController.SIZE_PROPERTY: {
			String newStringValue = event.getNewValue().toString();
            if (!sizeTextField.getText().equals(newStringValue)) {
            	sizeTextField.setText(newStringValue);
            }
            break;
			
		}
		
		case CardController.VALUE_PROPERTY: {
			try{
				System.out.println("CardView: Event angekommen");
				int newIntValue = Integer.parseInt(event.getNewValue().toString());
				System.out.println("Value eingelesen");
				int currentSelection = valueComboBox.getSelectedIndex();
				if (!(newIntValue == currentSelection)) {
	            	valueComboBox.setSelectedIndex(newIntValue);
	            }
				
				
		           setJPanelColorForCreatingAExistingCard(currentSelection);
		           System.out.println("Background geändert");
		          
		          //this.removeAll();
		          this.updateUI();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			System.out.println("Ende: Value Property");
			
            break;
		}
		
		case CardController.BLOCKER_PROPERTY: {
			Object newObject = event.getNewValue();
			Card newCard = 	(Card) newObject;
			boolean newBlocker = newCard.getBlocker();
			boolean oldBlocker = blockerToggleButton.isSelected();
		
            if (!newBlocker==oldBlocker) {
            	blockerToggleButton.setSelected(newBlocker);
            }
            break;
		}
		
		
		case CardController.DESCRIPTION_PROPERTY: {
			String newStringValue = event.getNewValue().toString();
            if (!descriptionTextPane.getText().equals(newStringValue)) {
            	descriptionTextPane.setText(newStringValue);
            }
            break;
		}
		
		case CardController.JRADIOBUTTON_PROPERTY: {
			Object newObject = event.getNewValue();
			Card card = 	(Card) newObject;
			
			if(card!= null && card.getStartedString() == null) {
				rdbtnCreated.isSelected();
			}
			if(card.getStartedString()!= null && card.getDone() == null) {
				rdbtnStarted.isSelected();
			}
			if(card.getDescription()!= null) {
				rdbtnDone.isSelected();
			}
		}
	  }
	}

	public void setPreference(Preference pref) {
		this.pref = pref;
	}

	public Card getCreatedCard() {
		return this.currentCard;
		
	}

	public void setColumnList(LinkedList<Column> columnList) {
		
		
	}


	
}

