package edu.fh.kanban.dao;

import edu.fh.kanban.domain.Card;

public interface CardDAO {
	public Card findCard();
	public int insertCard();
	public boolean deleteCard();
	public boolean updateCard();
}
