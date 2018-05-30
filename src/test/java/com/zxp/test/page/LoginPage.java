package com.zxp.test.page;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.zxp.test.util.ContantsUtil;

/**
 * PageFactory是为了支持页面设计模式而开发出来的，它的方法在selenium.support库里面
 * 
 * @author belita
 *
 */
public class LoginPage {
	
	private WebDriver driver;
	
	@FindBy(xpath = "//*[@id='theme']/form/div[1]/div[1]/input")
	private WebElement loginName;

	@FindBy(xpath = "//*[@id='theme']/form/div[2]/div[1]/input")
	private WebElement password;

	@FindBy(xpath = "//*[@id='theme']/form/div[5]/input")
	private WebElement btnLogin;
	
	@FindBy(xpath = "//*[@id='theme']/form/h2")
	private WebElement showLogin;

	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public void login(){
		Reporter.log("打开登录搜狐邮箱网址");
		driver.get(ContantsUtil.SOHU_MAIL_URL);
		
		//访问搜狐登录页面 显式等待页面元素出来
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(showLogin));
		Assert.assertTrue(driver.getPageSource().contains("登录搜狐邮箱"));
		Reporter.log("显示登录搜狐邮箱页面");
		
		loginName.sendKeys(ContantsUtil.SOHU_MAIL_NAME);
		password.sendKeys(ContantsUtil.SOHU_MAIL_PWD);
		btnLogin.click();
		
		Reporter.log("开始登录");
		
	}
}
