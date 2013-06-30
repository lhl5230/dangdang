package com.tarena.util;

import java.util.UUID;

/*
 * 生成邮箱验证码
 */
public class VerifyCodeUtil {
	public static String generatorCode(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
