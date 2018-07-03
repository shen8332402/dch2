package com.digitalchina.frame.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author shentt
 * @date 2018年4月10日
 * @className AccessFilter.java
 * @param 
 * @Description 跨域访问filter
 */
public class AccessFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2=(HttpServletRequest)request;
		HttpServletResponse response2=(HttpServletResponse)response;
		response2.addHeader("Access-Control-Allow-Origin","*");
		response2.addHeader("Access-Control-Allow-Methods", "*");
		chain.doFilter(request2, response2);
	}

	@Override
	public void destroy() {
	}

}
