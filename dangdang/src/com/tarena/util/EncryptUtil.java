package com.tarena.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/*
 * 用来加密数据
 */
public class EncryptUtil {
	/*
	 * str : 要加密的数据
	 * algorithm : 加密算法
	 * return 加密后的数据
	 */
	private static String encryptCode(String str,String algorithm) throws NoSuchAlgorithmException{
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		byte[] bts = digest.digest(str.getBytes());
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(bts);
	}
	public static String encryptMD5(String str){
		try {
			String val =  encryptCode(str, "MD5");
			return val;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String encryptSHA(String str){
		try {
			String val =  encryptCode(str, "SHA");
			return val;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] args) {
		System.out.println(encryptMD5("aaa"));
		System.out.println(encryptSHA("aaa"));
	}
}
