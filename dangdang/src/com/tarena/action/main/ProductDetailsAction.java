package com.tarena.action.main;

import java.util.Set;

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Category;
import com.tarena.pojo.Product;
/*
 * 查询单个产品的详细信息
 */
public class ProductDetailsAction {
	//input
	private int id;
	//output
	private Product product;
	private Set<Category> cats;
	
	public String execute(){
		ProductDao productDao = new ProductDaoImpl();
		product = productDao.findById(id);
		cats = product.getCats();
		return "success";		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<Category> getCats() {
		return cats;
	}

	public void setCats(Set<Category> cats) {
		this.cats = cats;
	}
	
}
