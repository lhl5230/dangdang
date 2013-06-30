package com.tarena.action.main;

import java.util.List;

import com.tarena.dao.CategoryDao;
import com.tarena.dao.impl.CategoryDaoImpl;
import com.tarena.pojo.Category;

public class CategoryAction {
	//input  默认为图书下的类别
	private int pid = 1;
	//list
	private List<Category> cats;
	
	public List<Category> getCats() {
		return cats;
	}

	public void setCats(List<Category> cats) {
		this.cats = cats;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String execute(){
		CategoryDao categoryDao = new CategoryDaoImpl();
		cats = categoryDao.findByParentId(pid);
		return "success";
	}
}
