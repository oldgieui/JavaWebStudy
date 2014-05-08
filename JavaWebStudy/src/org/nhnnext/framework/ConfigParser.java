package org.nhnnext.framework;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ConfigParser extends DefaultHandler {

	String key;
	String value;
	ArrayList<String> dbInfo = new ArrayList<String>();

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("start");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Controller".equals(qName)) {
			key = attributes.getValue(0);
			System.out.println(key);
		}
		if ("Database".equals(qName)) {
			dbInfo.add(attributes.getValue(0));
			dbInfo.add(attributes.getValue(1));
			dbInfo.add(attributes.getValue(2));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = new String(ch, start, length);
		System.out.println(value);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			if ("Controller".equals(qName)) {
				System.out.println(key + " : " + value);
				Controller ctr = (Controller) Class.forName(value).newInstance();
				ControllerMap.addController(key, ctr);
			}
			if ("Database".equals(qName)) {
				System.out.println(dbInfo.get(0) + " " + dbInfo.get(1) + " " + dbInfo.get(2));
				ConnectionManager.initDB(dbInfo.get(0), dbInfo.get(1), dbInfo.get(2));
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
