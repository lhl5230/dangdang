package com.tarena.action.order;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.tarena.action.BaseAction;
import com.tarena.dao.OrderDao;
import com.tarena.dao.impl.OrderDaoImpl;
import com.tarena.pojo.Item;
import com.tarena.pojo.Order;
import com.tarena.pojo.ReceiveAddress;
import com.tarena.pojo.User;
import com.tarena.service.CartItem;
import com.tarena.service.CartService;
import com.tarena.util.CartFactory;
import com.tarena.util.Constant;
import com.tarena.util.CookieUtil;

/*
 * 确认订单action
 */
public class AffirmOrderAction extends BaseAction implements ModelDriven{
	//input
	private ReceiveAddress address = new ReceiveAddress();
	//output
	
	public String execute(){
		try {
			CartService cart = CartFactory.getInstance(session,request);
			List<CartItem> buyList = cart.buyList();
			User user = (User) session.get(Constant.USER);
			//增加一条订单信息
			OrderDao orderDao = new OrderDaoImpl();			
			Order order = new Order();
			order.setUserId(user.getId());
			order.setStatus(Constant.NEED);
			order.setOrderTime(System.currentTimeMillis());
			order.setTotalPrice(cart.cost());
			//从表单获取的信息

			order.setReceiveName(address.getReceiveName());
			order.setFullAddress(address.getFullAddress());
			order.setPostalCode(address.getPostalCode());
			order.setMobile(address.getMobile());
			order.setPhone(address.getPhone());
			//或许的收货信息，待填写了收货信息后再更新
			orderDao.add(order);
			//把购物的条目都写入到数据库item表中

			for(CartItem c : buyList){			
				Item item = new Item();
				item.setOrderId(order.getId());
				item.setPro(c.getProduct());
				item.setProductName(c.getProduct().getProductName());
				item.setDangPrice(c.getProduct().getDangPrice());
				item.setProductNum(c.getQty());
				item.setAmount(c.getQty() * c.getProduct().getDangPrice());
				orderDao.addItem(item);
			}	
			//把订单写入到session中，好在order_ok页面获取相应的数据
			session.put(Constant.ORDER, order);
			//写入地址到数据库中	
			address.setUserId(user.getId());
			
			if(address.getId()==0){
				orderDao.saveAddr(address);
			}else{
				orderDao.addressUpdate(address);
			}
			//清空购物车
			session.remove(Constant.CART);
			CookieUtil.deleteCookie(Constant.CART, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "success";
	}
	//模型注入
	public Object getModel() {
		// TODO Auto-generated method stub
		return address;
	}
	
	
	
}
