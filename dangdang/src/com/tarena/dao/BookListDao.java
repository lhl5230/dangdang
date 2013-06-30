package com.tarena.dao;

import java.util.List;

import com.tarena.pojo.Category;
import com.tarena.pojo.CategoryProduct;

public interface BookListDao {
	/*
	 * 根据ID查找出类别（一对多关联，子类目也可以关联查出来）
	 */
	public Category findById(int id);
	/*
	 * 根据cat_id，从d_category_product查找出cat_id下的product个数
	 */
	public long findProductCountById(int id);
	
	
}
