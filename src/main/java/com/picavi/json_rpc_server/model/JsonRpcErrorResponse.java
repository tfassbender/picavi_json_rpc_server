package com.picavi.json_rpc_server.model;

public class JsonRpcErrorResponse {
	
	private String jsonRpc;
	private Object error;
	private String id;
	
	public JsonRpcErrorResponse() {
		
	}
	
	@Override
	public String toString() {
		return "JsonRpcErrorResponse [jsonRpc=" + jsonRpc + ", error=" + error + ", id=" + id + "]";
	}
	
	public String getJsonRpc() {
		return jsonRpc;
	}
	public void setJsonRpc(String jsonRpc) {
		this.jsonRpc = jsonRpc;
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
