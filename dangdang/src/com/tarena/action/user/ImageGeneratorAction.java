package com.tarena.action.user;

import java.io.ByteArrayInputStream;

import com.tarena.action.BaseAction;
import com.tarena.util.ImageGenerator;
/*
 * 创建图片验证码
 */
public class ImageGeneratorAction extends BaseAction{
	//input ,图片流
	private ByteArrayInputStream imageSteam;
	//output
	private String randString;
	
	public String getRandString() {
		return randString;
	}

	public void setRandString(String randString) {
		this.randString = randString;
	}

	public ByteArrayInputStream getImageSteam() {
		return imageSteam;
	}

	public void setImageSteam(ByteArrayInputStream imageSteam) {
		this.imageSteam = imageSteam;
	}
	
	public String execute() throws Exception{
		ImageGenerator generator = new ImageGenerator();
		imageSteam = generator.getImageAsInputStream();
		//随机字符串
		randString  = generator.getRandString();
		session.put("randString", randString);
		return "success";
	}
}
