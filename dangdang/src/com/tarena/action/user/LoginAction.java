package com.tarena.action.user;

import com.tarena.action.BaseAction;
import com.tarena.pojo.User;
import com.tarena.service.UserService;
import com.tarena.service.impl.UserServiceImpl;
import com.tarena.util.Constant;
import com.tarena.util.EncryptUtil;

public class LoginAction extends BaseAction{
	//input
	private String name;
	private String password;
	
	//output
	private String error;
	private String verify;
	private String success;
	private String prePage;//登录前页面
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute(){
//		去d_user表检查Email和密码是否匹配.
		UserService userService = new UserServiceImpl();
		User user =	userService.login(name, EncryptUtil.encryptMD5(password));
//	     如果is_email_verify="N",转向到verify_form.jsp验证邮箱
		if(user == null){
			request.setAttribute("loginmsg", "用户名或密码错误");
//			session.put("loginmsg", "用户名或密码错误");
			error = "error";
			return error;
		}
		if("N".equals(user.getIsEmailVerify())){
			session.put(Constant.USER, user);
			verify = "verify";
			return verify;
		}
//	    更新last_login_time和last_login_ip
		user.setLastLoginTime(System.currentTimeMillis());
		user.setLastLoginIp(request.getRemoteAddr());
		userService.update(user);
//	     将user信息写入session
		session.put(Constant.USER, user);
//		 获取跳转到登陆界面之前的页面地址，由拦截器提供
		prePage = (String) session.get(Constant.PREPAGE);
//		清除session中的数据
		session.remove(Constant.PREPAGE);
//		不是拦截器跳转到登陆页面的，直接访问的登陆页面
//	     进入到/main/main.jsp
		if(prePage == null){
			success = "success";
			return success;
		}else{
//			跳到之前的页面
			return "prePage";
		}
	}
	public String getPrePage() {
		return prePage;
	}
	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	
}
