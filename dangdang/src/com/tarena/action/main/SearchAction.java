package com.tarena.action.main;

import java.util.List;
/*
 * 搜索产品action
 */

import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Product;
public class SearchAction {
	//intpu
	private String key;
	private int currentPage =1;
	private int pageSize = 3;
	private String sort = "addTime";//排序方式,默认按上架时间
	
	//output
	private List<Product> pros;
	private int totalPage = 1;//一共有多少页
	private int totalNum; //搜索结果一共有多少条记录
	public String execute(){
		ProductDao productDao = new ProductDaoImpl();
		pros = productDao.findProByKey(key.trim(), currentPage, pageSize, sort);
		List<Product> ps = productDao.findProByKey(key.trim());
		if (pros != null) {
			totalNum = ps.size();
			totalPage = (ps.size() % pageSize == 0) ? (ps.size() / pageSize)
					: ((ps.size() / pageSize) + 1);
		}
		return "success";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<Product> getPros() {
		return pros;
	}
	public void setPros(List<Product> pros) {
		this.pros = pros;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
