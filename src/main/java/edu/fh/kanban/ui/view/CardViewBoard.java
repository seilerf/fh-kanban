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
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CardViewBoard extends JPanel implements View {
	private JTextField idTextField;
	private JTextField aufwandTextField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

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
		
		idTextField = new JTextField();
		add(idTextField, "4, 2, fill, fill");
		idTextField.setColumns(1);
		
		JLabel aufwandLabel = DefaultComponentFactory.getInstance().createLabel("Aufwand:");
		aufwandLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(aufwandLabel, "2, 4, right, fill");
		
		aufwandTextField = new JTextField();
		add(aufwandTextField, "4, 4, fill, fill");
		aufwandTextField.setColumns(1);
		
		JToggleButton blockerToggleButton = new JToggleButton("Blocker");
		blockerToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		blockerToggleButton.setForeground(Color.RED);
		add(blockerToggleButton, "6, 4, 1, 3");
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(wertLabel, "2, 6, right, fill");
		
		JComboBox wertComboBox = new JComboBox();
		wertComboBox.setFont(new Font("Tahoma", Font.PLAIN, 8));
		wertComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus:", "1: Blau", "2: Orange", "3: Rot", "4: Grün"}));
		add(wertComboBox, "4, 6, fill, default");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(beschreibungLabel, "2, 8, right, top");
		
		JTextPane beschreibungTextPane = new JTextPane();
		add(beschreibungTextPane, "4, 8, 3, 2, fill, fill");
		
		JRadioButton rdbtnCreated = new JRadioButton("Created");
		buttonGroup.add(rdbtnCreated);
		rdbtnCreated.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(rdbtnCreated, "2, 10, center, fill");
		
		JRadioButton rdbtnStarted = new JRadioButton("Started");
		buttonGroup.add(rdbtnStarted);
		rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(rdbtnStarted, "4, 10, center, fill");
		
		JRadioButton rdbtnDone = new JRadioButton("Done");
		buttonGroup.add(rdbtnDone);
		rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(rdbtnDone, "6, 10, center, fill");

	}


	public JComponent getComponent() {
		return this;
	}

}
