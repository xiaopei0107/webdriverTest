package com.zxp.test.learn;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zxp.test.util.WebDriverUtil;

public class SeleniumTest {
	WebDriver driver;

	@BeforeClass
	@Parameters("Browser") 
	public void beforeClass(String browser) {
		if("Chrome".equals(browser)){
			driver = WebDriverUtil.getChromeWebDriver();
		}else if ("IE".equals(browser)){
			driver = WebDriverUtil.getIEWebDriver();
		}
	}

	@Test
	public void test1() {
		Reporter.log("测试方法[ test1() ]开始");
		driver.get("https://www.baidu.com");
		try {
			Thread.sleep(1000);

			WebElement setHome_link = driver.findElement(By.partialLinkText("设为主页"));
			setHome_link.click();
			Thread.sleep(1000);

			Set<String> whs = driver.getWindowHandles();
			for (Iterator iterator = whs.iterator(); iterator.hasNext();) {
				String wh = (String) iterator.next();
				System.out.println(wh);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ test1() ]结束");
		}
	}

	// 关于xpath这种定位方式，webdriver会将整个页面的所有元素进行扫描以定位我们所需要的元素，所以这是一个非常费时的操作，如果你的脚本中大量使用xpath做元素定位的话，将导致你的脚本执行速度大大降低，所以请慎用
	@Test(enabled=false)
	public void test2() {
		Reporter.log("测试方法[ test2() ]开始");
		try {
			driver.get("https://www.jd.com/");
			Thread.sleep(1000);
			System.out.println("京东首页：" + driver.getWindowHandle() + " url:" + driver.getCurrentUrl());

			// By.linkText全词匹配 By.partialLinkText部分匹配即模糊
			WebElement linkElement = driver.findElement(By.xpath("//div[@id='J_cate']/ul/li[2]/a[1]"));
			System.out.println("手机:" + linkElement.getAttribute("href"));
			linkElement.click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ test2() ]结束");
		}
	}

	/**
	 * 一般来说，自动化也是尽量在一个页面做完相关测试，才会切换到其他页面
	 * dependsOnMethods={"test2"},
	 */
	@Test(enabled=false) //依赖方法test2() test2先执行后执行test3()
	public void test3() {
		try {
			Reporter.log("测试方法[ test3() ]开始");
			driver.get("https://www.jd.com/");
			Thread.sleep(1000);

			// 点击京东左侧菜单中的 手机
			WebElement phone_link = driver.findElement(By.linkText("手机"));
			phone_link.click();
			Thread.sleep(1000);
			
			closeWindow(driver.getWindowHandle());
			
			driver.findElement(By.id("key")).sendKeys("华为手机");
			driver.findElement(By.cssSelector("#search-2014 .form button")).click();
			Thread.sleep(1000);
			
			//a[title='合约机']
			driver.findElement(By.cssSelector("a[title='合约机']")).click();
			Thread.sleep(1000);
			
			//点击搜索合约机结果第一条信息
			WebElement hyjElement = driver.findElement(By.cssSelector("#J_goodsList >ul>li:first-child>div>div:first-child>a"));
			String firstTitle = hyjElement.getAttribute("title").trim();
			hyjElement.click();
			Thread.sleep(1000);
			
			closeWindow(driver.getWindowHandle());
			
			WebElement phoneElement = driver.findElement(By.cssSelector("div.sku-name"));
			String phoneTitle = phoneElement.getText().trim();
			
			Assert.assertEquals(firstTitle, phoneTitle);
			System.out.println("打开链接标题是一样的");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ test3() ]结束");
		}
	}

	@Test
	public void testScript() {
		try {
			Reporter.log("测试方法[ testScript() ]开始");
			driver.get("http://blog.csdn.net/u011541946");
			Thread.sleep(1000);

			//初进页面时 某个元素需要下拉滚动条才会显示时 这个时候就要用javascript来拖动滚动条  直到显示某个元素处 才能进行点击行为
			WebElement element = driver.findElement(By.cssSelector("div.feed_copyright > p  :nth-child(2)"));
			JavascriptExecutor script = (JavascriptExecutor) driver; //创建一个javascript 执行实例  
			script.executeScript("arguments[0].scrollIntoView(true);", element); //执行js语句，拖拽浏览器滚动条，直到该元素到底部
			element.click();
			Thread.sleep(1000);
			
			//切换窗口 确保只有一个浏览页面
			closeWindow(driver.getWindowHandle());
			
			//在新窗口进行断言 是否为要打开的页面
			WebElement zpelement = driver.findElement(By.cssSelector("div.cont >ul >li a[href='php_engineer.html']"));
			Assert.assertEquals("PHP 中高级工程师", zpelement.getText());
			System.out.println("Test Pass"); 

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ testScript() ]结束");
		}
	}
	/**
	 * 定位找不到元素时 要考虑是否用了iframe技术，
	 * 如用需要切换到iframe页面 才能点击内部元素   switchTo().frame("rightMain")
	 * 如要从iframe中回到主页面 就switchTo().defaultContent()就能回到原页面了
	 */
	@Test
	public void testFrame() {
		try {
			Reporter.log("测试方法[ testFrame() ]开始");
			driver.get("http://data.pharmacodia.com/web/homePage/index?ns=1&ts=1&str=YWSJ");
			Thread.sleep(1000);
			
			//切换到iframe子页面
			driver.switchTo().frame("rightMain");	
			//在iframe中点击化学药选项，将弹出登录窗口 
			driver.findElement(By.cssSelector("span[title='化学药']")).click();
			Thread.sleep(1000);
			
			//需要切换到父页面 去点击登录操作
			driver.switchTo().defaultContent();
			driver.findElement(By.cssSelector("#noLoginAlert >div >button")).click();
			Thread.sleep(1000);
			
			WebElement welElement = driver.findElement(By.className("welco_login"));
			Assert.assertEquals("欢迎登录", welElement.getText());
			System.out.println("来到欢迎登录页面 Test pass..");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ testFrame() ]结束");
		}
	}

	/**
	 * 点击页面alert弹出框
	 */
	@Test
	public void testAlert() {
		try {
			Reporter.log("测试方法[ testAlert() ]开始");
			driver.get("http://news.cyol.com/node_60799.htm");
			Thread.sleep(1000);
			
			System.out.println(driver.switchTo().alert().getText()); //弹出文本内空
			driver.switchTo().alert().accept(); //确定
			//driver.switchTo().alert().dismiss();//取消
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ testAlert() ]结束");
		}
	}
	
	@Test
	public void testChangeColor() {
		try {
			Reporter.log("测试方法[ testChangeColor() ]开始");
			driver.get("https://www.baidu.com/");
			Thread.sleep(1000);
			
			// xpath:div[@id='u']/a[3]
			driver.findElement(By.cssSelector("#u1 >a[name='tj_login']")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.id("TANGRAM__PSP_10__footerULoginBtn")).click();
			Thread.sleep(1000);
			
			WebElement loginName = driver.findElement(By.id("TANGRAM__PSP_10__userName"));
			loginName.sendKeys("xiaopei0107");
			
			//使用js来改变文本颜色
			JavascriptExecutor js= (JavascriptExecutor)driver;  
			js.executeScript("arguments[0].setAttribute('style','background-color: yellow;border: 2px solid red;')", loginName);
			
			driver.findElement(By.id("TANGRAM__PSP_10__submit")).click();
			Thread.sleep(1000);
			
			WebElement errorEle = driver.findElement(By.id("TANGRAM__PSP_10__error"));
			
			//错误文本是否显示
			assert errorEle.isDisplayed();			
			Assert.assertEquals("请您输入密码", errorEle.getText());
			
			System.out.println("改变文本颜色Test Pass...");
//			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Reporter.log("测试方法[ testChangeColor() ]结束");
		}
	}
	
	
	//切换窗口 确保只有一个浏览页面
	private void closeWindow(String handle) {
		for (String temhandle : driver.getWindowHandles()) {
			if (!temhandle.equals(handle))
				driver.close();
			driver.switchTo().window(temhandle);

		}
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
