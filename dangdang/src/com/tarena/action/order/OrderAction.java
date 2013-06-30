package com.tarena.action.order;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.tarena.action.BaseAction;
import com.tarena.service.CartItem;
import com.tarena.service.CartService;
import com.tarena.util.CartFactory;

/*
 * 确认订单
 */
public class OrderAction extends BaseAction{
	//input
	
	//output
	private List<CartItem> buyList;
	private double amount;//总金额
	public String execute(){
		CartService cart;
		try {
			cart = CartFactory.getInstance(session,request);
			buyList = cart.buyList();
			amount = cart.cost();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}

	public List<CartItem> getBuyList() {
		return buyList;
	}

	public void setBuyList(List<CartItem> buyList) {
		this.buyList = buyList;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
