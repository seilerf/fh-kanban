package edu.fh.kanban.dao.PDF;

import java.io.File;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil {
	protected static File file = new File("board.pdf");
	public static Document document;
	public static PdfWriter writer;
	public static String boardName;
	public static PdfContentByte cb;
	public static BaseFont bf;


	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		PDFUtil.file = file;
	}
	
	public static String getBoardName() {
		return boardName;
	}

	public static void setBoardName(String boardName) {
		PDFUtil.boardName = boardName;
	}
}
