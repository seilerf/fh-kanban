package edu.fh.kanban.domain;


public class Preference extends AbstractModel {

	private String name;
	private static int[] colorStandard;
	private static int[] colorExpedite;
	private static int[] colorFixed;
	private static int[] colorIntagible;
	
	private static String limitNext;
	private static String limitDev;
	private static String limitTest;
	private static String limitDone;

	public Preference(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int[] getColorStandard() {
		return colorStandard;
	}

	public static void setColorStandard(int[] color1) {
		colorStandard = color1;
	}

	public static int[] getColorExpedite() {
		return colorExpedite;
	}

	public static void setColorExpedite(int[] farbe2) {
		colorExpedite = farbe2;
	}

	public static int[] getColorFixed() {
		return colorFixed;
	}

	public static void setColorFixed(int[] farbe3) {
		colorFixed = farbe3;
	}

	public static int[] getColorIntagible() {
		return colorIntagible;
	}

	public static void setColorIntagible(int[] farbe5) {
		colorIntagible = farbe5;
	}
	
	public String getLimitNext() {
		return limitNext;
	}

	public void setLimitNext(String limitNext) {
		this.limitNext = limitNext;
	}

	public String getLimitDev() {
		return limitDev;
	}

	public void setLimitDev(String limitDev) {
		this.limitDev = limitDev;
	}

	public String getLimitTest() {
		return limitTest;
	}

	public void setLimitTest(String limitTest) {
		this.limitTest = limitTest;
	}

	public String getLimitDone() {
		return limitDone;
	}

	public void setLimitDone(String limitDone) {
		this.limitDone = limitDone;
	}
	
	
}
