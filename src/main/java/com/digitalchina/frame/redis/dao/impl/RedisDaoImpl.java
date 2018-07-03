package com.digitalchina.frame.redis.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.digitalchina.frame.redis.dao.RedisDao;
/**
 * 
 * 
 * @author shentt
 * @date 2018年4月13日
 * @className RedisDaoImpl.java
 * @param 
 * @Description redis的dao服务实现
 */
public class RedisDaoImpl implements RedisDao {
	private RedisTemplate redisTemplate;
	private Logger logger=LoggerFactory.getLogger(RedisDaoImpl.class);
	
	@Override
	public void addStringKeyValue(String key, String value) {
		try {
			ValueOperations<String, Object> valueOpt=redisTemplate.opsForValue();
			valueOpt.set(key, value);
			logger.info("添加redis string数据成功==key:"+key+"===value:"+value);
		} catch (Exception e) {
			logger.error("添加redis string数据失败==key:"+key+"===value:"+value,e);
		}
	}

	@Override
	public String getStringKeyValue(String key) {
		String value="";
		try {
			ValueOperations<String, Object> valueOpt=redisTemplate.opsForValue();
			value=(String) valueOpt.get(key);
			logger.info("查询redis string数据成功==key:"+key+"===value:"+value);
		} catch (Exception e) {
			logger.error("查询redis string数据失败==key:"+key+"===value:"+value,e);
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteKeyValue(String key,String keyType) {
		try {
			if(redisTemplate.hasKey(key)&&redisTemplate.type(keyType)==keyTypeToDataType(keyType)){
				redisTemplate.delete(key);
				logger.info("删除"+keyType+"类型的"+key+"成功");
			}
		} catch (Exception e) {
			logger.info("删除"+keyType+"类型的"+key+"失败",e);
		}
	}

	private DataType keyTypeToDataType(String keyType){
		DataType dataType=DataType.NONE;
		switch (keyType) {
		case "hash":
			dataType=DataType.HASH;
			break;
		case "string":
			dataType=DataType.STRING;
			break;
		case "list":
			dataType=DataType.LIST;
			break;
		case "set":
			dataType=DataType.SET;
			break;
		case "zset":
			dataType=DataType.ZSET;
			break;
		default:
			break;
		}
		return dataType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addHashKeyFieldValue(String key, String field, String value) {
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			if (!redisTemplate.hasKey(key)) {
				logger.info("不存在该key");
			}
			 else if(redisTemplate.type(key)==DataType.HASH){
				hashOpt.put(key, field, value);
				logger.info("添加redis hashmap数据成功==key:"+key+"=====field"+field+"===value:"+value);
			}else {
				logger.info("存在该key,但该key不是hashmap类型");
			}
		} catch (Exception e) {
			logger.info("添加redis hashmap数据失败==key:"+key+"=====field"+field+"===value:"+value,e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addHashKeyFieldsValues(String key, Map<String, String> fieldToValue) {
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			if (!redisTemplate.hasKey(key)) {
				logger.info("不存在该key");
			}
			else if(redisTemplate.type(key)==DataType.HASH){//有问题
				hashOpt.putAll(key, fieldToValue);
				logger.info("添加redis hashmap数据成功==fieldToValue:"+fieldToValue);
			}else {
				logger.info("存在该key,但是该key不是hashmap类型");
			}
		} catch (Exception e) {
			logger.info("添加redis hashmap数据失败==fieldToValue:"+fieldToValue,e);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public Map getHashKeyMap(String key, List<String> fields, boolean isAll) {
		Map<String,Object> fieldValue=new HashMap<>();
		List<Object> fList=new ArrayList<>();
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			if (isAll) {//获取全部keyvalue
				fieldValue=hashOpt.entries(key);	
			}else {
				fList=hashOpt.multiGet(key, fields);
				for (int i = 0,h=fields.size(); i < h; i++) {
					fieldValue.put(fields.get(i), fList.get(i));
				}
			}
		} catch (Exception e) {
			logger.info("获取redis hashmap数据失败==key:"+key,e);
		}
		return fieldValue;
	}

	@Override
	public String getHashValueByKeyAndField(String hashKey, String field) {
		String result="";
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			result=(String) hashOpt.get(hashKey, field);
			logger.info("获取redis hashmap数据成功==key:"+hashKey+"=========field"+field);
		} catch (Exception e) {
			logger.info("获取redis hashmap数据失败==key:"+hashKey+"=========field"+field);
		}
		return result;
	}

	@Override
	public boolean isHashExist(String key, String field) {
		boolean result=false;
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			result=hashOpt.hasKey(key, field);
			logger.info("redis hashmap中存在==key:"+key+"=========field"+field);
		} catch (Exception e) {
			logger.info("redis hashmap中不存在==key:"+key+"=========field"+field);
			result=false;
		}
		return result;
	}

	@Override
	public Long getHashLength(String key) {
		Long result = null;
		try {
			HashOperations<String, String, Object> hashOpt=redisTemplate.opsForHash();
			result=hashOpt.size(key);
			logger.info("redis hashmap中的key："+key+"有"+result+"个键值对");
		} catch (Exception e) {
			logger.info("redis hashmap查询key："+key+"中键值对数量失败");
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Long setSetKeyValue(String key, String [] values) {
		Long setSetCount=new Long(0);
		try {
			SetOperations<String, String> setOpt=redisTemplate.opsForSet();
			if (!redisTemplate.hasKey(key)) {
				logger.info("添加set类型数据失败---key:"+key+",不存在该key");
			} 
			else if(redisTemplate.type(key)==DataType.SET){
				for (int i = 0,vLength=values.length; i < vLength; i++) {
					setOpt.add(key,values[i]);
					setSetCount++;
				}
				logger.info("添加set类型数据成功---key:"+key+",一共"+setSetCount+"个");
			}else {
				logger.info("添加set类型数据失败---key:"+key+",存在该key,但该key不是set类型");
			}
		} catch (Exception e) {
			logger.info("添加set类型数据失败---key:"+key,e);
		}
		return setSetCount;
	}
	@Override
	public Set<String> getSetKeyValues(String key) {
		Set<String> resultSet=new HashSet<>();
		try {
			SetOperations<String, String> setOpt=redisTemplate.opsForSet();
			resultSet=setOpt.members(key);
			logger.info("查询set类型数据成功---key:"+key);
		} catch (Exception e) {
			logger.info("查询set类型数据失败---key:"+key,e);
		}
		return resultSet;
	}

	@Override
	public Long getSetLength(String key) {
		Long setLength=null;
		try {
			SetOperations<String, String> setOpt=redisTemplate.opsForSet();
			setLength=setOpt.size(key);
			logger.info("查询set类型数据数量成功---key:"+key+"---------length:"+setLength);
		} catch (Exception e) {
			logger.info("查询set类型数据数量失败---key:"+key+"---------length:"+setLength,e);
		}
		return setLength;
	}

	@Override
	public Boolean isSetMember(String key, String value) {
		Boolean result=null;
		try {
			SetOperations<String, String> setOpt=redisTemplate.opsForSet();
			result=setOpt.isMember(key, value);
			if (result) {
				logger.info("key为"+key+" set集合中存在value为"+value+"的数据");
			} else {
				logger.info("key为"+key+" set集合中不存在value为"+value+"的数据");
			}
		} catch (Exception e) {
			logger.info("查询set类型数据失败---key:"+key,e);
			result=false;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long removeSetMember(String key, String[] values) {
		Long setSetCount=new Long(0);
		try {
			SetOperations<String, String> setOpt=redisTemplate.opsForSet();
			for (int i = 0,sLength=values.length; i <sLength ; i++) {
				setOpt.remove(key, values[i]);
				setSetCount++;
			}
			logger.info("删除set类型数据成功---key:"+key+",一共"+setSetCount+"个");
		} catch (Exception e) {
			logger.info("删除set类型数据失败---key:"+key+",一共"+setSetCount+"个",e);
		}
		return setSetCount;
	}

	@Override
	public Long addListValues(String key, String[] values, String headOrEnd) {
		Long setListCount=new Long(0);
		try {
			ListOperations<String, String> listOpt=redisTemplate.opsForList();
			if (headOrEnd.equals("0")) {
				//setListCount=listOpt.leftPushAll(key, values);//这里需要判断key是否存在
				for (int i = 0; i < values.length; i++) {
					listOpt.leftPushIfPresent(key, values[i]);
					setListCount++;
				}
				logger.info("头部：添加list类型数据成功---key:"+key+",一共"+setListCount+"个");
			} else {
				//setListCount=listOpt.rightPushAll(key, values);
				for (int i = 0; i < values.length; i++) {
					listOpt.rightPushIfPresent(key, values[i]);
					setListCount++;
				}
				logger.info("尾部部：添加list类型数据成功---key:"+key+",一共"+setListCount+"个");
			}
		} catch (Exception e) {
			logger.info("添加list类型数据失败---key:"+key,e);
		}
		return setListCount;
	}
	@Override
	public List<String> getListValues(String key, Long start, Long end) {
		List<String> resultList=new ArrayList<>();
		try {
			ListOperations<String, String> listOpt=redisTemplate.opsForList();
			resultList=listOpt.range(key, start, end);
			logger.info("获取list类型数据成功---key:"+key+",一共"+resultList.size()+"个");
		} catch (Exception e) {
			logger.info("获取list类型数据失败---key:"+key,e);
		}
		return resultList;
	}

	@Override
	public String popListValueHeadOrEnd(String key, String headOrEnd) {
		String resultValue="";
		try {
			ListOperations<String, String> listOpt=redisTemplate.opsForList();
			if (headOrEnd.equals("0")) {
				resultValue=listOpt.leftPop(key, 5, TimeUnit.SECONDS);//list的pop操作，如果list没有数据则会阻塞，这里设置阻塞时间为5秒
				if (resultValue.equals("")) {
					logger.info("头 pop list类型数据失败---key:"+key+"---value:"+resultValue+"list中无数据");
				} else {
					logger.info("头 pop list类型数据成功---key:"+key+"---value:"+resultValue);
				}
			} else {
				resultValue=listOpt.rightPop(key, 5, TimeUnit.SECONDS);
				if (resultValue.equals("")) {
					logger.info("尾 pop list类型数据失败---key:"+key+"---value:"+resultValue+"list中无数据");
				} else {
					logger.info("尾 pop list类型数据成功---key:"+key+"---value:"+resultValue);
				}
			}
		} catch (Exception e) {
			logger.info("pop list数据失败",e);
		}
		return resultValue;
	}
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
