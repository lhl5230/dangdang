package com.tarena.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.pojo.User;
import com.tarena.util.Constant;

/*
 * 此拦截器判断用户是否登陆
 */
public class LoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation in) throws Exception {
		ActionContext context = in.getInvocationContext();
		//获取session
		Map<String, Object> session = context.getSession();
		User user = (User) session.get(Constant.USER);
		if(user == null){
			//获取HttpServletRequest对象
			HttpServletRequest request = ServletActionContext.getRequest();
			//获取请求地址
			String path = request.getServletPath();
			System.out.println(path);
			//获得请求中的参数
			String queryString = request.getQueryString();
			System.out.println(queryString);
			if(queryString == null){
				queryString = "";
			}
			// 拼凑得到登陆之前的地址
			String realPath = path + "?" + queryString;
			System.out.println(realPath);
			 // 存入session，方便调用
			session.put(Constant.PREPAGE, realPath);
			return "login";
		}else{
			String result = in.invoke();
			return result;
		}
	}

}
