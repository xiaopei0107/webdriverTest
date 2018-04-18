package com.zxp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeTest {
	WebDriver driver; 
	
	@Test(priority=1,enabled=false) 
  public void openBaidu() {
	  driver.get("https://www.baidu.com");
	  String baiduTitle = "百度一下，你就知道";
	  
	  try {
		  Assert.assertEquals(baiduTitle, driver.getTitle());
		  System.out.println("Test Pass");  
	} catch (Exception e) {
		e.printStackTrace();
	}
	 // driver.findElement(By.id("kw")).sendKeys("Selenium");  
     // driver.findElement(By.id("su")).click();  
  }
  
  @Test(priority=1,enabled=false) 
  public void openIcode() {
	  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	  driver.get("https://www.icloud.com/");
	  String baiduTitle = "百度一下，你就知道";
	  
	  try {
		 // Assert.assertEquals(baiduTitle, driver.getTitle());
		  System.out.println("Test Pass");  
	} catch (Exception e) {
		e.printStackTrace();
	}
	 // driver.findElement(By.id("kw")).sendKeys("Selenium");  
     // driver.findElement(By.id("su")).click();  
  }
  
  @Test(priority=1,enabled=false) 
  public void openNewsByLink(){
	  driver.get("https://www.baidu.com");
	  driver.findElement(By.linkText("新闻")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://news.baidu.com/");
  }
  @Test(priority=1,enabled=false) 
  public void openNewsByCss(){
	  driver.get("https://www.baidu.com");
	  driver.findElement(By.className("s_ipt")).sendKeys("java");
	  driver.findElement(By.className("s_btn")).click(); //按样式查找时 不能组合样式  只能单个样式查找
  }
  @Test(priority=1,enabled=false) 
  public void openNewsByName(){
	  driver.get("https://www.baidu.com");
	  driver.findElement(By.name("wd")).sendKeys("java");
	  driver.findElement(By.id("su")).click(); //按样式查找时 不能组合样式  只能单个样式查找
  }
  @Test(priority=1,enabled=false) 
  public void openNewsByCssSelector(){
	  driver.get("https://www.baidu.com");
	  driver.findElement(By.cssSelector("#kw")).sendKeys("java");
	  driver.findElement(By.cssSelector("#su")).click(); 
  }
  @Test(priority=1,enabled=false) 
  public void openNewsByPath(){
	  driver.get("http://news.baidu.com/");
	  List<WebElement> list =driver.findElements(By.xpath(".//*[@id='pane-news']/ul[3]/li/a"));
	  for(int i=0;i<list.size();i++){
		  WebElement element = list.get(i);
		  System.out.println(element.getAttribute("href"));
	  }
  }
  
  /**
   * navigate的使用  跳转 前进 后面 刷新
   */
  @Test(priority=1,enabled=false) 
  public void learnNavigate() {
	try {
		  driver.get("http://www.bsteel.com.cn/newexchange/index.do");
		//设置隐性等待时间    
		  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  
		  driver.navigate().to("http://news.baidu.com");
		  Thread.sleep(2000);
		  
		  driver.navigate().back();
		  Thread.sleep(2000);
		  
		  driver.navigate().forward();
		  Thread.sleep(2000);
		  
		  driver.navigate().refresh();
		  
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
  
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "D://test//chromedriver.exe");  
	  driver = new ChromeDriver();
	 // driver.manage().window().maximize();	//设置浏览器窗口最大化
	 // driver.manage().timeouts().pageLoadTimeout(1,TimeUnit.SECONDS);  //页面加载时间 超过这个时间就报错
	  
	  //浏览器位置显示
//	  Point targetPosition = new Point(300, 600);  
//      driver.manage().window().setPosition(targetPosition);  
//      System.out.println(driver.manage().window().getPosition());  
	  
	  //浏览器初始化大小
//	  Dimension targetSize = new Dimension(1024, 768);   
//      driver.manage().window().setSize(targetSize);  
//      System.out.println( driver.manage().window().getSize());
	  
	  //浏览器全屏
	  driver.manage().window().fullscreen();  
  }
  
  @Test
	public void test3() {
		try {
			System.out.println("测试方法[ test3() ]开始");
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
			System.out.println("测试方法[ test3() ]结束");
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
