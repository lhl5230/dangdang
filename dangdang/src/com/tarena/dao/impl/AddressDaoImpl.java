package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.AddressDao;
import com.tarena.pojo.ReceiveAddress;
import com.tarena.util.HibernateUtil;

public class AddressDaoImpl implements AddressDao{

	public void add(ReceiveAddress address) {
		Session session = HibernateUtil.getSession();
		session.saveOrUpdate(address);
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

}
