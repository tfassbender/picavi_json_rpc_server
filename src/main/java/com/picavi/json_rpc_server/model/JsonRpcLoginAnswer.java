package com.picavi.json_rpc_server.model;

import java.util.Map;

public class JsonRpcLoginAnswer {
	
	private String sessionId;
	private Configuration configuration;
	
	public JsonRpcLoginAnswer() {
		//default constructor for JSON serialization
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
	
	public static JsonRpcLoginAnswer fromParameters(Object params) {
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = (Map<String, Object>) params;
		
		//check whether the required fields are included
		if (!parameterMap.containsKey("sessionId")) {
			throw new IllegalArgumentException("The required field \"sessionId\" is not found in the parameters");
		}
		
		JsonRpcLoginAnswer loginAnswer = new JsonRpcLoginAnswer();
		loginAnswer.setSessionId((String) parameterMap.get("sessionId"));
		if (parameterMap.containsKey("configuration")) {
			@SuppressWarnings("unchecked")
			Map<String, String> configParameters = (Map<String, String>) parameterMap.get("configuration");
			Configuration config = Configuration.fromParameters(configParameters);
			loginAnswer.setConfiguration(config);
		}
		
		return loginAnswer;
	}
}