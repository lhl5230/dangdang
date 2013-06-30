package com.tarena.action.cart;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.tarena.action.BaseAction;
import com.tarena.pojo.User;
import com.tarena.service.CartItem;
import com.tarena.service.CartService;
import com.tarena.service.impl.CartServiceImpl;
import com.tarena.util.CartFactory;
import com.tarena.util.Constant;
import com.tarena.util.CookieUtil;

public class CartListAction extends BaseAction{
	//input
	private int pid;
	private int count;//修改产品的数量
	
	//output
	private List<CartItem> buyList;
	private List<CartItem> delList;
	private double amount;//总金额
	private double retrenchCost;//减少的价格
	//获得用户登陆前和登陆后的cartItem集合
	public List<CartItem> getAllCartItem(){
		User user = (User) session.get(Constant.USER);
		List<CartItem> cartItems=new ArrayList<CartItem>();
		try {
			if(user == null){
				CartService cart1 = CartFactory.getInstance(session,request);
				cartItems.addAll(cart1.getItems());
			}else{
				CartService cart1 = CartFactory.getInstance(session,request);
				CartService cart2 = CartFactory.getInstance(session,request,user);
				
				cartItems.addAll(cart1.getItems());
				cartItems.addAll(cart2.getItems());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItems;
		
	}
	//显示购物车列表
	public String list(){
		//CartService cart = null;
		try {
			/*
			User user = (User) session.get(Constant.USER);
			if(user==null){
				cart = CartFactory.getInstance(session,request);
				buyList = cart.buyList();
				delList = cart.delList();
				amount = cart.cost();
				retrenchCost = cart.retrenchCost();	
			}else{
				CartServiceImpl cart1 = new CartServiceImpl();
				List<CartItem> items = getAllCartItem();
				//登陆后的购物车，为登陆前的购物车加上登陆后的购物车
				cart1.items = items;
				buyList = cart1.buyList();
				delList = cart1.delList();
				amount = cart1.cost();
				retrenchCost = cart1.retrenchCost();	
				
			}
			*/
			CartServiceImpl cart = new CartServiceImpl();
			List<CartItem> items = getAllCartItem();
			cart.items = items;
			buyList = cart.buyList();
			delList = cart.delList();
			amount = cart.cost();
			retrenchCost = cart.retrenchCost();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//删除购买的商品
	public String delete(){
		//CartService cart;
		try {
			CartServiceImpl cart = new CartServiceImpl();
			List<CartItem> items = getAllCartItem();
			cart.items = items;
			cart.delete(pid);
			User user = (User) session.get(Constant.USER);
			if(user == null){
				CookieUtil.addCookie(Constant.CART, cart.store(), response);
			}else{
				CookieUtil.addCookie(Constant.CART+"_"+user.getId(), cart.store(), response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//恢复商品
	public String recover(){
		try {
			CartServiceImpl cart = new CartServiceImpl();
			List<CartItem> items = getAllCartItem();
			cart.items = items;
			cart.recover(pid);
			User user = (User) session.get(Constant.USER);
			if(user == null){
				CookieUtil.addCookie(Constant.CART, cart.store(), response);
			}else{
				CookieUtil.addCookie(Constant.CART+"_"+user.getId(), cart.store(), response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//修改商品
	public String modify(){
		try {
			CartServiceImpl cart = new CartServiceImpl();
			List<CartItem> items = getAllCartItem();
			cart.items = items;
			if(count<=0){ //如果修改的数字小于等于0，数量就还为原来的值
				//根据pid找到产品，然后找到产品的数量
				count = cart.findById(pid).getQty();
			}
			cart.modify(pid, count);
			User user = (User) session.get(Constant.USER);
			if(user == null){
				CookieUtil.addCookie(Constant.CART, cart.store(), response);
			}else{
				CookieUtil.addCookie(Constant.CART+"_"+user.getId(), cart.store(), response);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//清空购物车
	public String clear(){
		try {
			CartServiceImpl cart = new CartServiceImpl();
			List<CartItem> items = getAllCartItem();
			cart.items = items;
			cart.clear();
			User user = (User) session.get(Constant.USER);
			if(user == null){
				CookieUtil.addCookie(Constant.CART, cart.store(), response);
				CookieUtil.deleteCookie(Constant.CART, response);
			}else{
				CookieUtil.addCookie(Constant.CART+"_"+user.getId(), cart.store(), response);
				CookieUtil.deleteCookie(Constant.CART+"_"+user.getId(), response);
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public List<CartItem> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<CartItem> buyList) {
		this.buyList = buyList;
	}
	public List<CartItem> getDelList() {
		return delList;
	}
	public void setDelList(List<CartItem> delList) {
		this.delList = delList;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRetrenchCost() {
		return retrenchCost;
	}
	public void setRetrenchCost(double retrenchCost) {
		this.retrenchCost = retrenchCost;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
