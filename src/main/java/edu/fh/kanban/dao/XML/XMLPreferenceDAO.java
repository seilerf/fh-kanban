package edu.fh.kanban.dao.XML;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import edu.fh.kanban.dao.PreferenceDAO;
import edu.fh.kanban.domain.Preference;

public class XMLPreferenceDAO implements PreferenceDAO {
	
	private Preference preference;
	private File prefFile = new File("Preferences.xml");
	
	@Override
	public Preference findPreference() {
		try {
			preference = readPreferenceFromXML();
		} catch (JDOMException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return preference;
	}

	@Override
	public int insertPreference() {
		
		return 0;
	}

	@Override
	public boolean deletePreference() {
	
		return false;
	}

	@Override
	public boolean updatePreference() {
	
		return false;
	}
	
	public Preference readPreferenceFromXML() throws JDOMException, IOException{
		preference = new Preference();
		Document xmlDocForPrefs = null;
		SAXBuilder builder2 = new SAXBuilder();
		xmlDocForPrefs = builder2.build(prefFile);
		Element pRootElement = xmlDocForPrefs.getRootElement();
		System.out.println("-------------------------------------------------------------");
		System.out.println("Einstellungsname: " + pRootElement.getAttributeValue("name"));
		preference.setName(pRootElement.getAttributeValue("name"));
		readAll(pRootElement);
		return preference; 
	}
	
	public void readAll(Element root){
		List<Element> prefs = (List<Element>) root.getChildren();
		

		//System.out.println(Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(0).getAttributeValue("f1")));
		//System.out.println(Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(1).getAttributeValue("f2")));
		System.out.println("Das war ich!");
	
		
		int[] farbe1 = new int[3];
		farbe1[0] = Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(0).getAttributeValue("f1"));
		farbe1[1] =	Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(1).getAttributeValue("f2"));
		farbe1[2] =	Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(2).getAttributeValue("f3"));
				
		
		System.out.println("Das war ich!");

		int[] farbe2 = new int[3];
		farbe2[0] = Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(0).getAttributeValue("f1"));
		farbe2[1] =	Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(1).getAttributeValue("f2"));
		farbe2[2] =	Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(2).getAttributeValue("f3"));
		
		int[] farbe3 = new int[3];
		farbe3[0] = Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(0).getAttributeValue("f1"));
		farbe3[1] =	Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(1).getAttributeValue("f2"));
		farbe3[2] =	Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(2).getAttributeValue("f3"));
		
		int[] farbe4 = new int[3];
		farbe4[0] = Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(0).getAttributeValue("f1"));
		farbe4[1] =	Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(1).getAttributeValue("f2"));
		farbe4[2] =	Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(2).getAttributeValue("f3"));
		
		
		
		System.out.println(farbe1[0] + "" +  farbe1[1] + "" + farbe1[2]);
		System.out.println(farbe2[0] + "" +  farbe2[1] + ""+ farbe2[2]);
		System.out.println(farbe3[0] + "" +  farbe3[1] + ""+ farbe3[2]);
		
		
		
		
		
		
		preference.setColorStandard(farbe1);
		preference.setColorExpedite(farbe2);
		preference.setColorFixed(farbe3);
		//preference.setColorIntagible(color4);
		
		String column1 = prefs.get(1).getChildren().get(0).getAttributeValue("limit");
		String column2 = prefs.get(1).getChildren().get(1).getAttributeValue("limit");
		String column3 = prefs.get(1).getChildren().get(2).getAttributeValue("limit");
		String column4 = prefs.get(1).getChildren().get(3).getAttributeValue("limit");
		
		preference.setLimitNext(column1);
		preference.setLimitDev(column2);
		preference.setLimitTest(column3);
		preference.setLimitDone(column4);
		
		System.out.println(column1 + column2 + column3 + column4);

	
		
		System.out.println("-------------------------------------------------------------\n");
		
	}

	
	

}
