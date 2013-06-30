package com.tarena.action.main;

import java.util.List;

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Product;
/*
 * 显示近一个月上架,销量最高的前10个产品信息.
 */
public class NewHotAction {
	//input
	private long time = System.currentTimeMillis() - 2592000000L; //要大于现在时间减一个月的差的值
	private int size = 10;
	//output
	private List<Product> pros;

	public String execute(){
		ProductDao productDao = new ProductDaoImpl();
		pros = productDao.findHotDate(time, size);
		return "success";
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
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
