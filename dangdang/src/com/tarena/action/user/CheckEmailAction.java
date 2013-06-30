package com.tarena.action.user;

import com.tarena.service.UserService;
import com.tarena.service.impl.UserServiceImpl;

public class CheckEmailAction {
	//input
	private String email;
	//output 以json方式发送
	boolean ok = false;
	
	public String execute(){
		UserService service = new UserServiceImpl();
		ok = service.checkEmail(email);
		return "success";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
}
