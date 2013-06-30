package com.tarena.dao.impl;

import org.hibernate.Session;

import com.tarena.dao.ItemDao;
import com.tarena.pojo.Item;
import com.tarena.util.HibernateUtil;

public class ItemDaoImpl implements ItemDao {

	public void add(Item item) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(item);
	}

	public void delete(Item item) {
		Session session = HibernateUtil.getSession();
		session.delete(item);
	}

}
