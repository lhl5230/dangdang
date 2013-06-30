package com.tarena.action.user;

import com.tarena.action.BaseAction;
import com.tarena.pojo.User;
import com.tarena.service.UserService;
import com.tarena.service.impl.UserServiceImpl;


public class RegisterAction extends BaseAction{
	//input
	private User user;
	//output
	
	public String execute(){
		user.setLastLoginIp(request.getRemoteAddr());//用request域获取
		UserService service = new UserServiceImpl();
		service.register(user);
		session.put("user", user); //把注册信息保存在session中
//	    进入邮箱验证页面	
		return "verify";
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
