package edu.fh.kanban.dao.CSV;

import java.io.BufferedWriter;

public class CSVUtil {
	public static String boardName = "Testboard";
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
