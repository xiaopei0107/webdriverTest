package com.zxp.test.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class PassTest {
  @Test
  public void isPassTest() {
	  Reporter.log("测试方法isPassTest开始");
	  Assert.assertEquals("1", "1");
	  Reporter.log("测试方法isPassTest结束");
  }
  @Test
  public void isPassTest2() {
	  Reporter.log("测试方法isPassTest2开始");
	  Assert.assertEquals("1", "1");
	  Reporter.log("测试方法isPassTest2结束");
  }
  @Test
  public void isPassTest3() {
	  Reporter.log("测试方法isPassTest3开始");
	  Assert.assertEquals("1", "1");
	  Reporter.log("测试方法isPassTest3结束");
  }
  
  @BeforeClass
  public void beforeClass() {
	  Reporter.log("测试方法beforeClass开始");
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("测试方法beforeClass结束");
  }

}
