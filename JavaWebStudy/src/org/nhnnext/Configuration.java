package org.nhnnext;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public abstract class Configuration {
	protected Properties props = null;
	public abstract void init(); 

	public void loadFile(String fileName){
//		URL url = ClassLoader.getSystemResource(fileName);
//		System.out.println(url.toString());
		File configFile = new File(fileName);
		try {
			props = new Properties();
			FileInputStream fis = new FileInputStream(configFile);
			props.load(new BufferedInputStream(fis));
			fis.close();
		} catch (Exception e) {
			System.err.println("load properties file error : " + e.getMessage());
		}
		
	}

	public Properties getProperty() {
		return props;
	}

	public String getValue(String key) {
		String value = null;
		try {
			value = props.getProperty(key, "Not in property file.");
		} catch (Exception e) {
			System.out.println("getPropertyValue error : " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}
}
