package com.zxp.test.page;

import com.zxp.test.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Random;

public class AddEnquiryPage {

    private WebDriver driver;

    @FindBy(name = "enquiryName")
    private WebElement enquiryName;

    @FindBy(id = "datetimeStart1")
    private WebElement datetimeStart1;

    @FindBy(id = "kssj1")
    private WebElement kssj1;

    @FindBy(id = "kssj2")
    private WebElement kssj2;

    @FindBy(id = "datetimeStart2")
    private WebElement datetimeStart2;

    @FindBy(id = "jssj1")
    private WebElement jssj1;

    @FindBy(id = "jssj2")
    private WebElement jssj2;

    @FindBy(xpath = "//*[@id=\'D-login-code\']")
    private WebElement ccode;

    @FindBy(id = "suppDeposit")
    private WebElement suppDeposit;

    @FindBy(css = "input[name='jhgc']:nth-child(1)")
    private WebElement jhgc;

    @FindBy(css = "input[name='paymentType'][value='5']")
    private WebElement paymentType;

    @FindBy(css = "input[value='油气开采']")
    private WebElement zylx;

    @FindBy(css = "input[name='hkjsyd'][value='1']")
    private WebElement hkjsyd;



    public AddEnquiryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void addEnquiry(String url){
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement editEnquiryH4 = driver.findElement(By.cssSelector("#bigBox > h4"));
            wait.until(ExpectedConditions.visibilityOf(editEnquiryH4));
            Reporter.log("编辑询比价标题:"+editEnquiryH4.getText());

            enquiryName.sendKeys("zxp询比价"+ RandomUtil.getRandomNum());


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
