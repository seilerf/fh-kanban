package edu.fh.kanban.dao.XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import edu.fh.kanban.dao.DAOFactory;

public class XMLDAOProperties {
	
	protected static DAOFactory xmlFactory = DAOFactory.getDAOFactory(DAOFactory.XML);
	
	protected static File file = new File("board1.xml");
	protected static DocumentBuilderFactory docFactory;
	protected static DocumentBuilder docBuilder;
	protected static org.w3c.dom.Document xmlDoc;

	//statischer Konstruktor
	static {
			try {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				docBuilder = docFactory.newDocumentBuilder();
				xmlDoc = docBuilder.newDocument();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
	}
	
	public static DAOFactory getXmlFactory() {
		return xmlFactory;
	}

	public static void setXmlFactory(DAOFactory xmlFactory) {
		XMLDAOProperties.xmlFactory = xmlFactory;
	}

	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		XMLDAOProperties.file = file;
	}


}
