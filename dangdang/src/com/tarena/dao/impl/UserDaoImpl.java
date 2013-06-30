package com.tarena.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.UserDao;
import com.tarena.pojo.User;
import com.tarena.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	//用户注册
	public void save(User user) {
		Session session = HibernateUtil.getSession();
		session.save(user);
	}
	//用户登陆
	public User login(String email, String password){
		Session session = HibernateUtil.getSession();
		String hql = "select new User(id,email,nickname,password,userIntegral," +
				"isEmailVerify,emailVerifyCode,lastLoginTime,lastLoginIp) " +
				"from User where email=? and password=?";
		Query query = session.createQuery(hql).setParameter(0, email).setParameter(1, password);
		User user = (User) query.uniqueResult();
		return user;
	}
	//根据邮箱查找用户，可以判断是否已经注册 ，登陆时查找用户
	public User findByEmail(String email) {
		Session session = HibernateUtil.getSession();
		String hql = "from User where email = ?";
		User user = (User)session.createQuery(hql).setParameter(0, email).uniqueResult();
		return user;
		
	}
	//判断邮箱验证码是否正确
	public User findByEmailVerifyCode(String emailCode) {
		Session session = HibernateUtil.getSession();
		String hql = "from User where email_verify_code = ?";
		User user = (User) session.createQuery(hql).setParameter(0, emailCode).uniqueResult();
		return user;
	}
	//更新用户数据
	public void update(User user) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(user);
	}
	public User findUserById(int id) {
		Session session = HibernateUtil.getSession();
		User user = (User) session.createQuery("from User where id = ?").setParameter(0, id).uniqueResult();
		return user;
	}
	

}
