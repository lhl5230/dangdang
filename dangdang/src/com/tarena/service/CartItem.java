package com.tarena.service;

import com.tarena.pojo.Product;

/*
 * 添加一个购物车信息类，用来封装购物车中的需要的信息
 */
public class CartItem {
	private Product product;//产品信息
	private int qty = 1;//产品购买的数量,默认购买的数量是1
	private boolean flag = true;//是否已经购买 ,默认为true(购买了),false(表示已经删除,应该显示在购物车的恢复列表中)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
