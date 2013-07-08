package edu.fh.kanban.ui.view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.SystemColor;
import java.beans.PropertyChangeEvent;

public class CardView extends AbstractView implements View {
	private CardController cardController;
	private JTextField idTextField;
	private JTextField workloadTextField;
	private JToggleButton blockerToggleButton;
	private JRadioButton rdbtnCreated;
	private JRadioButton rdbtnStarted;
	private JRadioButton rdbtnDone;
	private Color background;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnResetAll;
	private JButton btnSaveAll;
	private JComboBox valueComboBox;
	private JTextPane descriptionTextPane;
	private JLabel blockerLabel;
	private JLabel lblStatus;
	
	/**
	 * Create the panel.
	 */
	public CardView(CardController cardController) {
		this.cardController = cardController;
		
		setBackground(SystemColor.menu);
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Karte", TitledBorder.CENTER, TitledBorder.TOP, null, null));
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
				FormFactory.RELATED_GAP_ROWSPEC,
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
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
		
		this.workloadTextField = new JTextField();
		add(this.workloadTextField, "8, 2, fill, fill");
		this.workloadTextField.setColumns(10);
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(wertLabel, "2, 4, right, default");
		
		this.valueComboBox = new JComboBox();
		this.valueComboBox.setToolTipText("1: Blau = Intangible\r\n2: Orange = Standard\r\n3: Rot = Expedite\r\n4: Grün = Fixed date\r\n");
		this.valueComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus", "1: Blau", "2: Orange", "3: Rot", "4: Grün"}));
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
		add(this.rdbtnCreated, "4, 6, left, bottom");
		
		this.rdbtnStarted = new JRadioButton("Started");
		this.buttonGroup.add(rdbtnStarted);
		this.rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnStarted, "4, 8, left, fill");
		
		this.rdbtnDone = new JRadioButton("Done");
		rdbtnDone.setBackground(Color.LIGHT_GRAY);
		this.buttonGroup.add(rdbtnDone);
		this.rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnDone, "4, 10, left, fill");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(beschreibungLabel, "6, 8, right, top");
		
		this.descriptionTextPane = new JTextPane();
		add(this.descriptionTextPane, "8, 8, 3, 3, fill, fill");
		
		this.background = Color.LIGHT_GRAY;
		this.rdbtnCreated.setBackground(Color.LIGHT_GRAY);
		this.rdbtnStarted.setBackground(Color.LIGHT_GRAY);
		this.rdbtnDone.setBackground(Color.LIGHT_GRAY);
		
		this.btnSaveAll = new JButton("Speichern");
		add(this.btnSaveAll, "6, 12, fill, fill");
		
		this.btnSaveAll.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveAllActionPerformed(e);
				} catch (NumberFormatException ex) { 
					Component parent = getJPanel();
					JOptionPane.showMessageDialog(parent,"Keine Nullwerte/ Ungütlige Werte(ID/Workload/Description)!","ERROR!" , JOptionPane.ERROR_MESSAGE);
					btnDeleteAllActionPerformed(e);
				// cardViewException
				} catch (cardViewException ex) {
					
				}				
			}
		});
		
		this.btnResetAll = new JButton("Löschen");
		add(this.btnResetAll, "8, 12, fill, fill");
		this.btnResetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDeleteAllActionPerformed(e);
			}
		});
		

	}
	private void btnSaveAllActionPerformed(ActionEvent e) throws cardViewException {
		//idTextField.getText().isEmpty()==true || workloadTextField.getText().isEmpty()==true || //
		if( valueComboBox.getSelectedIndex() == -1 || valueComboBox.getSelectedIndex() == 0  || getJRadioButton() == -1 || descriptionTextPane.getText().isEmpty()==true){
			throw new cardViewException();
		
		}
		else {
			cardController.changeCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(workloadTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), getJRadioButton());
			setJPanelColor();
		 }	
	}
	
	private void btnDeleteAllActionPerformed(ActionEvent e) {
		resetAllValues();
		setJPanelColor();						
		cardController.deleteCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(workloadTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), getJRadioButton());
	}
	
	public Color getBackgroundColor() {
		return this.background;
	}
	
	class cardViewException extends Exception {
		cardViewException() {
			super();
			Component parent = getJPanel();
			JOptionPane.showMessageDialog(parent,"Fehlerhafte Eingabe(JRadio/ Value/ Description)!","ERROR!" , JOptionPane.ERROR_MESSAGE);
			resetAllValues();
			setJPanelColor();
			cardController.deleteCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(workloadTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), getJRadioButton());

		}
	}
	
	public Component getJPanel() {
		return this;
	}
	/**
	 * Methode zum Einfärben des Panels entsprechen mit der passenden Hintergrundfarbe bezüglich der Wertauswahl.
	 */
	public void setJPanelColor(){
		int x = this.valueComboBox.getSelectedIndex();
		if(x == 0||x==-1) {
			this.background = Color.LIGHT_GRAY;
		}
		if(x == 1) {
			this.background = Color.blue;
		}
		if(x == 2) {
			this.background = Color.orange;
		}
		if(x == 3) {
			this.background = Color.red;
		}
		if(x == 4) {
			this.background = Color.green;
		}
		this.setBackground(background);
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
	
	/**
	 * Zurücksetzen aller Attribut-Werte
	 */
	public void resetAllValues() {
		this.idTextField.setText("00");
		this.workloadTextField.setText("00");
		this.descriptionTextPane.setText(" ");
		this.valueComboBox.setSelectedIndex(0);
		this.blockerToggleButton.setSelected(false);
		this.rdbtnCreated.setSelected(false);
		this.rdbtnStarted.setSelected(false);
		this.rdbtnDone.setSelected(false);
	}
	
	/**
	 * 
	 */
	@Override
	public void modelPropertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
}
