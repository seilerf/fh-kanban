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

		int[] intangible = new int[3];
		intangible[0] = Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(0).getAttributeValue("f1"));
		intangible[1] =	Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(1).getAttributeValue("f2"));
		intangible[2] =	Integer.parseInt(prefs.get(0).getChildren().get(0).getChildren().get(2).getAttributeValue("f3"));

		int[] standard = new int[3];
		standard[0] = Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(0).getAttributeValue("f1"));
		standard[1] =	Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(1).getAttributeValue("f2"));
		standard[2] =	Integer.parseInt(prefs.get(0).getChildren().get(1).getChildren().get(2).getAttributeValue("f3"));
		
		int[] expedite = new int[3];
		expedite[0] = Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(0).getAttributeValue("f1"));
		expedite[1] =	Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(1).getAttributeValue("f2"));
		expedite[2] =	Integer.parseInt(prefs.get(0).getChildren().get(2).getChildren().get(2).getAttributeValue("f3"));
		
		int[] fixedDate = new int[3];
		fixedDate[0] = Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(0).getAttributeValue("f1"));
		fixedDate[1] =	Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(1).getAttributeValue("f2"));
		fixedDate[2] =	Integer.parseInt(prefs.get(0).getChildren().get(3).getChildren().get(2).getAttributeValue("f3"));

		System.out.println(intangible[0] + "" +  intangible[1] + "" + intangible[2]);
		System.out.println(standard[0] + "" +  standard[1] + ""+ standard[2]);
		System.out.println(expedite[0] + "" +  expedite[1] + ""+ expedite[2]);
		System.out.println(fixedDate[0] + "" +  fixedDate[1] + ""+ fixedDate[2]);

		preference.setColorStandard(standard);
		preference.setColorExpedite(expedite);
		preference.setColorFixed(fixedDate);
		preference.setColorIntagible(intangible);
		
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
