package edu.fh.kanban.dao.CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CSVUtil {
	public static String boardName;
	public static BufferedWriter bw; 
	public static BufferedWriter getBw() {
		return bw;
	}

	public static void setBw(BufferedWriter bw) {
		CSVUtil.bw = bw;
	}

	public static String getBoardName() {
		return boardName;
	}

	public static void setBoardName(String boardName) {
		CSVUtil.boardName = boardName;
	}
}
