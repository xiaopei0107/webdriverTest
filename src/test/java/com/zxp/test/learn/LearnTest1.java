package com.zxp.test.learn;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.zxp.test.util.WebDriverUtil;

public class LearnTest1 {
	private WebDriver driver;

	/**
	 * 显示等待 设置等待时间  在设置范围内找到元素就立即执行后续程序
	 * 可自定义 等待的条件 用于复杂的判断
	 */
	@Test(priority=1)
	public void testExplicitWait() {
		driver.get("https://www.baidu.com/");
		try {
			WebElement kw = driver.findElement(By.id("kw"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(kw));
			
			Reporter.log("sk搜索框可见可单击");
			
			kw.sendKeys("selenium");
			Thread.sleep(1000);
			
			WebElement su = driver.findElement(By.id("su"));
			su.click();
			
			//自定义 等待的条件
			WebElement textBox = (new WebDriverWait(driver, 5))
					.until(new ExpectedCondition<WebElement>() {

						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By.xpath("//div[@class='nums']/span"));
						}
			});
			String text = textBox.getText();
			if(!text.startsWith("百度为您找到相关结果")) Assert.fail("搜索失败");
			
			Boolean spanVisible = (new WebDriverWait(driver, 5))
					.until(new ExpectedCondition<Boolean>() {

						@Override
						public Boolean apply(WebDriver d) {
							return d.findElement(By.xpath("//div[@class='nums']/span")).isDisplayed();
						}
			});
			Assert.assertTrue(spanVisible);
			Reporter.log("搜索内容出现..."+text);
			
		} catch (NoSuchElementException e) {
			Assert.fail("没找到搜索框"); //没找到搜索框 用例失败
			e.printStackTrace();
		} catch (Exception e) {
			Reporter.log("系统报错");
			e.printStackTrace();
		}
		
	}
	
	@Test(priority=2)
	public void testAjaxDivOption(){
		try {
			Reporter.log("测试搜狗页面开始");
			driver.get("https://www.sogou.com/");
			WebElement textBox = driver.findElement(By.id("query"));
			textBox.click();
			
		//	Actions action = new Actions(driver);
			Thread.sleep(1000);
			/*try {
				List<WebElement> hisList = driver.findElements(By.xpath("//ul[@class='suglist']/li"));
				for (WebElement webElement : hisList) {
					if(webElement.isDisplayed()){
						action.moveToElement(webElement);
						
						WebElement closeEle = driver.findElement(By.xpath("//ul[@class='suglist']/li/a"));
						closeEle.click();
					}
				}
				Reporter.log("点击搜狗搜索框历史记录："+hisList.size()+"条");
			} catch (Exception e) {
				Reporter.log("点击搜狗搜索框无历史记录");
			}*/
			
			
			List<WebElement> eleList = driver.findElements(By.xpath("//div[@id='vl']/div/ul/li"));
			Reporter.log("搜狗新闻条数："+eleList.size());
			for (int i=0; i< eleList.size(); i++) {
				WebElement ele = eleList.get(i);
				Reporter.log("第"+i+"行："+ele.getText());
				String atr = ele.getAttribute("lid");
				if("3".equals(atr)){ //点击第四个新闻信息
					Reporter.log("点击第四个新闻信息 "+ele.getText());
					ele.click();
					break;
				}
			}
			
			Reporter.log("测试搜狗页面结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = WebDriverUtil.getChromeWebDriver();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
