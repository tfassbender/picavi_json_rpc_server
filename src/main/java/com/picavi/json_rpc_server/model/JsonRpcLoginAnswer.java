package com.picavi.json_rpc_server.model;

public class JsonRpcLoginAnswer {
	
	private String sessionId;
	private Configuration configuration;
	
	public JsonRpcLoginAnswer() {
		
	}
	
	@Override
	public String toString() {
		return "JsonRpcLoginAnswer [sessionId=" + sessionId + ", configuration=" + configuration + "]";
	}
	
	public JsonRpcLoginAnswer(String sessionId, Configuration configuration) {
		this.sessionId = sessionId;
		this.configuration = configuration;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}