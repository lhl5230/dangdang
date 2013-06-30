package com.tarena.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.tarena.action.user.EmailVerifyCodeAction;
import com.tarena.dao.CategoryDao;
import com.tarena.dao.impl.CategoryDaoImpl;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.dao.impl.UserDaoImpl;
import com.tarena.pojo.Book;
import com.tarena.pojo.Category;
import com.tarena.pojo.CategoryProduct;
import com.tarena.pojo.Product;
import com.tarena.service.impl.UserServiceImpl;
import com.tarena.util.HibernateUtil;

public class Test {
	public static void main(String[] args) {
		/*Session session = HibernateUtil.getSession();
		Category category = (Category)session.load(Category.class, 8);
		System.out.println(category.getId() + " " + category.getName());
		for(Category c : category.getSubCats()){
			System.out.println("---" + c.getId() + " " + c.getName());
		}
		HibernateUtil.closeSession();*/
		UserServiceImpl impl = new UserServiceImpl();
		UserDaoImpl daoImpl = new UserDaoImpl();
		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
//		System.out.println(daoImpl.findByEmailVerifyCode("dc6c19a4-1f29-4ba0-91ac-927d06ea43c7"));
//		System.out.println(impl.verify("dc6c19a4-1f29-4ba0-91ac-927d06ea43c7 "));
		
//		System.out.println(category.getPnum());
//		CategoryDao categoryDao = new CategoryDaoImpl();
//		System.out.println(categoryDao.findByParentId(2));
		List<Product> list = productDaoImpl.findByCatId(9, 1, 3,"addTime");
		for(Product p : list){
			Book book = (Book)p;
			System.out.println(book.getId()+ " " +book.getProductName());
		}
//		List<Product> list2 = productDaoImpl.findHot(4);
//		for(Product p : list2){
//			Book book = (Book)p;
//			System.out.println(book.getId()+ " " +book.getProductName());
//		}
//		System.out.println(new Date((new Date().getTime()-2592000000L)));
//		System.out.println(System.currentTimeMillis()-2592000000L);
//		System.out.println(new Date().getTime());
		list = productDaoImpl.findProByKey("人  ",1,5,"addTime");
		for(Product p : list){
			Book book = (Book)p;
			System.out.println(book.getId()+ " " +book.getProductName());
		}
		List a = new ArrayList();
		List b = new ArrayList();
		
		a.add("aa");
		a.add("bb");
		b.addAll(a);
		System.out.println(b);
	}
}
