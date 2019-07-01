package com.picavi.json_rpc_server.model;

import java.util.Map;

public class PicklistRequestParameters {
	
	private String sessionId;
	private String pickingListIdent;
	
	public PicklistRequestParameters() {
		
	}
	
	@Override
	public String toString() {
		return "PicklistRequestParameters [sessionId=" + sessionId + ", pickingListIdent=" + pickingListIdent + "]";
	}
	
	public PicklistRequestParameters(String sessionId, String pickingListIdent) {
		this.sessionId = sessionId;
		this.pickingListIdent = pickingListIdent;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getPickingListIdent() {
		return pickingListIdent;
	}
	public void setPickingListIdent(String pickingListIdent) {
		this.pickingListIdent = pickingListIdent;
	}
	
	public static PicklistRequestParameters fromParameters(Object params) {
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, String> parameterMap = (Map<String, String>) params;
		
		//check whether the required fields are included
		if (!parameterMap.containsKey("sessionId")) {
			throw new IllegalArgumentException("The required field \"sessionId\" is not found in the parameters");
		}
		
		PicklistRequestParameters pickingListRequest = new PicklistRequestParameters();
		pickingListRequest.setSessionId(parameterMap.get("sessionId"));
		pickingListRequest.setPickingListIdent(parameterMap.get("pickingListIdent"));
		
		return pickingListRequest;
	}
}