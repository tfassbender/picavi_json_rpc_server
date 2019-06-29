package com.picavi.json_rpc_server.model;

public class JsonRpcError {
	
	private int code;
	private String message;
	private Object data;
	
	public JsonRpcError() {
		
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
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}