package edu.fh.kanban.ui.view;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import edu.fh.kanban.ui.controller.CardController;

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
	private JButton btnIdDelete;
	private JButton btnIdSave;
	private JComboBox valueComboBox;
	private JButton btnWorkloadSave;
	private JButton btnWorkloadDelete;
	private JTextPane descriptionTextPane;
	private JButton btnDescriptionSave;
	private JButton btnDescriptionDelete;
	private JButton btnValueSave;
	private JButton btnValueDelete;
	private JLabel blockerLabel;
	private JButton btnJRadioSelectedDelete;
	
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
				ColumnSpec.decode("max(40dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default):grow"),
				ColumnSpec.decode("max(4dlu;default)"),
				ColumnSpec.decode("max(50dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),},
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
		
		this.btnIdSave = new JButton("Speichern");
		add(this.btnIdSave, "6, 2");
		
		this.btnIdDelete = new JButton("Löschen");
		add(this.btnIdDelete, "6, 4");
		
		this.workloadTextField = new JTextField();
		add(this.workloadTextField, "10, 2, fill, fill");
		this.workloadTextField.setColumns(10);
		
		this.descriptionTextPane = new JTextPane();
		add(this.descriptionTextPane, "8, 12, 3, 5, fill, fill");
		
		this.valueComboBox = new JComboBox();
		this.valueComboBox.setToolTipText("1: Blau = Intangible\r\n2: Orange = Standard\r\n3: Rot = Expedite\r\n4: Grün = Fixed date\r\n");
		this.valueComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus", "1: Blau", "2: Orange", "3: Rot", "4: Grün"}));
		add(this.valueComboBox, "4, 6, fill, default");
		
		this.blockerToggleButton = new JToggleButton("Blocker");
		this.blockerToggleButton.setForeground(Color.RED);
		this.blockerToggleButton.setBackground(Color.BLACK);
		this.blockerToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.blockerToggleButton, "10, 8, 2, 2, fill, fill");
		
		this.background = Color.LIGHT_GRAY;
		
		this.btnIdSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIdSaveActionPerformed(e);				
			}
		});
		this.btnIdDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIdDeleteActionPerformed(e);
			}
		});
		
		JLabel aufwandLabel = DefaultComponentFactory.getInstance().createLabel("Aufwand:");
		aufwandLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(aufwandLabel, "8, 2, right, default");
		
		
		
		this.btnWorkloadSave = new JButton("Speichern");
		add(this.btnWorkloadSave, "12, 2");
		
		
		
		this.btnWorkloadDelete = new JButton("Löschen");
		add(this.btnWorkloadDelete, "12, 4");
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(wertLabel, "2, 6, right, default");
		
		
		
		this.btnValueSave = new JButton("Speichern");
		add(this.btnValueSave, "6, 6");
		
		this.btnValueDelete = new JButton("Löschen");
		add(this.btnValueDelete, "6, 8, default, top");
		
		
		
		this.blockerLabel = DefaultComponentFactory.getInstance().createLabel("Blocker:");
		this.blockerLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.blockerLabel, "8, 8, right, top");
		
		this.rdbtnCreated = new JRadioButton("Created");
		this.buttonGroup.add(rdbtnCreated);
		this.rdbtnCreated.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnCreated, "4, 10, left, fill");
		
		this.rdbtnStarted = new JRadioButton("Started");
		this.buttonGroup.add(rdbtnStarted);
		this.rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnStarted, "4, 12, left, fill");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(beschreibungLabel, "6, 12, right, top");
		
		this.btnDescriptionSave = new JButton("Speichern");
		add(this.btnDescriptionSave, "12, 12, default, bottom");
		
		this.rdbtnDone = new JRadioButton("Done");
		this.buttonGroup.add(rdbtnDone);
		this.rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(this.rdbtnDone, "4, 14, left, fill");
		
		
		
		this.btnDescriptionDelete = new JButton("Löschen");
		add(this.btnDescriptionDelete, "12, 14");
		
		this.btnJRadioSelectedDelete = new JButton("Löschen");
		add(this.btnJRadioSelectedDelete, "4, 16");

	}
	private void btnIdSaveActionPerformed(ActionEvent e) {
		cardController.changeCardId(Integer.parseInt(idTextField.getText()), Integer.parseInt(workloadTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), getJRadioButton());
		setJPanelColor();
		
	}
	private void btnIdDeleteActionPerformed(ActionEvent e) {
		resetAllValues();
		setJPanelColor();								//descriptionTextPane.getText()
		cardController.deleteCardViewValues(Integer.parseInt(idTextField.getText()), Integer.parseInt(workloadTextField.getText()), descriptionTextPane.getText(), valueComboBox.getSelectedIndex(), getBackgroundColor(), blockerToggleButton.isSelected(), getJRadioButton());
	}
	
	public Color getBackgroundColor() {
		return this.background;
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
		else return 2;	
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
		this.rdbtnCreated.setSelected(true);
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
