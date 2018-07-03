package com.digitalchina.dch.testdemo.model;


public class Student {
	private Long id;
	private String name;
	private String address;
	private String tel;
	private Double score;
	public Student(){
		
	}
	public Student(String name,String address,String tel,Double score){
		this.name=name;
		this.address=address;
		this.tel=tel;
		this.score=score;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
}
