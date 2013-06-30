package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.ReceiveAddress;

/*
 * 管理地址的dao
 */
public interface AddressDao {
	/*
	 * 增加地址
	 */
	public void add(ReceiveAddress address);
	/*
	 * 根据用户id查找地址
	 */
	public List<ReceiveAddress> findById(int userId);
	/*
	 * 根据地址主键id查找地址
	 */
	public ReceiveAddress findAddr(int id);
}
