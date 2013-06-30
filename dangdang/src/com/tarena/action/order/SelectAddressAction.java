package com.tarena.action.order;


import com.tarena.dao.OrderDao;
import com.tarena.dao.impl.OrderDaoImpl;
import com.tarena.pojo.ReceiveAddress;

public class SelectAddressAction {
	//input
	private int id;
	//output
	private ReceiveAddress address;

	public String execute(){
		OrderDao orderDao = new OrderDaoImpl();
		address = orderDao.findAddr(id);
		return "success";
	}
	
	public ReceiveAddress getAddress() {
		return address;
	}

	public void setAddress(ReceiveAddress address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
