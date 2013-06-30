package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.CartDao;
import com.tarena.dao.ProductDao;
import com.tarena.pojo.Item;
import com.tarena.pojo.Product;
import com.tarena.util.HibernateUtil;

public class CartDaoImpl implements CartDao{

	public Item addItem(Item item) {
		Session session = HibernateUtil.getSession();
		//记得删除事务
		session.beginTransaction();
		session.saveOrUpdate(item);
		session.beginTransaction().commit();
		return item;
	}

	public Item findById(int id) {
		Session session = HibernateUtil.getSession();
		Item item = (Item) session.createQuery("from Item where productId = ?")
				.setParameter(0, id).uniqueResult();
		return item;
	}

	public List<Item> cartList(int orderId) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Item where orderId = ?")
				.setParameter(0, orderId);
		return query.list();
	}
	

}
