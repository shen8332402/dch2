package com.digitalchina.frame.datamodel;

/**
 * 
 * @author shentt
 * @date 2018年4月8日
 * @className ResponseData.java
 * @param 
 * @Description 返回前端的响应数据
 */
public class ResponseData {
	private int status;
	private String msg;
	private Object data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static ResponseData ok(Object data){
		ResponseData responseData=new ResponseData();
		responseData.setStatus(200);
		responseData.setMsg("操作成功");
		responseData.setData(data);
		return responseData;
	}
	public static ResponseData fail(){
		ResponseData responseData=new ResponseData();
		responseData.setStatus(404);
		responseData.setMsg("操作失败");
		responseData.setData(null);
		return responseData;
	}
}
