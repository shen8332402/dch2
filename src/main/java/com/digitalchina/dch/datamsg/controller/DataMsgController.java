package com.digitalchina.dch.datamsg.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.dch.datamsg.service.DataMsgService;
import com.digitalchina.frame.datamodel.ResponseData;
/**
 * @author shentt
 * @date 2018年4月10日
 * @className DataMsgController.java
 * @param 
 * @Description 返回数据统计结果
 */
@RestController
@Scope("prototype")
@RequestMapping("/dataMsg")
public class DataMsgController {
	@Autowired
	@Qualifier(value="DataMsgServiceImpl")
	private DataMsgService dataMsgService;

	@RequestMapping(value="/dataCount",method={RequestMethod.POST, RequestMethod.GET})
	public ResponseData qryDataCount(){
		Map<String, Object> dataMsg=new HashMap<>();
		dataMsg=dataMsgService.qryDataCount();
		if (dataMsg!=null&&!dataMsg.isEmpty()) {
			return ResponseData.ok(dataMsg);
		}else {
			return ResponseData.fail();
		}
	}
}
