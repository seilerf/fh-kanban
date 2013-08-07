package edu.fh.kanban.dao;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Element;

import edu.fh.kanban.domain.Column;

public interface ColumnDAO {
	public Column findColumn(List<Element> columns, int i);
	public int insertColumn(Column column);
	
	public boolean deleteColumn();
	public boolean updateColumn();
}
