package com.blenheimchalcot.modulr.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	public static Properties load(String filename,String location){		 
		
		Properties prop = null;
		FileInputStream stream = null;
		String driverPath =System.getProperty("user.dir");
		try {				
				stream = new FileInputStream(driverPath+location+filename);
				prop = new Properties();
                prop.load(stream);
            } 
			catch(FileNotFoundException fnfe) 
			{
				fnfe.printStackTrace();
			}
			catch (IOException e) 
			{
                e.printStackTrace();
            }
			finally {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            return prop;
		
		
       }
	
	public static String getProperty(String filename,String path,String key)
	{		
		Properties props = ReadProperties.load(filename,path);
		return props.getProperty(key) ;
		
	}
	

}