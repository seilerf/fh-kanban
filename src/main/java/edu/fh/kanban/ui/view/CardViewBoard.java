package edu.fh.kanban.ui.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CardViewBoard extends JPanel implements View {
	private JTextField idTextField;
	private JTextField workloadTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox valueComboBox;
	private JTextPane descriptionTextPane;
	private JRadioButton rdbtnCreated;
	private JRadioButton rdbtnStarted;
	private JRadioButton rdbtnDone;
	private Color background;
	private JToggleButton blockerToggleButton;

	/**
	 * Create the panel.
	 */
	public CardViewBoard() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Karte", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		FormLayout formLayout = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("45dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("45dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),});
		setLayout(formLayout);
		JLabel idLabel = DefaultComponentFactory.getInstance().createLabel("ID:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(idLabel, "2, 2, right, fill");
		
		this.idTextField = new JTextField();
		add(this.idTextField, "4, 2, fill, fill");
		this.idTextField.setColumns(1);
		
		JLabel aufwandLabel = DefaultComponentFactory.getInstance().createLabel("Aufwand:");
		aufwandLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(aufwandLabel, "2, 4, right, fill");
		
		this.workloadTextField = new JTextField();
		add(this.workloadTextField, "4, 4, fill, fill");
		this.workloadTextField.setColumns(1);
		
		this.blockerToggleButton = new JToggleButton("Blocker");
		blockerToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		blockerToggleButton.setForeground(Color.RED);
		add(blockerToggleButton, "6, 4, 1, 3");
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(wertLabel, "2, 6, right, fill");
		
		this.valueComboBox = new JComboBox();
		this.valueComboBox.setFont(new Font("Tahoma", Font.PLAIN, 8));
		this.valueComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus:", "1: Blau", "2: Orange", "3: Rot", "4: Grün"}));
		add(this.valueComboBox, "4, 6, fill, default");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(beschreibungLabel, "2, 8, right, top");
		
		this.descriptionTextPane = new JTextPane();
		add(this.descriptionTextPane, "4, 8, 3, 2, fill, fill");
		
		this.rdbtnCreated = new JRadioButton("Created");
		this.buttonGroup.add(rdbtnCreated);
		this.rdbtnCreated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(this.rdbtnCreated, "2, 10, center, fill");
		
		this.rdbtnStarted = new JRadioButton("Started");
		this.buttonGroup.add(this.rdbtnStarted);
		this.rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(this.rdbtnStarted, "4, 10, center, fill");
		
		this.rdbtnDone = new JRadioButton("Done");
		this.buttonGroup.add(rdbtnDone);
		this.rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(this.rdbtnDone, "6, 10, center, fill");

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
		this.setBackground(background);
		this.rdbtnCreated.setBackground(background);
		this.rdbtnStarted.setBackground(background);
		this.rdbtnDone.setBackground(background);	
	}
	
	/**
	 * Setzt das IdTextFeld auf einen bestimmten Wert.
	 * @param n
	 */
	public void setIdTextField(String n) {
		this.idTextField.setText(n);	
	}
	
	/**
	 * Setzt das WorkloadTextFeld auf einen bestimmten Wert mit Hilfe des übergebenen Strings.
	 * @param n
	 */
	public void setWorkloadTextField(String n) {
		this.workloadTextField.setText(n);
	}
	
	/**
	 * Setzt die ComboBox auf den übergebenen Wert. Und passt die Hintergrundfarbe entsprechend der Auswahl ein
	 * @param i
	 */
	public void setValueComboBox(int i) {
		this.valueComboBox.setSelectedIndex(i);
		this.setJPanelColor();
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
	 * Setzt mit Hilfe der übergebenen Calender Daten die Auswahl auf den passenden JRadioButton.
	 * @param c
	 * @param s
	 * @param d
	 */
	public void setJRadioButton(Color b, Calendar c, Calendar s, Calendar d) {
		if(c != null && s == null && d == null) {
			this.rdbtnCreated.isSelected();
		}
		if(c != null && s != null && d == null) {
			this.rdbtnStarted.isSelected();
			
		}
		if(c != null && s != null && d != null) {
			this.rdbtnDone.isSelected();
		}
	}
	
	/**
	 * Rückgabe der JComponenete.
	 */
	public JComponent getComponent() {
		return this;
	}

}