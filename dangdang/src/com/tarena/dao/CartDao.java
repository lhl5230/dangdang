package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.Item;

public interface CartDao {
	/*
	 * 根据id查找item
	 */
	public Item findById(int id);
	/*
	 * 根据产品id添加到购物车
	 */
	public Item addItem(Item item);
	/*
	 * 根据order_id查找List<Item>
	 */
	public List<Item> cartList(int orderId);
}
