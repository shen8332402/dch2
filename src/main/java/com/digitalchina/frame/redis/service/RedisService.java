package com.digitalchina.frame.redis.service;
import java.util.List;
/**
 * 
 * 
 * @author shentt
 * @date 2018年4月13日
 * @className RedisService.java
 * @param 
 * @Description redis的service服务接口
 */
import java.util.Map;
import java.util.Set;
public interface RedisService {
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月13日
	 * @param key value
	 * @Description 向redis添加String键值对
	 */
	public void addStringKeyValue(String key,String value);
	/**
	 * 
	 * 
	 * @author shentt
	 * @date 2018年4月13日
	 * @param key 
	 * @Description 获取redis中string类型键值对
	 */
	public String getStringKeyValue(String key);
	/**
	 * 
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param key删除的key
	 * @param keyType 删除type类型的key
	 * @Description 删除redis中类型的key-value
	 */
	public void deleteKeyValue(String key,String keyType);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param key field value
	 * @Description 向redis中添加hashMap键值对
	 */
	public void addHashKeyFieldValue(String key,String field,String value);
	/**
	 * 
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 向redis中添加多个hashmap键值对
	 */
	public void addHashKeyFieldsValues(String key,Map<String, String> fieldToValue);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param key
	 * @param fields获取哪些field的值
	 * @param isAll 是否获取全部
	 * @Description 获取hashmap中全部键值对
	 */
	public Map getHashKeyMap(String key,List<String> fields,boolean isAll);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param hashKey field
	 * @Description 获取hashmap中field对应的值
	 */
	public String getHashValueByKeyAndField(String hashKey,String field);
	/**
	 * 
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 判断指定的field在key中是否存在
	 */
	public boolean isHashExist(String key,String field);
	/**
	 * 
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 获取hashmap中键值对的数量
	 */
	public Long getHashLength(String key);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 向redis中添加set集合,返回添加成功的数量
	 */
	public Long setSetKeyValue(String key,String [] values);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 返回redis中key对应的set类型
	 */
	public Set<String> getSetKeyValues(String key);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 返回redis中key对应的set类型的长度
	 */
	public Long getSetLength(String key);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 判断redis中set类型key中value是否存在
	 */
	public Boolean isSetMember(String key,String value);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月16日
	 * @param
	 * @Description 从redis的set集合中删除值
	 */
	public Long removeSetMember(String key,String[] values);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月17日
	 * @param headOrEnd添加到list链表的头部还是尾部(0头部 1尾部)
	 * @Description
	 */
	public Long addListValues(String key,String[] values,String headOrEnd);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月17日
	 * @param
	 * @Description 获取start开始end结束的list中的元素。end可为负值 -1表示链表尾部-2为倒数第二个元素，以此类推
	 */
	public List<String> getListValues(String key,Long start,Long end);
	/**
	 * 
	 * @author shentt
	 * @date 2018年4月17日
	 * @param
	 * @Description 弹出list中头部或者尾部值(headOrEnd 0头部1尾部)
	 * pop与get的区别：pop后数据不在list中get后数据仍在list中
	 */
	public String popListValueHeadOrEnd(String key,String headOrEnd);
}	
