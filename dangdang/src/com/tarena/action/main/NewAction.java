package com.tarena.action.main;

import java.util.List;

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Product;

public class NewAction {
	//input 
	private int size = 8;
	//output
	private List<Product> pros;
	
	public String execute(){

		ProductDao productDao = new ProductDaoImpl();
		pros = productDao.findNew(size);
		return "success";
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Product> getPros() {
		return pros;
	}

	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	
}
