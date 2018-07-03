package com.digitalchina.dch.datamsg.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalchina.dch.datamsg.mapper.DataMsgMapper;
import com.digitalchina.dch.datamsg.service.DataMsgService;
/**
 * 
 * 
 * @author shentt
 * @date 2018年4月10日
 * @className DataMsgServiceImpl.java
 * @param 
 * @Description 数据统计service实现
 */
@Service(value="DataMsgServiceImpl")
public class DataMsgServiceImpl implements DataMsgService {
	@Autowired
	private DataMsgMapper dataMsgMapper;
	
	@Override
	public Map<String, Object> qryDataCount() {
		return dataMsgMapper.qryDataCount();
	}

}
