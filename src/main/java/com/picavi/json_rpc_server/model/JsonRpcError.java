package com.picavi.json_rpc_server.model;

import java.util.Map;

/**
 * Error codes:
 *
 * Ranges Types
 * 
 * error -10000 to -19999 <br>
 * warning 10000 to 19999 <br>
 * info 20000 to 29999
 * 
 * Groups
 * 
 * within every range there are some areas
 * 
 * 0-999 Global <br>
 * 1000-1499 Initialization <br>
 * 1500-1999 Order Picking
 * 
 */
public class JsonRpcError {
	
	private int code;
	private String message;
	private Object data;
	
	public static final JsonRpcError OK = new JsonRpcError(0, "", null);
	
	public JsonRpcError() {
		
	}
	
	@Override
	public String toString() {
		return "JsonRpcError [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	
	public JsonRpcError(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
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
	
	public static JsonRpcError fromParameters(Object params) {
		if (params == null) {
			return null;
		}
		
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = (Map<String, Object>) params;
		
		JsonRpcError error = new JsonRpcError();
		error.setCode((Integer) parameterMap.get("code"));
		error.setMessage((String) parameterMap.get("message"));
		error.setData(parameterMap.get("data"));
		return error;
	}
}