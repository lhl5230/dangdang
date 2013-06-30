package com.tarena.action.main;

import java.util.List;

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Product;

public class HotAction {
	//input
	private int size = 4;//默认找热销的4个产品
	//output
	private List<Product> pros;
	public String execute(){
		ProductDao productDao = new ProductDaoImpl();
		pros = productDao.findHot(size);
		return "success";
	}
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
