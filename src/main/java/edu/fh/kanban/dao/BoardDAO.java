package edu.fh.kanban.dao;

import edu.fh.kanban.domain.Board;

public interface BoardDAO {
	public Board findBoard();
	public int insertBoard();
	public boolean deleteBoard();
	public boolean updateBoard();
}
