package com.zxp.test.util;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class WebDriverUtil {

	private static WebDriver driver;
	private static String webDriverPath = ResourceBundle.getBundle("base").getString("webdriver.path");

	public static WebDriver getChromeWebDriver() {
		Reporter.log("初始化webdriver webDriverPath：" + webDriverPath);
		// String path =
		// WebDriverUtil.class.getResource("/chromedriver.exe").getPath();
		System.setProperty("webdriver.chrome.driver", webDriverPath);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1280,728"); // 设置窗口大小
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		Reporter.log("初始化webdriver成功");
		return driver;
	}

	public static WebDriver getIEWebDriver() {
		String path = WebDriverUtil.class.getResource("/IEDriverServer.exe").getPath();
		driver = new InternetExplorerDriver();
		// 最大化窗口
		driver.manage().window().maximize();
		// 设置隐性等待时间
		// driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		return driver;
	}

}
