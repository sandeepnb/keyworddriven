package com.demokeyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.demokeyword.basepackage.Base;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	
	public WebElement element;
	
	public Base base;
	
	
	public final String SCENARIO_SHEET_PATH_EXCEL="C:\\Users\\DELL\\eclipse-workspace\\com.demokeyword\\src\\main\\java\\com\\demokeyword\\scenarios\\Book1.xlsx";
	
	public void startExecution(String sheetName)
	{
		/*
		 * String locatorName=null; String locatorValue=null;
		 */
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(SCENARIO_SHEET_PATH_EXCEL);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
		
		sheet=book.getSheet(sheetName);
		
		
		int k=0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			
			try {
				
			
			String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
			String locatorValue=sheet.getRow(i+1).getCell(k+2).toString().trim();
				/*
				 * if(!locatorColValue.equalsIgnoreCase("NA")) {
				 * locatorName=locatorColValue.split(",")[0].trim();//id
				 * locatorValue=locatorColValue.split(",")[1].trim();//username
				 * 
				 * }
				 */
		
			String action=sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value=sheet.getRow(i+1).getCell(k+4).toString().trim();
		
		switch(action)
		{
		case "open browser":
		base=new Base();
		prop=base.intialize_Properties();
		
		if(value.isEmpty() || value.equals("NA"))
		{
			driver=base.init_Driver(prop.getProperty("browserName"));
			
		}
		else {
			driver=base.init_Driver(value);
			
		}
		
		break;
		
		case "enter url":
			if(value.isEmpty() || value.equals("NA"))
			{
				driver=base.init_Driver(prop.getProperty("siteUrl"));
				
			}
			else {
				driver.get(value);
			}
			
			break;
		
		case "quit":
			Thread.sleep(3000);
			driver.quit();
			break;
			
		default:
			break;
		}
		
		switch(locatorType)
		{
		
		
		case "xpath":
			element=driver.findElement(By.xpath(locatorValue));
			
			if(action.equalsIgnoreCase("sendkeys"))
			{
				element.clear();
				element.sendKeys(value);
				
			}
			else if(action.equalsIgnoreCase("click"))
			{
				Thread.sleep(2000);
				element.click(); 
			}
			locatorType=null;
			 break;
			 
		case "id":
		element=driver.findElement(By.id(locatorValue));
		
		if(action.equalsIgnoreCase("sendkeys"))
		{
			element.clear();
			element.sendKeys(value);
			
		}
		else if(action.equalsIgnoreCase("click"))
		{
			element.click(); 
		}
		locatorType=null;
		break;
		
		case "linkText":
			 element=	driver.findElement(By.linkText((locatorValue)));
			 element.click();
			 locatorType=null;
			 break;
			 
		default:
			break;
		}
	
		} catch (Exception e) {
			// TODO: handle exception
		}
  }
}
}
