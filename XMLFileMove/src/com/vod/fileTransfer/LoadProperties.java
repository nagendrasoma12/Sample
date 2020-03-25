package com.vod.fileTransfer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties
{
	public static String getPropertyValue(String propertyKey)
	{
		Properties prop=null;
		try
		{
			InputStream input = new FileInputStream(Constants.PROPERTY_FILE_LOCATION);
			prop= new Properties();
			prop.load(input);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return prop.getProperty(propertyKey);
	}
}