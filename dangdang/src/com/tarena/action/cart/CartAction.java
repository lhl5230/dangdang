package com.tarena.action.cart;

import com.tarena.service.CartService;
import com.tarena.service.impl.CartServiceImpl;

public class CartAction {
	//input
	private int id;
	//output
	private boolean ok;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String execute(){
		CartService cartService = new CartServiceImpl();
		ok = cartService.add(id);
		
		return "success";
	}
}
