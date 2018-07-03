package com.digitalchina.dch.redisTest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.frame.redis.service.RedisService;

/**
 * 
 * 
 * @author shentt
 * @date 2018年4月13日
 * @className RedisTestController.java
 * @param 
 * @Description 测试redis的可用性
 */
@RestController
@Scope("prototype")
@RequestMapping("/testRedis")
public class RedisTestController {
	@Autowired
	@Qualifier(value="redisService")
	private RedisService redisService;
	
	@RequestMapping(value="add")
	public void setStringValue(@PathParam(value = "key") String key,@PathParam(value="value")String value){
		redisService.addStringKeyValue(key, value);
	}
	@RequestMapping(value="qry")
	public String getStringValue(@PathParam(value="key")String key){
		System.out.println("cccccccccc");
		return redisService.getStringKeyValue(key);
	}
	@RequestMapping(value="addKeyFieldValue")
	public void addHashKeyFieldValue(@PathParam(value = "key") String key,
									 @PathParam(value="field")String field,
									 @PathParam(value="value")String value){
		redisService.addHashKeyFieldValue(key, field, value);
	}
	@RequestMapping(value="addKeyFieldsValues")
	public void getHashValue(@PathParam(value="key")String key,
			 				   @PathParam(value="field")String field,
			 				   @PathParam(value="value")String value){
		System.out.println("cccccccccc");
		Map<String, String> fieldValue=new HashMap<>();
		fieldValue.put(field+"1", value);
		fieldValue.put(field+"2", value);
		fieldValue.put(field+"3", value);
		redisService.addHashKeyFieldsValues(key, fieldValue);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getHashKeyMap")
	public Map<String,Object> getHashKeyMap(@PathParam(value = "key") String key,
											@PathParam(value="field")String field,
											@PathParam(value="isAll")String isAll){
		Map<String,Object> resultMap=new HashMap<>();
		List<String> fields=new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			fields.add(field+Integer.toString(i+1));
		}
		resultMap=redisService.getHashKeyMap(key, fields, false);
		return resultMap;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getHashKeyMapAll")
	public Map<String,Object> getHashKeyMapAll(@PathParam(value = "key") String key,
											@PathParam(value="field")String value,
											@PathParam(value="isAll")String isAll){
		Map<String,Object> resultMap=new HashMap<>();
		resultMap=redisService.getHashKeyMap(key, new ArrayList<>(), true);
		return resultMap;
	}
	@RequestMapping(value="getHashValueByKeyAndField")
	public String getHashValueByKeyAndField(@PathParam(value="key")String key,
											@PathParam(value="field")String field){
		return redisService.getHashValueByKeyAndField(key, field);
	}
	@RequestMapping(value="isHashExist")
	public boolean isHashExist(@PathParam(value = "key") String key,
						   @PathParam(value="field")String field){
		return redisService.isHashExist(key, field);
	}
	@RequestMapping(value="getHashLength")
	public String getHashLength(@PathParam(value="key")String key){
		System.out.println("cccccccccc");
		return Long.toString(redisService.getHashLength(key));
	}
	@RequestMapping(value="setSetKeyValue")
	public Long setSetKeyValue(@PathParam(value="key")String key){
		String [] values={"a","b","c"};
		return redisService.setSetKeyValue(key, values);
	}
	@RequestMapping(value="getSetKeyValues")
	public Set<String> getSetKeyValues(@PathParam(value="key")String key){
		System.out.println("cccccccccc");
		return redisService.getSetKeyValues(key);
	}
	@RequestMapping(value="getSetLength")
	public String getSetLength(@PathParam(value="key")String key){
		System.out.println("cccccccccc");
		return Long.toString(redisService.getSetLength(key));
	}
	@RequestMapping(value="isSetMember")
	public Boolean isSetMember(@PathParam(value="key")String key,
							  @PathParam(value="value")String value){
		System.out.println("cccccccccc");
		return redisService.isSetMember(key, value);
	}
	@RequestMapping(value="removeSetMember")
	public String removeSetMember(@PathParam(value="key")String key){
		String [] values={"a","b"};
		return Long.toString(redisService.removeSetMember(key, values));
	}
	@RequestMapping(value="addListValues")
	public String addListValues(@PathParam(value="key")String key,
								@PathParam(value="headOrEnd")String headOrEnd){
		String [] values={"a","b","c"};
		return Long.toString(redisService.addListValues(key, values, headOrEnd));
	}
	@RequestMapping(value="getListValues")
	public List<String> getListValues(@PathParam(value="key")String key,
								@PathParam(value="start")String start,
								@PathParam(value="end")String end){
		return redisService.getListValues(key, Long.parseLong(start), Long.parseLong(end));
	}
	@RequestMapping(value="popListValueHeadOrEnd")
	public String popListValueHeadOrEnd(@PathParam(value="key")String key,@PathParam(value="headOrEnd")String headOrEnd){
		return redisService.popListValueHeadOrEnd(key, headOrEnd);
	}
}
