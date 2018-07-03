package com.digitalchina.dch.testdemo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.digitalchina.dch.testdemo.mapper.TestDemoMapper;
import com.digitalchina.dch.testdemo.model.Student;
import com.digitalchina.dch.testdemo.service.TestDemoService;

@Service(value="testDemoServiceImpl")
public class TestDemoServiceImpl implements TestDemoService{

	@Autowired
	@Qualifier(value="testDemoMapper")
	private TestDemoMapper testDemoMapper;
	
	@Autowired
	@Qualifier(value="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addData(Student student) {
		return testDemoMapper.addData(student);
	}

	@Override
	public Student qryDataReturnClass(Long id) {
		return testDemoMapper.qryDataReturnClass(id);
	}

	@Override
	public List<Map<String, Object>> qryDataReturnList(Long id) {
		String sql="select * from student a where a.id='"+id+"'";
		List<Map<String, Object>> list=new ArrayList<>();
		list=jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public int delData(Long id) {
		return testDemoMapper.delData(id);
	}

	@Override
	public List<Map<String, Object>> qryDataListReturnLM() {
		return testDemoMapper.qryDataListReturnLM();
	}

	@Override
	public List<Student> qryDataListReturnLC() {
		return testDemoMapper.qryDataListReturnLC();
	}

}
