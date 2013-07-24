package edu.fh.kanban.domain;

import java.awt.Color;

public class Preference extends AbstractModel {

	private String name;
	private int[] colorStandard;
	private int[] colorExpedite;
	private int[] colorFixed;
	private String colorIntagible;
	
	private String limitNext;
	private String limitDev;
	private String limitTest;
	private String limitDone;

	public Preference(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getColorStandard() {
		return colorStandard;
	}

	public void setColorStandard(int[] color1) {
		this.colorStandard = color1;
	}

	public int[] getColorExpedite() {
		return colorExpedite;
	}

	public void setColorExpedite(int[] farbe2) {
		this.colorExpedite = farbe2;
	}

	public int[] getColorFixed() {
		return colorFixed;
	}

	public void setColorFixed(int[] farbe3) {
		this.colorFixed = farbe3;
	}

	public String getColorIntagible() {
		return colorIntagible;
	}

	public void setColorIntagible(String colorIntagible) {
		this.colorIntagible = colorIntagible;
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
