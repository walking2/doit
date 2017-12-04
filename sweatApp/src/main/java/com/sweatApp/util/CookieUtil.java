package com.sweatApp.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * @author lha
 * 对cookie进行操作
 */
public class CookieUtil {
	
	private static int maxAge=0;
	
	private static String path="";
	
	private static String domain="";
	
	static{
	    String cookieProperties="/cookie.properties";
		InputStream ins=CookieUtil.class.getResourceAsStream(cookieProperties);  
		Properties p = new Properties(); 
		try {
			p.load(ins);
			maxAge=Integer.parseInt(p.getProperty("maxAge"));
			path=p.getProperty("path");
			domain=p.getProperty("domain");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param value 存放在cookie的 key
	 * @return 返回cookie存放的值 解密后
	 */
	public static String getCookieValue(HttpServletRequest request, String value){
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (value.equals(cookie.getName())) {
					return Base64Util.decodeBase64(cookie.getValue());
				}
			}
		}
		return "";
	}
	
	/**
	 * 
	 * @param request
	 * @param value 需要查询的cookie的 key
	 * @return 返回key对应的 cookie
	 */
	public static Cookie getCookie(HttpServletRequest request, String value) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie != null && value.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param response
	 * @param name  存放cookie的key
	 * @param value 存放cookie的value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
			if(StringUtils.isNotEmpty(domain)){
				if(domain.contains(",")){
					String[] str=domain.split(",");
					for (String string : str) {
						Cookie cookie = createCookie(name, value);
						cookie.setDomain(string);
						response.addCookie(cookie);
					}
				}else{
					Cookie cookie = createCookie(name, value);
					cookie.setDomain(domain);
					response.addCookie(cookie);
				}
			}else{
				Cookie cookie = createCookie(name, value);
				response.addCookie(cookie);
			}
	}
	
	
	
	private static Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, Base64Util.encodeBase64(value));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	
	/**
	 * 
	 * @param key 删除cookie
	 * 删除cookie
	 */
	public static void removeCookie(String key){
		Cookie cookie = new Cookie(key, null);
		cookie.setMaxAge(0);

	}
	
	
	
}
