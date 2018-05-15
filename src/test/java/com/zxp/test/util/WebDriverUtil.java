package com.zxp.test.util;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverUtil {

	private static WebDriver driver;
	private static String webDriverPath = ResourceBundle.getBundle("base").getString("webdriver.path");

	public static WebDriver getChromeWebDriver() {
		//String path = WebDriverUtil.class.getResource("/chromedriver.exe").getPath();
		System.setProperty("webdriver.chrome.driver", webDriverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver getIEWebDriver() {
		String path = WebDriverUtil.class.getResource("/IEDriverServer.exe").getPath();
		driver = new InternetExplorerDriver();
		// 最大化窗口
		driver.manage().window().maximize();
		// 设置隐性等待时间
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;
	}

}
