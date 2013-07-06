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

public class CardViewBoard extends JPanel implements View {
	private JTextField idTextField;
	private JTextField aufwandTextField;

	/**
	 * Create the panel.
	 */
	public CardViewBoard() {
		setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Karte", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("15dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("55dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
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
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(14dlu;default)"),}));
		
		JLabel idLabel = DefaultComponentFactory.getInstance().createLabel("ID:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(idLabel, "2, 2, right, fill");
		
		idTextField = new JTextField();
		add(idTextField, "4, 2, fill, fill");
		idTextField.setColumns(1);
		
		JLabel aufwandLabel = DefaultComponentFactory.getInstance().createLabel("Aufwand:");
		aufwandLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(aufwandLabel, "8, 2, right, fill");
		
		aufwandTextField = new JTextField();
		add(aufwandTextField, "10, 2, fill, fill");
		aufwandTextField.setColumns(1);
		
		JLabel wertLabel = DefaultComponentFactory.getInstance().createLabel("Wert:");
		wertLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(wertLabel, "8, 6, right, fill");
		
		JComboBox wertComboBox = new JComboBox();
		wertComboBox.setModel(new DefaultComboBoxModel(new String[] {"Wähle aus:", "1: Blau", "2: Orange", "3: Rot", "4: Grün"}));
		add(wertComboBox, "10, 6, fill, default");
		
		JToggleButton blockerToggleButton = new JToggleButton("Blocker");
		blockerToggleButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blockerToggleButton.setForeground(Color.RED);
		add(blockerToggleButton, "3, 8, 3, 5");
		
		JLabel beschreibungLabel = DefaultComponentFactory.getInstance().createLabel("Beschreibung:");
		beschreibungLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(beschreibungLabel, "8, 10, right, top");
		
		JTextPane beschreibungTextPane = new JTextPane();
		add(beschreibungTextPane, "10, 10, 3, 5, fill, fill");
		
		JRadioButton rdbtnCreated = new JRadioButton("Created");
		rdbtnCreated.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(rdbtnCreated, "8, 18, right, fill");
		
		JRadioButton rdbtnStarted = new JRadioButton("Started");
		rdbtnStarted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(rdbtnStarted, "10, 18, right, fill");
		
		JRadioButton rdbtnDone = new JRadioButton("Done");
		rdbtnDone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(rdbtnDone, "12, 18, right, fill");

	}


	public JComponent getComponent() {
		return this;
	}

}
