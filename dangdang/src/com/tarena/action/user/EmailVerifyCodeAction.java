package com.tarena.action.user;

import com.tarena.service.UserService;
import com.tarena.service.impl.UserServiceImpl;

public class EmailVerifyCodeAction {
	//input
	private String verifycode;
	//output
	private boolean ok;//页面根据返回值判断处理结果
	
	public String execute(){
		UserService userService = new UserServiceImpl();
		ok = userService.verify(verifycode.trim());
		return "success";
	}
	
	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
