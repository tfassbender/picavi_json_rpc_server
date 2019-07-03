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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JsonRpcLoginAnswer other = (JsonRpcLoginAnswer) obj;
		if (configuration == null) {
			if (other.configuration != null)
				return false;
		}
		else if (!configuration.equals(other.configuration))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		}
		else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
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