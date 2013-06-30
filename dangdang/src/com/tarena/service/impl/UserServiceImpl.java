package com.tarena.service.impl;

import com.tarena.dao.UserDao;
import com.tarena.dao.impl.UserDaoImpl;
import com.tarena.pojo.User;
import com.tarena.service.UserService;
import com.tarena.util.Constant;
import com.tarena.util.EmailUtil;
import com.tarena.util.EncryptUtil;
import com.tarena.util.VerifyCodeUtil;

public class UserServiceImpl implements UserService {
	//用户注册
	public void register(User user) {
//		密码加密
		String pwd = EncryptUtil.encryptMD5(user.getPassword());
		user.setPassword(pwd);
//	    生成邮箱验证码
		String code = VerifyCodeUtil.generatorCode();
//		非表单项设置初始值
		user.setIsEmailVerify("N");//注册初始化没有邮箱验证
		user.setEmailVerifyCode(code);//邮箱验证码
		user.setUserIntegral(Constant.NORMAL);//用户等级,初始为普通用户
		user.setLastLoginTime(System.currentTimeMillis());
		
//		将用户注册信息写入d_user表
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);

//	    给用户邮箱发送验证码
		EmailUtil.sendEmail(code, user.getEmail());
	
	}
//	检测邮箱是否可用
	public boolean checkEmail(String email) {
		boolean ok = false;
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findByEmail(email);
		if(user != null){//数据库有数据
			ok = false;//被占用
		}else{
			ok = true;//可用
		}
		return ok;
	}
//	检测用户邮箱验证码
	public boolean verify(String emailCode) {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findByEmailVerifyCode(emailCode);
		boolean ok = false;
		if(user != null){//数据库有数据
			ok = true;//验证码正确
//			如果正确,修改该用户的is_email_verify字段值,设置为"Y"
			user.setIsEmailVerify("Y");
			userDao.update(user);
		}
		return ok;
	}
	public User login(String email, String pwd) {
		UserDao userDao = new UserDaoImpl();
		User user = userDao.login(email, pwd);
		return user;
	}
	//更新用户信息
	public void update(User user) {
		UserDao userDao = new UserDaoImpl();
		userDao.update(user);
	}
	
}
