package com.zxp.test.upload;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * 截图类
 * 
 * @author Sotiy-赵小沛
 *
 */
public class TakeScreenshotTest {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "D://test//chromedriver.exe");
		driver = new ChromeDriver();
		// 最大化窗口
		driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	/**
	 * 截图到指定目录
	 */
	@Test
	public void testScreenshot() {
		try {
			driver.get("https://www.baidu.com");
			Thread.sleep(1000);

			// 调用截图方法 只截浏览器里面的图而不是整个屏幕
			TakesScreenshot screen = (TakesScreenshot) driver;
			File src = screen.getScreenshotAs(OutputType.FILE);

			// 拷贝截图文件到我们项目./Screenshots
			FileUtils.copyFile(src, new File(".\\Screenshots\\screen.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRobot() {
		try {
			driver.get("https://www.baidu.com");
			Thread.sleep(1000);

			// 调用截图方法 截图整个屏幕的图
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

			// 拷贝截图文件到我们项目./Screenshots
			ImageIO.write(image, "png", new File(".\\Screenshots\\screen_robot.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * webdriver处理不信任证书的情况
	 */
	public void testHandPopup() {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		ChromeOptions options = new ChromeOptions();
		options.merge(cap);

		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://10.60.16.9/");
	}

	/**
	 * 日历选择器有readonly时 用javascript去掉其属性
	 * 报org.openqa.selenium.StaleElementReferenceException: stale element
	 * reference: element is not attached to the page document 重新加载元素就可以了
	 */
	@Test
	public void testDatePicker() {
		try {
			driver.get("http://www.bsteel.com.cn/newexchange/xhwzJoinBuy/doDefault.do?type=2");
			Thread.sleep(1000);

			WebElement dateEle = driver.findElement(By.id("bidDateEnd"));

			// 日历选择器有readonly时 用javascript去掉其属性
			JavascriptExecutor script = (JavascriptExecutor) driver;
			script.executeScript(
					"var bidDate = document.getElementById('bidDateEnd'); bidDate.removeAttribute('readonly');",
					dateEle);

			// 直接输入值再点击查询
			dateEle.sendKeys("2018-03-08");
			driver.findElement(By.id("btn_search")).click();
			Thread.sleep(2000);

			// 需要重新加载元素 不然报元素不在当前页
			WebElement dateEle2 = driver.findElement(By.id("bidDateEnd"));
			Assert.assertEquals("2018-03-08", dateEle2.getAttribute("value"));
			System.out.println("日期选取通过 Test Pass.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	/**
	 * Robot类处理文件上传
	 */
	public void testUploadPic() {
		try {
			driver.get("https://www.baidu.com");
			Thread.sleep(1000);

			// 指定图片的路径
			StringSelection select = new StringSelection("D:\\12.jpg");

			// 把图片文件路径复制到剪贴板
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
			System.out.println("selection" + select);

			driver.findElement(By.className("soutu-btn")).click();

			driver.findElement(By.className("upload-pic")).click();

			Robot robot = new Robot();
			Thread.sleep(1000);

			// 按下回车
			robot.keyPress(KeyEvent.VK_ENTER);

			// 释放回车
			robot.keyRelease(KeyEvent.VK_ENTER);

			// 按下 CTRL+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			// 释放 CTRL+V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);

			// 点击回车 Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testExplicitWait() {
		try {
			driver.get("https://www.icloud.com/");

			// 创建一个WebDriverWait类的一个对象 wait，设置5，默认单位是秒
			WebDriverWait wait = new WebDriverWait(driver, 5);

			// 等待知道5秒之后该元素还是不可见，就报错。
			driver.switchTo().frame("auth-frame");

			WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appleId")));
			
			boolean isShow = ele.isDisplayed();
			
			// 判断  
	        if (isShow) {  
	            System.out.println("===== 元素可见======");  
	        } else {  
	            System.out.println("===== 元素不可见======");  
	        }  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
