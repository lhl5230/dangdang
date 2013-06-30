package com.tarena.action.main;

import com.tarena.action.BaseAction;
import com.tarena.util.Constant;

public class LoginOutAction extends BaseAction{
	private String prePage;//登录前页面
	public String execute(){
//		 获取跳转到登陆界面之前的页面地址，由拦截器提供
		prePage = (String) session.get(Constant.PREPAGE);
		
		session.remove(Constant.USER);
		return "success";
	}
	public String getPrePage() {
		return prePage;
	}
	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}
	
}
