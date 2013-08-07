package edu.fh.kanban.dao;

import java.io.File;
import java.text.ParseException;

import edu.fh.kanban.domain.Board;

public interface BoardDAO {
	public Board findBoard() throws ParseException, InterruptedException, NullPointerException;
	public int insertBoard(Board board);
	public boolean deleteBoard();
	public boolean updateBoard();
}
