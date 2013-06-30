package com.tarena.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.tarena.action.user.EmailVerifyCodeAction;
import com.tarena.dao.BookListDao;
import com.tarena.dao.impl.BookListDaoImpl;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.dao.impl.UserDaoImpl;
import com.tarena.pojo.Book;
import com.tarena.pojo.Category;
import com.tarena.pojo.CategoryProduct;
import com.tarena.pojo.Product;
import com.tarena.service.impl.UserServiceImpl;
import com.tarena.util.HibernateUtil;

public class CategoryTest {
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
		
		BookListDao bookListDao = new BookListDaoImpl();
		
	}
}
