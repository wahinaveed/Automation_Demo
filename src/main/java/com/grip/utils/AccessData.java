package com.grip.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.log4testng.Logger;

public enum AccessData {
	CONFIG("config.properties"), 
	TESTDATA("testdata.properties");

	public static FileInputStream fis = null;
	public static Properties prop = null;
	private static final Logger LOGGER = Logger.getLogger(AccessData.class);
	public String resourceFile;

	private AccessData(String resource) {
		this.resourceFile = resource;
	}

	public void loadPropertyFile(String Key) {
		try {
			String projectRoothPath = "/src/main/resources/PropertiesFile/";
			String propFilePath = new File(".").getCanonicalPath() + projectRoothPath + resourceFile;
			LOGGER.info("File Path is - " + propFilePath);
			fis = new FileInputStream(propFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public String get(String key) {
		loadPropertyFile(key);
		if (!prop.getProperty(key).isEmpty())
			return prop.getProperty(key);
		else
			throw new RuntimeException(key + " value is null in " + resourceFile + " file");
	}

	public int getInt(String key) {
		return Integer.parseInt(get(key));
	}

	public long getLong(String key) {
		return Long.parseLong(get(key));
	}

	public double getDouble(String key) {
		return Double.parseDouble(get(key));
	}

}
