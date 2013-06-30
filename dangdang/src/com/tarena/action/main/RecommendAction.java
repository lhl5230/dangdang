package com.tarena.action.main;

import java.util.List;

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Product;
/*
 * 编辑推荐图书
 */
public class RecommendAction {
	//input 默认为2本
	private int size = 2;
	//output
	private List<Product> list;
	
	public String execute(){
		ProductDao productDao = new ProductDaoImpl();
		list = productDao.recommend(size);
		return "success";
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	
	
}
