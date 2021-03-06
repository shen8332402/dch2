package com.digitalchina.frame.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 页面请求的工具类
 * @author lvhuic
 *
 */
public class HttpRequestUtil {
	/**
	 * 解析请求参数,如果数组解析成用逗号分隔字符串
	 * @param request HttpServletRequest
	 * @return Map&lt;String,String&gt;
	 */
	public static Map<String,String> getParameter(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
//		try {
			
			Enumeration<String> e = request.getParameterNames();
			while(e.hasMoreElements()){
				String key = e.nextElement();
				String[] list = request.getParameterValues(key);
				if(list.length==1){
					//String tempVal= list[0];				
					//String string=new String(tempVal.getBytes("iso8859-1"),"utf-8");					
					map.put(key, list[0]);
				}else{
					if(list.length>1){
						String value = null;
						for (int i = 0; i < list.length; i++) {
							//String tempVal= list[i];
							
							//	String string=new String(tempVal.getBytes("iso8859-1"),"utf-8");
		
							if(i==0){
								value = list[i];
							}
							else{
								value = value +',' + list[i];
							} 
						}
						map.put(key, value);
						System.out.println("KEY"+key+"---VALUE--"+value);
					}
				} 
			}
//		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		
//		}
		return map;
	}
	public static Map<String,String> getParameter(HttpServletRequest request,String beanName){
		Map<String,String> map = new HashMap<String,String>();
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			if(key.indexOf(beanName+".")>-1){
				String tempKey=key.substring(beanName.length()+1);
				
				String[] list = request.getParameterValues(key);
				if(list.length==1) map.put(tempKey, list[0]);
				else if(list.length>1){
					String value = null;
					for (int i = 0; i < list.length; i++) {
						if(i==0) value = list[i];
						else value = value +',' + list[i];
					}
					map.put(tempKey, value);
					System.out.println("KEY"+tempKey+"---VALUE--"+value);
				}				
			}

			
		}
		return map;
	}	
	public static void saveCookie(HttpServletResponse response,String key,String value,String path){
		Cookie cookie = new Cookie(key,value);
		cookie.setMaxAge(-1);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	public static String getCookie(HttpServletRequest request,String cookieName){
    	String value = "";
    	Cookie[] cookies = request.getCookies();
    	if(cookies!=null){
    		for (int i = 0; i < cookies.length; i++){
    			Cookie tempCookie = cookies[i];
    			if(tempCookie.getName().equalsIgnoreCase(cookieName)){
    				value = tempCookie.getValue();
    				break;
    			}
    		}
    	}
       return value;
	}
}
