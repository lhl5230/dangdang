package com.tarena.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * cookie工具类
 */
public class CookieUtil {
	//缺省的应用名
	private static String default_path = "/dangdang";
	//缺省的生存时间
	private static int default_age = 365*24*3600;
	/*
	 * 添加一个cookie
	 */
	public static void addCookie(String name,String value,HttpServletResponse response,
			int age) throws UnsupportedEncodingException{
		Cookie cookie = new Cookie(name,URLEncoder.encode(value,"utf-8"));
		cookie.setMaxAge(age);
		cookie.setPath(default_path);
		response.addCookie(cookie);
	}
	
	public static void addCookie(String name,String value,HttpServletResponse response) 
			throws UnsupportedEncodingException{
		addCookie(name, value, response,default_age);
	}
	/*
	 * 依据cookie的名字，查找cookie的值，如果找不到，返回null
	 */
	public static String findCookie(String name,HttpServletRequest request) throws UnsupportedEncodingException{
		String value = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0;i<cookies.length;i++){
				Cookie curr = cookies[i];
				if(curr.getName().equals(name)){
					value = URLDecoder.decode(curr.getValue(),"utf-8");
				}
			}
		}
		return value;
	}
	/*
	 * 删除cookie
	 */
	public static void deleteCookie(String name,HttpServletResponse response){
		Cookie cookie = new Cookie(name,"");
		cookie.setMaxAge(0);
		cookie.setPath(default_path);
		response.addCookie(cookie);
	}
		
}
