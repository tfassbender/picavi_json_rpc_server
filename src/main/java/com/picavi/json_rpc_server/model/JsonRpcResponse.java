package com.picavi.json_rpc_server.model;

public class JsonRpcResponse {
	
	private String jsonRpc;
	private Object result;
	private Object error;
	private String id;
	
	public JsonRpcResponse() {
		
	}
	
	public String getJsonRpc() {
		return jsonRpc;
	}
	public void setJsonRpc(String jsonRpc) {
		this.jsonRpc = jsonRpc;
	}
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}