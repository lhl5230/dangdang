package com.tarena.action.order;

import java.util.List;

import com.tarena.action.BaseAction;
import com.tarena.dao.OrderDao;
import com.tarena.dao.impl.OrderDaoImpl;
import com.tarena.pojo.ReceiveAddress;
import com.tarena.pojo.User;
import com.tarena.util.Constant;

/*
 * 获取填写的地址
 */
public class LoadAddressAction extends BaseAction{
	//input
	//output
	private List<ReceiveAddress> addresses;
	
	public String execute(){
		User user = (User) session.get(Constant.USER);
		OrderDao orderDao = new OrderDaoImpl();
		addresses = orderDao.findById(user.getId());
		return "success";
	}

	public List<ReceiveAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<ReceiveAddress> addresses) {
		this.addresses = addresses;
	}
	
}
