package com.zxp.test.util;

import java.util.ResourceBundle;

/**
 * 常量类
 * @author belita
 *
 */
public class ContantsUtil {

	public static ResourceBundle bundle = ResourceBundle.getBundle("base");
	
	//搜狐邮箱信息
	public static String SOHU_MAIL_URL = bundle.getString("sohu.mail.url");
	public static String SOHU_MAIL_NAME = bundle.getString("sohu.mail.name");
	public static String SOHU_MAIL_PWD = bundle.getString("sohu.mail.pwd");


	public static String ENQUIRY_URL = bundle.getString("enquiry.url");
	
}
