package com.tarena.test;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tarena.action.BaseAction;
import com.tarena.pojo.Item;
import com.tarena.service.CartService;
import com.tarena.service.impl.CartServiceImpl;
import com.tarena.util.CartFactory;
import com.tarena.util.Constant;
import com.tarena.util.CookieUtil;

public class CartServiceTest extends BaseAction{
	
	@org.junit.Test
	public void testCart() throws UnsupportedEncodingException{
		CartService cartService = CartFactory.getInstance(session,request);
		String str = CookieUtil.findCookie(Constant.CART, request);
		System.out.println(str);
	}
	

}



