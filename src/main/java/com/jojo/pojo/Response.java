package com.jojo.pojo;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Response {

	/**
	 * 
	 */
	public static final int SCUCCESS = 1;

	/**
	 * 
	 */
	public static final int FAIL = 0;

	private int code;

	private String message;

	private Object data;

	private List<?> list;

	private Map<String, Object> pairs;

	public Response() {
		super();
	}

	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSuccessMessage(String message) {
		this.code = SCUCCESS;
		this.message = message;
	}

	public void setFailMessage(String message) {
		this.code = FAIL;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> data) {
		this.list = data;
	}

	public Map<String, Object> getPairs() {
		return pairs;
	}

	public void setPairs(Map<String, Object> pairs) {
		this.pairs = pairs;
	}

	public static void main(String[] args) {
		Response response = new Response();
		response.getPairs().put("key", "value");
		response.getPairs().put("1", 1);
		response.getPairs().put("qwer", new Object());
		System.out.println(JSON.toJSONString(response));

	}
}