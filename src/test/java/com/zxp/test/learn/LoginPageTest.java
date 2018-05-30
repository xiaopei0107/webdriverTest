package com.zxp.test.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zxp.test.page.LoginPage;
import com.zxp.test.util.WebDriverUtil;

public class LoginPageTest {

	private WebDriver driver;
	String baseUrl;

	@BeforeMethod
	public void beforeMethod() {
		driver = WebDriverUtil.getChromeWebDriver();
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	@Test
	public void testSohuSendMail()  {
		try {
			LoginPage login = new LoginPage(driver);
			login.login();
			Thread.sleep(2000);
			
			Assert.assertTrue(driver.getPageSource().contains("写邮件"));
			Reporter.log("2.登录成功进入主页面");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
