package com.picavi.json_rpc_server.model;

public class JsonRpcResponse {
	
	private String jsonRpc;
	private Object result;
	private String id;
	
	public JsonRpcResponse() {
		//default constructor for JSON serialization
	}
	
	@Override
	public String toString() {
		return "JsonRpcResponse [jsonRpc=" + jsonRpc + ", result=" + result + ", id=" + id + "]";
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}