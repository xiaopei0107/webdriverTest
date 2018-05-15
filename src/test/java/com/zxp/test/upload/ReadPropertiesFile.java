package com.zxp.test.upload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zxp.test.util.WebDriverUtil;

public class ReadPropertiesFile {

	WebDriver driver; 
	
	@Test
	public void testReadProperties(){
		Reporter.log("读取属性文件开始..");
		Properties p = new Properties();
		try {
			InputStream is = new FileInputStream(".\\TestConfig\\mail.properties");
			p.load(is);
			
			String username = p.getProperty("mail.username");
			String password = p.getProperty("mail.password");
			System.out.println(username +"  "+password);
			
			is.close();
			Reporter.log("读取属性文件结束..");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMyWait(){
		driver.get("https://www.icloud.com/");  
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		driver.switchTo().frame("auth-frame");
		WebElement nameEl = driver.findElement(By.xpath("//*[@id='account_name_text_field']"));
		nameEl.clear();
		nameEl.sendKeys("test...");
		
		driver.findElement(By.xpath("//*[@id='remember-me']")).click();
	}
	
	 @BeforeClass
	  public void beforeClass() {
		 driver = WebDriverUtil.getChromeWebDriver(); 
	  }
	  
	
	 @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
}
