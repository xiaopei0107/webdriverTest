package example;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IETest {
	
	WebDriver driver; 
	
  @Test
  public void openBaidu() {
	  driver.get("https://www.baidu.com");
	  driver.findElement(By.id("kw")).sendKeys("Selenium");  
      driver.findElement(By.id("su")).click();  
  }
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.ie.driver", "D://test//webdriver//IEDriverServer.exe");
	  driver = new InternetExplorerDriver();
	//最大化窗口    
      driver.manage().window().maximize();    
      //设置隐性等待时间    
      driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
