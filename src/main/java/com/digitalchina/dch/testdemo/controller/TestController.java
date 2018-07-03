package com.digitalchina.dch.testdemo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
@RestController
public class TestController  {
	@RequestMapping("/test")
	public Map<String, Object> handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 System.out.println("===========TestController");
		 ModelAndView modelAndView=new ModelAndView();
		 Map<String,Object> map=new HashMap<>();
		 map.put("aaa", 111);
		 map.put("bbb", "xczx");
		 map.put("ccc", 222);
		 modelAndView.addObject("map", map);
		return map;
	}

}
