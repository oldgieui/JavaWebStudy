package org.nhnnext.framework;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigParser extends DefaultHandler {

	String controllerInfo[] = new String[2];
	String dbInfo[] = new String[3];

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("start");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Controller".equals(qName)) {
			controllerInfo[0] = attributes.getValue(0);
			controllerInfo[1] = attributes.getValue(1);
		}
		if ("Database".equals(qName)) {
			dbInfo[0] = attributes.getValue(0);
			dbInfo[1] = attributes.getValue(1);
			dbInfo[2] = attributes.getValue(2);
		}
	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			if ("Controller".equals(qName)) {
				System.out.println(controllerInfo[0] + " : " + controllerInfo[1]);
				Controller ctr = (Controller) Class.forName(controllerInfo[1]).newInstance();
				ControllerMap.addController(controllerInfo[0], ctr);
			}
			if ("Database".equals(qName)) {
				System.out.println(dbInfo[0] + " " + dbInfo[1] + " " + dbInfo[2]);
				ConnectionManager.initDB(dbInfo[0], dbInfo[1], dbInfo[2]);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void endDocument() throws SAXException {
	}

}
