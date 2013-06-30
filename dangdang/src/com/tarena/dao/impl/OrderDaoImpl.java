package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.OrderDao;
import com.tarena.pojo.Item;
import com.tarena.pojo.Order;
import com.tarena.pojo.ReceiveAddress;
import com.tarena.util.HibernateUtil;

public class OrderDaoImpl implements OrderDao {

	public void add(Order order) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(order);
		
	}

	public void delete(Order order) {
		Session session = HibernateUtil.getSession();
		session.delete(order);
	}

	public void update(Order order) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(order);
	}


	public void saveAddr(ReceiveAddress address) {
		Session session = HibernateUtil.getSession();
		session.save(address);
		
	}
	
	public void addressUpdate(ReceiveAddress address) {
		Session session = HibernateUtil.getSession();
		session.update(address);
	}

	public List<ReceiveAddress> findById(int userId) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ReceiveAddress where userId = ?")
					.setParameter(0, userId);
		return query.list();
	}
	public ReceiveAddress findAddr(int id) {
		Session session = HibernateUtil.getSession();
		ReceiveAddress address = (ReceiveAddress) session.get(ReceiveAddress.class, id);
		return address;
	}
	public void addItem(Item item) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(item);
	}

	public List<Order> findOrderByUserId(int userId) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Order where userId = ?").setParameter(0, userId);
		return query.list();
	}
}
