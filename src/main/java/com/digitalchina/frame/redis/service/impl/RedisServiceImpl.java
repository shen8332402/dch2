package com.digitalchina.frame.redis.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.digitalchina.frame.redis.dao.RedisDao;
import com.digitalchina.frame.redis.service.RedisService;

/**
 * 
 * 
 * @author shentt
 * @date 2018年4月13日
 * @className RedisServiceImpl.java
 * @param 
 * @Description redis的service服务实现
 */
public class RedisServiceImpl implements RedisService{

	private RedisDao redisDao;
	
	@Override
	public void addStringKeyValue(String key, String value) {
		redisDao.addStringKeyValue(key, value);
	}

	@Override
	public String getStringKeyValue(String key) {
		return redisDao.getStringKeyValue(key);
	}

	public RedisDao getRedisDao() {
		return redisDao;
	}

	public void setRedisDao(RedisDao redisDao) {
		this.redisDao = redisDao;
	}

	@Override
	public void addHashKeyFieldValue(String key, String field, String value) {
		redisDao.addHashKeyFieldValue(key, field, value);
	}

	@Override
	public void addHashKeyFieldsValues(String key, Map<String, String> fieldToValue) {
		redisDao.addHashKeyFieldsValues(key, fieldToValue);
		
	}
	@Override
	public void deleteKeyValue(String key,String keyType){
		redisDao.deleteKeyValue(key, keyType);
	}
	@Override
	public Map getHashKeyMap(String key, List<String> fields, boolean isAll) {
		return redisDao.getHashKeyMap(key, fields, isAll);
	}

	@Override
	public String getHashValueByKeyAndField(String hashKey, String field) {
		return redisDao.getHashValueByKeyAndField(hashKey, field);
	}

	@Override
	public boolean isHashExist(String key, String field) {
		return redisDao.isHashExist(key, field);
	}

	@Override
	public Long getHashLength(String key) {
		return redisDao.getHashLength(key);
	}

	@Override
	public Long setSetKeyValue(String key, String[] values) {
		return redisDao.setSetKeyValue(key, values);
	}

	@Override
	public Set<String> getSetKeyValues(String key) {
		return redisDao.getSetKeyValues(key);
	}

	@Override
	public Long getSetLength(String key) {
		return redisDao.getSetLength(key);
	}

	@Override
	public Boolean isSetMember(String key, String value) {
		return redisDao.isSetMember(key, value);
	}

	@Override
	public Long removeSetMember(String key, String[] values) {
		return redisDao.removeSetMember(key, values);
	}

	@Override
	public Long addListValues(String key, String[] values, String headOrEnd) {
		return redisDao.addListValues(key, values, headOrEnd);
	}

	@Override
	public List<String> getListValues(String key, Long start, Long end) {
		return redisDao.getListValues(key, start, end);
	}

	@Override
	public String popListValueHeadOrEnd(String key, String headOrEnd) {
		return redisDao.popListValueHeadOrEnd(key, headOrEnd);
	}
}
