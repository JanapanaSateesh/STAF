package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Readconfig {

	Properties prop;
	public Readconfig() {
		File src = new File("./Configurations/config.properties"); 
		prop = new Properties();
		
		try {
			FileInputStream fi = new FileInputStream(src);
			prop.load(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUrl() {
		String url = prop.getProperty("url");
		return url;
	}

	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}
	
	public String getAppname() {
		String appname = prop.getProperty("app");
		return appname;
	}
}
