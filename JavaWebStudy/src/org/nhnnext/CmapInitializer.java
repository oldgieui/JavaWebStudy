package org.nhnnext;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.nhnnext.framework.CmapParser;

public class CmapInitializer {
	
	//파일명을 지정하는 부분까지 분리하고 싶은데 그렇게 하면 파일명을 지정하는 메소드는 어디서 호출해야 하는가?
	//근데 생각해보면 환경설정을 web.xml에서 하도록 정해져 있는 것처럼 그냥 여기 쓰는 파일도 Cmap.xml로 고정시켜버려도 될 것 같기도...
	public static void mapInit() {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			
			File xmlFile = new File("/Users/oldgieui/git/JavaWebStudy/JavaWebStudy/src/org/nhnnext/Cmap.xml");
			parser.parse(xmlFile, new CmapParser());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
