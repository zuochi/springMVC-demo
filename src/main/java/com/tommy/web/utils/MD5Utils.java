package com.tommy.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

/**
 * 密文处理帮助内
 * @author niexiaolong
 *
 */
public class MD5Utils {	
	
	public static String environment_main = System.getenv("ENVIRONMENT_MAIN");
	
	@Value("${ADMIN_API_SECRET}")
	public static String ADMIN_API_SECRET;
	
	/**
	 * 将指定字符串进行MD5加密
	 */
	public static String getMd5(String input){
		byte[] out = {};
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes("UTF-8"));
			out = md.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return toHexString(out);
	}
	
	/**
	 * 转换成16进制
	 */
	private static String toHexString(byte[] out) {
		StringBuffer buf = new StringBuffer();
		for(byte b:out){
			buf.append(String.format("%02X", b));
		}
		return buf.toString();
	}
	
	/**
	 * 外部请求参数，生成tour域所需要的密文 新验签规则
	 * @param map
	 * @return
	 */
	public static String getAdminSign(Map<String, String> map){
		
		Object[]  keys = map.keySet().toArray();
		Arrays.sort(keys);
		StringBuilder str = new StringBuilder();
		for(Object o : keys){
			String key = String.valueOf(o);
			String value = map.get(key) + "";
			str.append(key + value);
		}
		String info =  str.toString() + ADMIN_API_SECRET;
		info = StringUs.stripslashes(info);
		String secret = getMd5(info).toUpperCase();
		return secret;
	}
}