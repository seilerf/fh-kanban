package edu.fh.kanban.dao;

import java.io.File;
import java.text.ParseException;

import edu.fh.kanban.domain.Board;

public interface BoardDAO {
	public Board findBoard(File file) throws ParseException, InterruptedException, NullPointerException;
	public int insertBoard();
	public boolean deleteBoard();
	public boolean updateBoard();
}
