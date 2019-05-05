package com.zxp.test.learn;

import org.testng.annotations.Test;

/**
 * testng 实现并发测试
 * TestNG测试的并发执行详解：https://blog.csdn.net/taiyangdao/article/details/52159065
 * //多线程测试,没有关联的用例可以使用多线程减少执行时间
 */
public class ThreadTest {

    private int i = 1;


    /**
     * 3个线程同时运行，共执行10次
     */
    @Test(invocationCount = 10,threadPoolSize = 3)
    public void threadTest(){
        System.out.println("运行次数："+(i++));
        System.out.printf("Thrad Id : %s%n",Thread.currentThread().getId());
    }

}
