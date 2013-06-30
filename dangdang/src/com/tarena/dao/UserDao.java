package com.tarena.dao;

import com.tarena.pojo.User;

public interface UserDao {
	public void save(User user);//注册
	public User login(String email,String password);
	public User findByEmail(String email);
	public User findByEmailVerifyCode(String emailCode);
	
	public void update(User user);
	/*
	 * 根据用户id查找用户
	 */
	public User findUserById(int id);
}
