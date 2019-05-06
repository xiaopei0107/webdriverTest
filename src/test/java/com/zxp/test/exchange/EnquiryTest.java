package com.zxp.test.exchange;

import com.zxp.test.page.EnquiryLoginPage;
import com.zxp.test.util.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

/**
 * 钢管自动化-实现新建询价单
 */
public class EnquiryTest {
  private WebDriver driver;
  String baseUrl;

  @Test
  public void login() {
    Reporter.log("1.打开钢管首页");
    try {
      baseUrl = "http://localhost:8090/newexchange/enquiry/index.do";
      driver.get(baseUrl);

      WebDriverWait wait = new WebDriverWait(driver, 10);
      //找到登录按钮
      WebElement loginBtn = driver.findElement(By.cssSelector("div.denglu_btn > a.dl"));
      wait.until(ExpectedConditions.visibilityOf(loginBtn));
      String loginUrl = loginBtn.getAttribute("href");

     // WebDriverUtil.closeWindow(driver.getWindowHandle());

     // Reporter.log("当前页面标题："+driver.getTitle());
     // System.out.println("标题："+driver.getTitle());
     // Assert.assertEquals("管通天下-用户登录", driver.getTitle());

      EnquiryLoginPage loginPage = new EnquiryLoginPage(driver);
      loginPage.login(loginUrl,"UG6282","a8888888");

      Assert.assertTrue(driver.getPageSource().contains("欢迎您"));

      WebElement welcomeEl = driver.findElement(By.className("ellipsis"));
      if(welcomeEl.getText().contains("欢迎您")){
        Reporter.log("登录成功");
        Assert.assertTrue(true);
      }else{
        Reporter.log("登录失败");
        throw new Exception("登录失败");
      }

      //点击进入新建询价单页面
     // WebElement addEnquiryEl = driver.findElement(By.cssSelector("div#tab-1 > ul.gongneng_c > li:nth-child(3) >a"));
      WebElement addEnquiryEl = driver.findElement(By.xpath("//*[@id=\"tab-1\"]/ul/li[3]/a"));
      addEnquiryEl.click();


    }catch (Exception e){
        e.printStackTrace();
    }
  }
  @BeforeMethod
  public void beforeMethod() {
    if(null == driver){
      driver = WebDriverUtil.getChromeWebDriver();
    }
  }

  @AfterMethod
  public void afterMethod() {

  }
  @BeforeClass
  public void beforeClass() {
    driver = WebDriverUtil.getChromeWebDriver();
  }

  @AfterClass
  public void afterClass() {
    if(null != driver) driver.quit();
  }

}
