package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.Product;
public interface ProductDao {
	/*
	 * 找出最新上架的size个产品
	 */
	public List<Product> findNew(int size);
	
	/*
	 * 编辑推荐产品 ，随机挑选size本书
	 */
	public List<Product> recommend(int size);
	/*
	 * 根据当前的catId，分页查找出对应的产品
	 * currentPage ：当前的页面
	 * pageSize ： 每页显示的产品数量
	 * sort: 排序方式
	 * public List<Product> findByCatId(int catId,int currentPage,int pageSize);	
	 */
	
	 public List<Product> findByCatId(int catId,int currentPage,int pageSize,String sort);
	/*
	 * 根据id查找对应的产品
	 */
	public Product findById(int pid);
	/*
	 * 根据订单销量统计,取出总销量最高的前4个产品显示
	 */
	public List<Product> findHot(int size);
	/*
	 * 显示近一个月上架,销量最高的前10个产品信息.
	 */
	public List<Product> findHotDate(long date,int size);
	/*
	 * 根据关键字查找对应的产品
	 */
	public List<Product> findProByKey(String key,int currentPage, int pageSize,String sort);
	public List<Product> findProByKey(String key);
}


