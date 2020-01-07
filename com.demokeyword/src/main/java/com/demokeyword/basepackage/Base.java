package com.demokeyword.basepackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	
	
	public WebDriver init_Driver(String browserName)
	{
		  if(browserName.equalsIgnoreCase(prop.getProperty("browserName"))) {
		 
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/DELL/chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(prop.getProperty("siteUrl"));
			driver.manage().window().maximize();
		
		  } else if(browserName.equalsIgnoreCase("firefox")) {
		  //driver.get("https://www.gm.com/");
		  
		  }
		 
		
		return driver;
		
	}
	
	
	public Properties intialize_Properties()
	{
		
		prop=new Properties();
		
		try {
			FileInputStream fis=new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\com.demokeyword\\src\\main\\java\\com\\demokeyword\\config\\config.properties");
			try {
				prop.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	

}
