package com.zxp.test.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.zxp.test.util.WebDriverUtil;

public class WebDriverWaitTest {
	private WebDriver driver;
	String baseUrl;

	@BeforeMethod
	public void beforeMethod() {
		baseUrl = "https://mail.sohu.com/fe/#/login";
		driver = WebDriverUtil.getChromeWebDriver();
		driver.get(baseUrl);
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
	/**
	 * 一、加sleep时间：
	 *   1.跳转页面后
	 *   2.click后
	 *   3.有动态元素显示与隐藏时
	 *   4.出现元素不可见  没找到时  最好在此之前加一个
	 *   5.wait.until(...)不好使的时候
	 * 二、 无痕模式时注意点
	 *   1.出现元素不可见  没找到时  最好在此之前加一个sleep time
	 *   2.实在没折用js来进行操作元素 
	 */
	@Test
	public void testSohuSendMail()  {
		Reporter.log("【start测试发送邮件】");
		int success = 0;
		try {
			//访问搜狐登录页面 显式等待页面元素出来
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='theme']/form/h2")));
			Assert.assertTrue(driver.getPageSource().contains("登录搜狐邮箱"));
			Reporter.log("1.显示登录搜狐邮箱");
			
			//填入用户名和密码后登录
			WebElement loginName = driver.findElement(By.xpath("//*[@id='theme']/form/div[1]/div[1]/input"));
			loginName.sendKeys("xiaopei_0107@sohu.com");
			
			WebElement pwd = driver.findElement(By.xpath("//*[@id='theme']/form/div[2]/div[1]/input"));
			pwd.sendKeys("peipei0107");
			
			WebElement btnLogin = driver.findElement(By.xpath("//*[@id='theme']/form/div[5]/input"));
			btnLogin.click();
			Thread.sleep(2000); //必要的等待
			
			//验证是否登录成功
			Assert.assertTrue(driver.getPageSource().contains("Hi，xiaopei_0107"));
			Reporter.log("2.登录成功进入主页面");
			
			//积分商城
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mailContent']/div/div[3]/div[4]/span[2]")));
			WebElement scoreShop = driver.findElement(By.xpath("//*[@id='mailContent']/div/div[3]/div[4]/span[2]"));
			scoreShop.click();
			Thread.sleep(1000);
			Assert.assertTrue(driver.getPageSource().contains("马上兑换"));
			Reporter.log("3.点击积分商城中 包含 【马上兑换】按钮");
			
			/**** 无痕模式无法获取此元素  ****/
//			//开始写邮件
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='写邮件']")));
//			WebElement writeMail = driver.findElement(By.xpath("//li[text()='写邮件']"));
//			writeMail.click();
			
			//用js方式来点击写邮件按钮
			JavascriptExecutor js  = (JavascriptExecutor)driver;
			WebElement writeEle = driver.findElement(By.xpath("//*[text()='写邮件']"));
			if(writeEle.isDisplayed() || writeEle.isEnabled()){
				Reporter.log("4.能看见写邮件按钮");
			}
			js.executeScript("arguments[0].click()", writeEle);
			Thread.sleep(2000); //必要的等待
			Assert.assertTrue(driver.getPageSource().contains("存草稿 "));
			Reporter.log("5.进入写信页面");
			
			//填写收件人
			WebElement recevie = driver.findElement(By.xpath("//*[@id='mailContent']/div/div[1]/div[1]/div[1]/div[1]/div/span/input"));
			recevie.sendKeys("724152220@qq.com");
			Reporter.log("6.添加收件人");
			//让收件人动态加载
			//Thread.sleep(1000);
			
			WebElement subject = driver.findElement(By.xpath("//input[@ng-model='mail.subject']"));
			subject.click();
			subject.sendKeys("自动化测试发送邮件");
			Reporter.log("7.填写邮件主题");
			Thread.sleep(2000); //网页加载慢  线程等2秒
			
			//切换到iframe页面
			driver.switchTo().frame("ueditor_0");
			WebElement bodyEle = driver.findElement(By.tagName("body"));
			wait.until(ExpectedConditions.elementToBeClickable(bodyEle));
			bodyEle.click();
			bodyEle.sendKeys("自动化测试发送邮件 ，我的selenium。。。。");
			Thread.sleep(1000);
			Reporter.log("8.进入iframe中填写邮件内容");
			
			//切回到主页面 点击发送
			driver.switchTo().defaultContent();			
			WebElement sendEmail = driver.findElement(By.xpath("//span[text()='发送']"));
			wait.until(ExpectedConditions.elementToBeClickable(sendEmail));
			sendEmail.click();
			Thread.sleep(1000);
			
			Assert.assertTrue(driver.getPageSource().contains("发送成功"));
			Reporter.log("9.邮件发送成功");
			
			success = 1;
		}catch (NoSuchElementException e1){
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("【end测试发送邮件】");
			if(success < 1) {
				Assert.fail("测试发送邮件失败，程序出错");
			}
		}
		
	}

}
