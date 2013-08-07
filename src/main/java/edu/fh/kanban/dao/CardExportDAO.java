package edu.fh.kanban.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.DocumentException;

public interface CardExportDAO {

	public void export() throws FileNotFoundException, DocumentException, IOException;
}
