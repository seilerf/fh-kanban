package edu.fh.kanban.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import edu.fh.kanban.domain.Card;
import edu.fh.kanban.ui.view.CardView;

public class CardController {
	private Card card;
	private CardView cardView;
	
	/**
	 * Konstruktor der CardController-Klasse.
	 */
	public CardController() {
		this.card = new Card(0, 0, 0, null, false, 0, null, null);
		this.cardView = new CardView();
		
		addListener();
	}
	
	/**
	 * Methode zum Hinzufügen der benötigten Listener. 
	 */
	private void addListener() {
		this.cardView.setBtnIdSpeichern(new CardIdSpeichernListener());
		this.cardView.setBtnIdLoeschen(new CardIdLoeschenListener());
		this.cardView.setBtnAufwandSpeichern(new AufwandSpeichernListener());
		this.cardView.setBtnAufwandLoeschen(new AufwandLoeschenListener());
		this.cardView.setBtnBeschreibungSpeichern(new BeschreibungSpeichernListener());
		this.cardView.setBtnBeschreibungLoeschen(new BeschreibungLoeschenListener());
		this.cardView.setBtnWertSpeichern(new WertSpeichernListener());
		this.cardView.setBtnWertLoeschen(new WertLoeschenListener());
		this.cardView.setBlockerToggleButton(new BlockerSpeichernListener());
		this.cardView.setBlockerToggleButton(new BlockerLoeschenListener());
		this.cardView.setCreatedJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setStartedJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setDoneJRadioButton(new JRadioButtonSelectedListener());
		this.cardView.setBtnJRadioSelectedLoeschen(new JRadioButtonDeleteListener());
	}
	
	/**
	 * Transferieren des Id-Attribut-Wertes aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class CardIdSpeichernListener implements ActionListener {
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
	class CardIdLoeschenListener implements ActionListener {
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
	class AufwandSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			int wert = Integer.valueOf(cardView.getAufwand());
			card.setValue(wert);
		}
	}
	
	/**
	 * Löschen des Aufwand-Attribut-Wertes in der View und dem Model.
	 * @author AdminMax
	 *
	 */
	class AufwandLoeschenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetAufwand();
			card.aufwandReset();
		}
		
	}
	
	/**
	 * Transferiert die Beschreibung aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class BeschreibungSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setDescription(cardView.getBeschreibung());
		}	
	}
	
	/**
	 * Löschen der Beschreibung in der View und dem Model.
	 * @author AdminMax
	 *
	 */
	class BeschreibungLoeschenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetBeschreibung();
			card.beschreibungReset();
		}	
	}
	
	
	/**
	 * Transferiert den ausgewählten Wert des Wert-Attributs in das Model.
	 * @author AdminMax
	 *
	 */
	class WertSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setValue(cardView.getWert());
			cardView.setJPanelColor();
			card.setBackground(cardView.getBackground());
		}
	}
	
	/**
	 * Löschen des Wert-Attributs im Model und Zurückseten in der View auf den Ursprungswert.
	 * @author AdminMax
	 *
	 */
	class WertLoeschenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cardView.resetWert();
			cardView.setJPanelColor();
			card.wertReset();
		}
	}
	
	/**
	 * Transferiert den BlockerToggleBtn-Wert aus der View in das Model.
	 * @author AdminMax
	 *
	 */
	class BlockerSpeichernListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			card.setBlocker(cardView.getBlocker());
		}
	}
	
	/**
	 * Zurücksetzen des Blocker-Attributs in der View und im Model.
	 * @author AdminMax
	 *
	 */
	class BlockerLoeschenListener implements ActionListener {
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
