package com.tarena.action.cart;

import com.tarena.action.BaseAction;
import com.tarena.pojo.User;
import com.tarena.service.CartService;
import com.tarena.service.impl.CartServiceImpl;
import com.tarena.util.CartFactory;
import com.tarena.util.Constant;
import com.tarena.util.CookieUtil;

public class BuyAction extends BaseAction{
	//input
	private int pid;
	//output
	private boolean ok;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String execute(){
		try {
			User user = (User) session.get(Constant.USER);
			CartService cart = (CartServiceImpl)CartFactory.getInstance(session, request);
			CartService cart2 = CartFactory.getInstance(session,request,user);
			if(user == null){
				ok = cart.add(pid);				
				if(ok){//如果购买成功,用户没有登陆，将cart的数据存入到cookie中, "3,8;2,10;4,5"(产品id,购买的数量)					
					CookieUtil.addCookie(Constant.CART, cart.store(), response);					
				}
			}else{
				ok = cart2.add(pid);
				if(ok){
					CookieUtil.addCookie(Constant.CART + "_" + user.getId(), cart.store(), response);
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
}
