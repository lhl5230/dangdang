package com.tarena.action.main;

import java.util.List;

import com.tarena.action.BaseAction;
import com.tarena.dao.CategoryDao;
import com.tarena.dao.ProductDao;
import com.tarena.dao.impl.CategoryDaoImpl;
import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.pojo.Category;
import com.tarena.pojo.Product;

public class BookListAction extends BaseAction{
	//input 
	private int pid;//父类别id
	private int catId;//子类别id
	private int currentPage = 1;//当前的页面，默认为1
	private int pageSize = 5;//每页默认显示个数
	private String sort = "addTime";//排序方式,默认按上架时间
	//output
	private List<Category> cats;//左侧类别集合
	private List<Product> pros;//中间产品信息集合
	private int totalNum;//当前类别下所有子类别的产品数量合计
	private int totalPage = 1;//一共有多少页
	private Category category; //当前的类别
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getTatolPage() {
		return totalPage;
	}

	public void setTatolPage(int tatolPage) {
		this.totalPage = tatolPage;
	}

	public String execute(){
		CategoryDao categoryDao = new CategoryDaoImpl();
		//根据父类别ID查找子类别
		cats = categoryDao.findByParentId(pid);
		for(Category c : cats){
			totalNum += c.getPnum();
			//根据总共的产品数量来判断一共有多少页数据
			if(c.getId() == catId){
				category = c;
				totalPage = (c.getPnum()%pageSize == 0)?(c.getPnum() / pageSize):((c.getPnum()/pageSize)+1);
			}
		}
		//如果直接点击的二级类别，tatolPage就要更改了
		if(catId == 0){
			category = categoryDao.findById(pid);
			totalPage = (category.getPnum()%pageSize == 0)?(category.getPnum() / pageSize):((category.getPnum()/pageSize)+1);
		}

		//判断输入的当前页是否超过范围,防止用户在浏览器中手动修改输入不正确数字
		//(案例中用的是ajax请求,所以地址栏中的地址没有变化,可以省略)
		if(currentPage < 1){
			currentPage = 1;
		}else if(currentPage > totalPage){
			currentPage = totalPage;
		}
		
		ProductDao productDao = new ProductDaoImpl();
		//根据catId查找出所对应的product
		if(catId == 0){			
			pros = productDao.findByCatId(pid,currentPage,pageSize,sort);			
		}else{
			pros = productDao.findByCatId(catId,currentPage,pageSize,sort);	
		}
		//把排序方式sort存入session中
		session.put("sort", sort);
		return "success";
	}
	
	public List<Product> getPros() {
		return pros;
	}

	public void setPros(List<Product> pros) {
		this.pros = pros;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public List<Category> getCats() {
		return cats;
	}
	public void setCats(List<Category> cats) {
		this.cats = cats;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
}
