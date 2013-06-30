package com.tarena.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tarena.dao.impl.ProductDaoImpl;
import com.tarena.dao.ProductDao;
import com.tarena.pojo.Product;
import com.tarena.service.CartItem;
import com.tarena.service.CartService;
/*
 * 购物车实现类
 */
public class CartServiceImpl implements CartService {
	//定义一个集合，存放购物的产品
	public List<CartItem> items = new ArrayList<CartItem>();
	/*
	 * 将items中的数据，转换成一个类似 "3,8;2,10;4,5"(产品id,购买的数量)
	 * 这样的字符串,在购买的时候,再把用户的id(userId-)关联上来,这样就可以区分同一台电脑上不同用户登陆可以看到不同的购物车
	 * 如果集合为空，返回"0".
	 */
	public String store(){
		StringBuffer sb = new StringBuffer();
		//只把是购买状态的才转换
		List<CartItem> buyList = buyList();
		if(buyList.size() == 0){
			sb.append("0");
		}else{
			for(int i=0;i<buyList.size();i++){
				CartItem item = buyList.get(i);
				//购买了才store到cookie中去	
				sb.append(item.getProduct().getId()+","+item.getQty()+";");
			}
		}
		if(sb.length()>1){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	/*
	 * 依据content(类似 "userId-3,8;2,10;4,5"(产品id,购买的数量)这样的字符串)
	 * 重新恢复cart中用户所购买的商品，即items集合
	 */
	public void load(String content){
		if(content == null || content.equals("0")){
			return;
		}
		String[] pros = content.split(";");
		for(int i=0;i<pros.length;i++){
			String pro = pros[i];
			String[] strs = pro.split(",");
			int id = Integer.parseInt(strs[0]);
			int qty = Integer.parseInt(strs[1]);
			ProductDao dao = new ProductDaoImpl();
			Product p = dao.findById(id);
			CartItem item = new CartItem();
			
			item.setProduct(p);
			item.setQty(qty);
			
			items.add(item);
		}
	}
	//添加到购物车
	public boolean add(int pid) {
		//判断购物车中是否有该产品
		for(CartItem c : items){
			if(c.getProduct().getId() == pid){//购物车中已经有该产品了
				if(!c.isFlag()){   //如果是删除了的产品，重新添加到已经购买的列表中
					c.setFlag(true);
				}
				return false;  //购买失败
			}			
		}
		//查找出pid对应的产品，设置cartItem属性
		ProductDao productDao = new ProductDaoImpl();
		CartItem item = new CartItem();
		Product pro = productDao.findById(pid);
		item.setProduct(pro);
		items.add(item);
		
		return true;
	}
	//修改产品数量
	public void modify(int pid,int count) {
		for(int i=0;i<items.size();i++){
			CartItem c = items.get(i);
			if(c.getProduct().getId() == pid){
				c.setQty(count);
				items.set(i, c);
			}
		}
	}
	//删除产品
	public void delete(int pid) {
		for(CartItem c : items){
			if(c.getProduct().getId() == pid){
				c.setFlag(false);
			}
		}
	}
	//恢复产品
	public void recover(int pid) {
		for(CartItem c : items){
			if(c.getProduct().getId() == pid){
				c.setFlag(true);
			}
		}
	}
	//总金额
	public double cost() {
		double amount = 0;
		for(CartItem c : items){
			if(c.isFlag()){
				amount += c.getProduct().getDangPrice()*c.getQty();
			}
		}
		return amount;
	}
	//节省金额
	public double retrenchCost() {
		double retrenchCost = 0;
		for(CartItem c : items){
			if(c.isFlag()){
				retrenchCost += (c.getProduct().getFixedPrice()*c.getQty() - c.getProduct().getDangPrice()*c.getQty());
			}
		}
		return retrenchCost;
	}
	//购买了的商品
	public List<CartItem> buyList() {
		List<CartItem> list = new ArrayList<CartItem>();
		for(CartItem c : items){
			if(c.isFlag()){
				list.add(c);
			}
		}
		return list;
	}
	//删除了待恢复
	public List<CartItem> delList() {
		
		List<CartItem> list = new ArrayList<CartItem>();
		for(CartItem c : items){
			if(!c.isFlag()){
				list.add(c);
			}
		}
		return list;
	}
	//根据产品id找到购物条目
	public CartItem findById(int pid) {
		CartItem item = null;
		for(CartItem c : items){
			if(c.getProduct().getId() == pid){
				item = c;
			}
		}
		return item;
	}
	//清空购物车
	public void clear() {
		for(CartItem c: items){
			if(c.isFlag()){
				c.setFlag(false);
			}
		}
	}
	public List<CartItem> getItems() {
		return items;
	}
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	
}
