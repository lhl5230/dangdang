package com.tarena.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.pojo.User;
import com.tarena.util.Constant;

/*
 * 此拦截器判断用户是否登陆
 */
public class OrderInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation arg0) throws Exception {
		//
		User user = (User) arg0.getInvocationContext().getSession().get(Constant.USER);
		if(user == null){
			return "login";
		}else{
			String result = arg0.invoke();
			return result;
		}
	}

}
