package com.tarena.dao;

import com.tarena.pojo.Item;

/*
 * 购物清单dao
 */
public interface ItemDao {
	//添加购物条目
	public void add(Item item);
	//删除购物条目
	public void delete(Item item);
}
