package com.digitalchina.springmvcdemo;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor1 extends HandlerInterceptorAdapter{
	 	@Override  
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
	        System.out.println("===========HandlerInterceptor1 preHandle");  
	        return true;  
	    }  
	    @Override  
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    	Map<String, Object> map=modelAndView.getModel();
	    	modelAndView.getModel().put("asada", "ashf");
	        System.out.println("===========HandlerInterceptor1 postHandle");  
	    }  
	    @Override  
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
	        System.out.println("===========HandlerInterceptor1 afterCompletion");  
	    }  
}