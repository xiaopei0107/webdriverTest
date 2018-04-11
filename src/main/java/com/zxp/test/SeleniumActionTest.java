package com.zxp.test;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

/** 
 * 在使用 Selenium WebDriver 做自动化测试的时候，会经常模拟鼠标和键盘的一些行为。
 * 比如使用鼠标单击、双击、右击、拖拽等动作；或者键盘输入、快捷键使用、组合键使用等模拟键盘的操作。
 * 在 WebDeriver 中，有一个专门的类来负责实现这些测试场景，那就是 Actions 类，
 * 在使用该类的过程中会配合使用到 Keys 枚举以及 Mouse、 Keyboard、CompositeAction 等类
 * https://www.ibm.com/developerworks/cn/java/j-lo-keyboard/
 */
public class SeleniumActionTest {
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
	 * 鼠标停留 出现下拉菜单 点击下拉菜单
	 * 在使用 Selenium WebDriver 做自动化测试的时候，会经常模拟鼠标和键盘的一些行为。
	 * 比如使用鼠标单击、双击、右击、拖拽等动作；或者键盘输入、快捷键使用、组合键使用等模拟键盘的操作。
	 * 在 WebDeriver 中，有一个专门的类来负责实现这些测试场景，那就是 Actions 类，
	 * 在使用该类的过程中会配合使用到 Keys 枚举以及 Mouse、 Keyboard、CompositeAction 等类
	 * https://www.ibm.com/developerworks/cn/java/j-lo-keyboard/
	 */
	@Test
	public void testMouseStop(){
		try {
			driver.get("https://www.baidu.com/");
			Thread.sleep(1000);
			
			WebElement settings = driver.findElement(By.cssSelector("#u1 > a[name='tj_settingicon']"));
			Actions action = new Actions(driver);
			action.moveToElement(settings).perform();
			
			driver.findElement(By.linkText("高级搜索")).click();
			
			//eg2
//			WebElement inputbox = driver.findElement(By.id("kw"));  
//	        inputbox.sendKeys("selenium a"); 
//	        
//	        // 自动补全其中一个选择项  F12不好定位下拉元素时 要看源代码是哪个div显示与隐藏 修改其属性让其显示 再定位元素
//	        WebElement auto_text = driver.findElement(By.xpath("//*[@id='form']/div/ul/li[@data-key='selenium api文档']")); 
//	        Actions action = new Actions(driver);  
//	        action.moveToElement(auto_text).click().perform(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 抓取页面段落内容
	 */
	@Test
	public void testMouseHold(){
		try {
			driver.get("https://www.baidu.com/duty/");  
			
			WebElement firstContent =driver.findElement(By.cssSelector("div.dwri_bule p"));
			
			WebElement dotContent =driver.findElement(By.cssSelector("div.dwri_bule ul :first-child"));
			
			Actions action = new Actions(driver);
			//搜索选中文本移至文本二处
			action.clickAndHold(firstContent).moveToElement(dotContent).perform();
			action.release(); //释放鼠标
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拖取页面元素到另一处
	 */
	@Test
	public void testDraggable(){
		try {
			driver.get("http://jqueryui.com/resources/demos/droppable/default.html");  
			// 定位能拖拽的元素  
	        WebElement move_ele = driver.findElement(By.id("draggable"));  
	          
	        // 定位拖拽目标位置元素  
	        WebElement target_ele = driver.findElement(By.id("droppable"));  
			
			Actions action = new Actions(driver);
			action.dragAndDrop(move_ele, target_ele).build().perform();
			
			// //*[@id='droppable']/p[text()='Dropped!']
			WebElement droped = driver.findElement(By.cssSelector("div#droppable p"));
			Assert.assertEquals("Dropped!", droped.getText());
			System.out.println("拖拽到目标位置成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
