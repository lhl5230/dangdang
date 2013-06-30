package com.tarena.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.tarena.pojo.User;
import com.tarena.service.CartService;
import com.tarena.service.impl.CartServiceImpl;

public class CartFactory {
	public static CartService getInstance(Map<String, Object> session,HttpServletRequest request) throws UnsupportedEncodingException{
		CartService cart = (CartServiceImpl)session.get(Constant.CART);
		if(cart == null){
			cart = new CartServiceImpl();
			//根据用户id查找名叫cart的cookie,恢复之前购物车中的物品数据
			cart.load(CookieUtil.findCookie(Constant.CART, request));
			session.put(Constant.CART, cart);
		}
		return cart;
	}
	//用户已经登陆了
	public static CartService getInstance(Map<String, Object> session,HttpServletRequest request,User user) throws UnsupportedEncodingException{
		CartService cart = (CartServiceImpl)session.get(Constant.CART+"_"+user.getId());
		if(cart == null){
			cart = new CartServiceImpl();
			//根据用户id查找名叫cart的cookie,恢复之前购物车中的物品数据
			cart.load(CookieUtil.findCookie(Constant.CART+"_"+user.getId(), request));
			
			session.put(Constant.CART+"_"+user.getId(), cart);
		}
		return cart;
	}
}
