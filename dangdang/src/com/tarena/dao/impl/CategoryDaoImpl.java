package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.CategoryDao;
import com.tarena.pojo.Category;
import com.tarena.util.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao{

	public List<Category> findByParentId(int pid) {
		Session session = HibernateUtil.getSession();
		//只发送1条语句
		String hql = "select distinct c from Category " +
				"c left outer join fetch c.subCats where c.parentId = ?";
		Query query = session.createQuery(hql).setParameter(0, pid);
		return query.list();
	}

	public Category findById(int id) {
		Session session = HibernateUtil.getSession();
		Category category = (Category)session.get(Category.class, id);
		return category;
	}

}
