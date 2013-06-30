package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.Category;

public interface CategoryDao {
	//参数用于扩展，用来显示不同产品下的类别
	public List<Category> findByParentId(int pid);
	
	//根据id查找对应的类别
	public Category findById(int id);
}
