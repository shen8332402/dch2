package com.digitalchina.dch.testdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalchina.dch.testdemo.model.Student;
import com.digitalchina.dch.testdemo.service.TestDemoService;

@RestController
@Scope("prototype")
@RequestMapping(value="/testDemo")
public class TestDemoController {
	@Autowired
	@Qualifier(value="testDemoServiceImpl")
	private TestDemoService testDemoService;
	
	@PutMapping
	public int addData(Student student)
	{
		Double double1=new Double(123);
		Student student2=new Student("小米","z","saf",double1);
		return testDemoService.addData(student2);
	}
	@GetMapping(value="/class/{id}")
	public Student qryDataReturnClass(@PathVariable(value="id")Long id){
		Student student=new Student();
		student=testDemoService.qryDataReturnClass(id);
		return student;
	}
	@GetMapping(value="/map/{id}")
	public Map<String, Object> qryDataReturnList(@PathVariable(value="id")Long id)
	{
		Map<String, Object> map=new HashMap<>();
		if (testDemoService.qryDataReturnList(id)!=null &&!testDemoService.qryDataReturnList(id).isEmpty()) {
			map=testDemoService.qryDataReturnList(id).get(0);
		}
		return map;
	}
	@DeleteMapping(value="/{id}")
	public int delData(@PathVariable(value="id") Long id){
		return testDemoService.delData(id);
	}
	@GetMapping(value="/listMap")
	public List<Map<String, Object>> qryDataListReturnLM(){
		return testDemoService.qryDataListReturnLM();
	}
	@GetMapping(value="/listClass")
	public List<Student> qryDataListReturnLC()
	{
		return testDemoService.qryDataListReturnLC();
	}
}
