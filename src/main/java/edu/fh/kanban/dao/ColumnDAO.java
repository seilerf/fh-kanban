package edu.fh.kanban.dao;

import edu.fh.kanban.domain.Column;

public interface ColumnDAO {
	public Column findColumn();
	public int insertColumn();
	public boolean deleteColumn();
	public boolean updateColumn();
}
