package com.tarena.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tarena.dao.ProductDao;
import com.tarena.pojo.Book;
import com.tarena.pojo.Product;
import com.tarena.util.HibernateUtil;

public class ProductDaoImpl implements ProductDao{
	//找出最新上架的size个产品
	public List<Product> findNew(int size) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product order by addTime";
		Query query = session.createQuery(hql).setFirstResult(0).setMaxResults(size);
		return query.list();
	}
	//编辑推荐  类似于 Product pro = new Book();
	public List<Product> recommend(int size) {
		Session session = HibernateUtil.getSession();
		String sql = "select * from d_product dp " +
				"join d_book db on(dp.id=db.id) " +
				"order by rand() limit 0,?";
		Query query = session.createSQLQuery(sql).
				addEntity("db",Book.class).setParameter(0, size);
		List list = query.list();
		return list;

	}
	/*
	 * 根据当前的catId，分页查找出对应的产品
	 * currentPage ：当前的页面
	 * pageSize ： 每页显示的产品数量
	 * sort: 排序方式
	 */

	public List<Product> findByCatId(int catId, int currentPage, int pageSize,String sort) {
		Session session = HibernateUtil.getSession();
		String hql = "select p from Category c join c.pros p where c.id = ? order by p."+sort+" desc";
		int beginIndex = (currentPage-1)*pageSize;
		List<Product> list = session.createQuery(hql).setParameter(0, catId)
				.setFirstResult(beginIndex).setMaxResults(pageSize).list();
		return list;
	}
	/*
	 * 	public List<Product> findByCatId(int catId, int currentPage, int pageSize) {
		Session session = HibernateUtil.getSession();
		String hql = "select p from Category c join c.pros p where c.id = ? order by p.addTime desc";
		int beginIndex = (currentPage-1)*pageSize;
		List<Product> list = session.createQuery(hql).setParameter(0, catId)
				.setFirstResult(beginIndex).setMaxResults(pageSize).list();
		return list;
	}
	 * 根据id查找对应的产品 ,由于有多对多关联，所以产品所对应的类别也查出来了
	 */
	public Product findById(int pid) {
		Session session = HibernateUtil.getSession();
		Product product = (Product) session.get(Product.class, pid);
		return product;
	}
	/*
	 * 根据订单销量统计,取出总销量最高的前4个产品显示
	 */
	public List<Product> findHot(int size) {
		Session session = HibernateUtil.getSession();
		String hql = "select dp "+
					"from Item di join di.pro dp "+ 
				"group by di.pro.id order by sum(di.productNum) desc";
		Query query = session.createQuery(hql).setFirstResult(0).setMaxResults(size);
		return query.list();
	}
	
	public List<Product> findHotDate(long date, int size) {
		Session session = HibernateUtil.getSession();
		String hql = "select dp "+
		"from Item di join di.pro dp where dp.addTime >? "+ 
		"group by di.pro.id order by sum(di.productNum) desc";
		Query query = session.createQuery(hql).setParameter(0, date)
				.setFirstResult(0).setMaxResults(size);
		return query.list();
	}
	/*
	 * 根据关键字查找对应的产品
	 */
	public List<Product> findProByKey(String key,int currentPage, int pageSize,String sort) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product where productName like ? order by "+sort+" desc";
		int beginIndex = (currentPage-1)*pageSize;
		Query query = session.createQuery(hql).setParameter(0, "%"+key+"%")
				.setFirstResult(beginIndex).setMaxResults(pageSize);
		return query.list();
	}
	public List<Product> findProByKey(String key) {
		Session session = HibernateUtil.getSession();
		String hql = "from Product where productName like :key";
		Query query = session.createQuery(hql).setParameter("key", "%"+key+"%");
		return query.list();
	}
	
	
	
}
