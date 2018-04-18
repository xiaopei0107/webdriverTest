package com.zxp.test;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
	WebDriver driver;

	@Test
	public void loginTest() {
		try {
			//driver.get("http://uat.bsteel.com/newexchange/login/doDefault.do");
//			RestTemplate template = new RestTemplate();
//			String url="http://uat.bsteel.com/newexchange/login/doDefault.do";
//			String param="?userLoginNo=xinshiye&userLoginPsd=admin1234";

			driver.get("https://10.60.16.9/index.php/dashboard");
	        Cookie bdussCookie = new Cookie("DBAPPUSM","5f37a79dfc5b4c1fa1741a8c4086d492a522d3113be56a23bbcc584e637bfead");
	        driver.manage().addCookie(bdussCookie);
	        driver.get("https://10.60.16.9/index.php/dashboard");
	        
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D://test//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
