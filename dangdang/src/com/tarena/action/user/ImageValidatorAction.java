package com.tarena.action.user;

import com.tarena.action.BaseAction;

/*
 * 判断验证码是否正确
 */
public class ImageValidatorAction extends BaseAction{
	//input
	private String imageString = "";
	//output json
	private boolean ok = false;
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String execute() throws Exception{
		String imageStr = (String)request.getSession().getAttribute("randString"); 
		if(imageStr.equalsIgnoreCase(imageString)){
			ok = true;
		}
		return "success";
	}
	
}
