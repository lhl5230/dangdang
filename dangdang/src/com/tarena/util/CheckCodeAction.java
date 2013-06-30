/*package com.tarena.util;

 * 随机验证码
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import com.tarena.action.BaseAction;

public class CheckCodeAction extends BaseAction{
	public string execute(){
		//第一步，生成一张图片
		//1,创建一个内存映像对象
		BufferedImage image = new BufferedImage(60,20,BufferedImage.TYPE_INT_RGB);
		//2,获得画笔
		Graphics g = image.getGraphics();
		//3,给笔上颜色(随机的一个颜色)
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),
				r.nextInt(255),r.nextInt(255)));
		//4,设置背景颜色
		g.fillRect(0, 0, 60, 20);
		//5,绘图
		//String number = r.nextInt(99999) + "";
		String number = getNumber(5);
		//将随机数绑订到session对象上
		session.put("number", number);
		g.setColor(new Color(0,0,0));
		g.drawString(number, 10, 15);
		//加干扰线
		for(int i=0;i<5;i++){
			g.drawLine(r.nextInt(60), r.nextInt(20),r.nextInt(60), r.nextInt(20));
		}
				
	//第二步，将图片压缩，并输出到客户端
		//1,设置正确的消息头，告诉浏览器返回的
		//是图片。
		response.setContentType("image/jpeg");
		//2,获得一个字节输出流。
		OutputStream ops = response.getOutputStream();
		//3,压缩并输出
		javax.imageio.ImageIO.write(image, "jpeg", ops);
		
}
//返回一个一个长度为size的字符串，并且
//字符串中的字符必须从"A~Z,0~9"中随机选取。
	private String getNumber(int size) {
		String str = "ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		String str2 = "";
		Random r = new Random();
		for(int i=0;i<size;i++){
			str2 += str.charAt(r.nextInt(str.length()));
		}
		return str2;
	}
}
*/