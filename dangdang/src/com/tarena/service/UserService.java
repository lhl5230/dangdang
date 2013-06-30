package com.tarena.service;

import com.tarena.pojo.User;

public interface UserService {
	public void register(User user);//注册
	public boolean checkEmail(String email);//检测邮箱是否可用
	public boolean verify(String verifycode);//检测邮箱验证码
	public User login(String email,String pwd);//登陆
	public void update(User user);//更新用户信息
}
