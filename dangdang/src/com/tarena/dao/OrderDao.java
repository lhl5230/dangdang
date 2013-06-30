package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.Item;
import com.tarena.pojo.Order;
import com.tarena.pojo.ReceiveAddress;

/*
 * 操作订单的dao
 */
public interface OrderDao {
	//添加订单
	public void add(Order order);
	//删除订单
	public void delete(Order order);
	//更新订单
	public void update(Order order);

	//添加地址
	public void saveAddr(ReceiveAddress address);
	public void addressUpdate(ReceiveAddress address);
	/*
	 * 根据用户id查找地址
	 */
	public List<ReceiveAddress> findById(int userId);
	/*
	 * 根据地址主键id查找地址
	 */
	public ReceiveAddress findAddr(int id);
	//添加购物条目
	public void addItem(Item item);
	/*
	 * 根据用户id查找order订单
	 */
	public List<Order> findOrderByUserId(int userId);
}
