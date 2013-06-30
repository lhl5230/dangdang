package com.tarena.util;
/*
 * 给用户发送邮件
 */
public class EmailUtil {
	public static void sendEmail(String message,String email){
		System.out.println("给"+email+"发送邮件,内容:"+message);
	}
}
