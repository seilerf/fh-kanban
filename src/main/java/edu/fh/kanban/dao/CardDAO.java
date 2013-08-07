package edu.fh.kanban.dao;

import java.text.ParseException;

import org.jdom2.Element;

import edu.fh.kanban.domain.Card;

public interface CardDAO {
	public Card findCard(Element column, int i) throws ParseException;
	public int insertCard(Card card);
	public boolean deleteCard();
	public boolean updateCard();
}
