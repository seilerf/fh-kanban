package edu.fh.kanban.ui.view;

import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

public abstract class AbstractView extends JPanel {
	public abstract void modelPropertyChange(PropertyChangeEvent event);
}
