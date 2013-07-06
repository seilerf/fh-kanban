package edu.fh.kanban.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.view.CardView;
/**
 *  Der CardController ke
 * 
 *
 */
public class CardController extends AbstractController {
	private Card card;
	private CardView cardView;
	
	public static final String CARDID_PROPERTY   = "CardId";

	
	/**
	 * Methode zum Hinzufügen der benötigten Listener. 
	 */
	private void addListener() {
		this.cardView.setBtnIdSave(new CardIdSaveListener());
		this.cardView.setBtnIdDelete(new CardIdDeleteListener());
		this.cardView.setBtnWorkloadSave(new WorkloadSaveListener());
		this.cardView.setBtnWorkloadDelete(new WorkloadDeleteListener());
		this.cardView.setBtnDescriptionSave(new DescriptionSaveListener());
		this.cardView.setBtnDescriptionDelete(new DescriptionDeleteListener());
		this.cardView.setBtnValueSave(new ValueSaveListener());
		this.cardView.setBtnValueDelete(new ValueDeleteListener());
		this.cardView.setBlockerToggleButton(new BlockerSaveListener());
		this.cardView.setBlockerToggleButton(new BlockerDeleteListener());
		this.cardView.setCreatedJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setStartedJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setDoneJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setBtnJRadioSelectedDelete(new JRadioButtonDeleteListener());
	}
	
	public void changeCardId(int newCardId){
		setModelProperty(CARDID_PROPERTY,newCardId);
	}
	
	/**
	 * Transferieren des Id-Attribut-Wertes aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class CardIdSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int wert = Integer.valueOf(cardView.getId());
			card.setId(wert);	
		}	
	}
	
	/**
	 * Löschen des Id-Attribut-Wertes in der View und dem Model.
	 * @author AdminMax
	 *
	 */
	class CardIdDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetId();
			card.cardIdReset();
		}
	}
	
	/**
	 * Transferieren des Aufwand-Attribut-Wertes aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class WorkloadSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			int wert = Integer.valueOf(cardView.getWorkload());
			card.setValue(wert);
		}
	}
	
	/**
	 * Löschen des Aufwand-Attribut-Wertes in der View und dem Model.
	 * @author AdminMax
	 *
	 */
	class WorkloadDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetWorkload();
			card.aufwandReset();
		}
		
	}
	
	/**
	 * Transferiert die Beschreibung aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class DescriptionSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setDescription(cardView.getDescription());
		}	
	}
	
	/**
	 * Löschen der Beschreibung in der View und dem Model.
	 * @author AdminMax
	 *
	 */
	class DescriptionDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetDescription();
			card.beschreibungReset();
		}	
	}
	
	
	/**
	 * Transferiert den ausgewählten Wert des Wert-Attributs in das Model.
	 * @author AdminMax
	 *
	 */
	class ValueSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setValue(cardView.getValue());
			cardView.setJPanelColor();
			card.setBackground(cardView.getBackground());
		}
	}
	
	/**
	 * Löschen des Wert-Attributs im Model und Zurückseten in der View auf den Ursprungswert.
	 * @author AdminMax
	 *
	 */
	class ValueDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetValue();
			cardView.setJPanelColor();
			card.wertReset();
		}
	}
	
	/**
	 * Transferiert den BlockerToggleBtn-Wert aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class BlockerSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setBlocker(cardView.getBlocker());
		}
	}
	
	/**
	 * Zurücksetzen des Blocker-Attributs in der View und im Model.
	 * @author AdminMax
	 *
	 */
	class BlockerDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetBlocker();
			card.blockerReset();
		}
	}
	
	/**
	 * Transferiert auf Grund der Auswahl in der View den passenden Zeitstempel in das Model.
	 * @author AdminMax
	 *
	 */
	class JRadioButtonSelectedListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Calendar zeitpunkt = null;
			if(cardView.getJRadioButton()==0) {
				card.setStarted(zeitpunkt);
			}
			if(cardView.getJRadioButton()==-1) {
				card.setCreated(zeitpunkt);
			}
			if(cardView.getJRadioButton()==1) {
				card.setDone(zeitpunkt);
			}
		}
	}

	
	/**
	 * Zurücksetzen der Created- /Startes- /Done-Werte im Model und in der View.
	 * @author AdminMax
	 *
	 */
	class JRadioButtonDeleteListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetJRadioButton();
			card.createdReset();
			card.startedReset();
			card.doneReset();
		}
	}
	
	
}
