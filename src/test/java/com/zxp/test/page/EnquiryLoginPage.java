package com.zxp.test.page;

import com.zxp.test.util.ContantsUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class EnquiryLoginPage {

    private WebDriver driver;

    @FindBy(name = "userLoginNo")
    private WebElement userLoginNo;

    @FindBy(id = "D-login-pass")
    private WebElement userLoginPsd;

    @FindBy(xpath = "//*[@id=\'D-login-code\']")
    private WebElement ccode;

    @FindBy(id = "loginsubm")
    private WebElement loginsubm;


    public EnquiryLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void login(String url,String username,String pwd){
        Reporter.log("进入钢管登录页面");
       try {
           driver.get(url);

           //访问搜狐登录页面 显式等待页面元素出来
           WebDriverWait wait = new WebDriverWait(driver, 10);
           wait.until(ExpectedConditions.visibilityOf(loginsubm));
           Assert.assertTrue(driver.getPageSource().contains("登  录"));
           Reporter.log("成功跳转至钢管登录页面");

           userLoginNo.sendKeys(username);
           userLoginPsd.sendKeys(pwd);
           ccode.sendKeys("1234");
           loginsubm.click();

           Reporter.log("开始登录钢管");
       }catch (Exception e){
           Reporter.log("登录钢管报错");
           e.printStackTrace();
       }
    }
}
