package com.zxp.test.util;

public class RandomUtil {

    public static String getRandomNum(){
       String verifyCode = String.valueOf((int)((Math.random()*9+1)*100000));
       return verifyCode;
    }
}
