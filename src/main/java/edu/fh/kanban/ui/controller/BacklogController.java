package edu.fh.kanban.ui.controller;

import edu.fh.kanban.domain.AbstractModel;

public class BacklogController extends AbstractController {

	public static final String HEADLINE_PROPERTY   = "Headline";
	public static void sortByHeadline() {
		AbstractModel.callModelMethod(HEADLINE_PROPERTY);
	}

	
	
}
