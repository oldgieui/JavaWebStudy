package org.nhnnext.framework;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CmapParser extends DefaultHandler {

	String key;
	String value;

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
		// xml에서 읽어온 정보를 사용해서 맞는 인스턴스를 생성해야 하는데 방법을 모르겠다. Class.forName()정도면 될 거라 막연히 생각했으나....
		// (추가) -> newInstance()라는 메소드가 있는것이었다 으아 아아 아아아
		try {
			if ("Controller".equals(qName)) {
				System.out.println(key + " : " + value);
				Controller ctr = (Controller) Class.forName(value).newInstance();
				ControllerMap.addController(key, ctr);
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
