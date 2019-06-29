package com.picavi.json_rpc_server.model;

public class JsonRpcRequest {
	
	private String jsonRpc;
	private String method;
	private Object params;
	private String id;
	
	public JsonRpcRequest() {
		
	}
	
	public String getJsonRpc() {
		return jsonRpc;
	}
	public void setJsonRpc(String jsonRpc) {
		this.jsonRpc = jsonRpc;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public Object getParams() {
		return params;
	}
	public void setParams(Object params) {
		this.params = params;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}