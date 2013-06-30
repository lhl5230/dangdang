package com.tarena.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
/*
 * 设置一些Action常用的对象
 */
public class BaseAction implements SessionAware,ServletRequestAware,ServletResponseAware{
	public Map<String, Object> session;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

}
