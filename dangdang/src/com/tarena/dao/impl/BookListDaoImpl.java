package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.tarena.dao.BookListDao;
import com.tarena.pojo.Category;
import com.tarena.pojo.CategoryProduct;
import com.tarena.util.HibernateUtil;

public class BookListDaoImpl implements BookListDao {
	/*
	 * 根据ID查找出类别（一对多关联，子类目也可以关联查出来）(
	 */
	public Category findById(int id) {
		Session session = HibernateUtil.getSession();
		Category category = (Category) session.createQuery("from Category where id = ?")
				.setParameter(0, id).uniqueResult();
		return category;
	}
	/*
	 * 从d_category_product查找出所有cat_id下的product个数
	 */
	public long findProductCountById(int id) {
		Session session = HibernateUtil.getSession();
		String hql = "select count(product_id) from CategoryProduct " +
				"where catId = ?";
		long count = (Long) session.createQuery(hql).setParameter(0, id).uniqueResult();
		return count;
	}
	
}
