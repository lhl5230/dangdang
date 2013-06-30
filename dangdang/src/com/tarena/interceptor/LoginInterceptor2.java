package com.tarena.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tarena.util.Constant;

/*
 * 此拦截器用来拦截主页面用户登陆，记录之前的地址
 */
public class LoginInterceptor2 extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation in) throws Exception {
		ActionContext context = in.getInvocationContext();
		//获取session
		Map<String, Object> session = context.getSession();

		// 获取HttpServletRequest对象
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得请求中的参数,保存的就是请求前的地址
		String url = request.getQueryString();
		System.out.println("拦截器拦截："+url);
		if(url!=null){
			String queryString = url.substring(10);//把项目名截取掉(长度根据项目修改)
			// 存入session，方便调用
			session.put(Constant.PREPAGE, queryString);
		}

		String result = in.invoke();
		return result;
		
	}

}
