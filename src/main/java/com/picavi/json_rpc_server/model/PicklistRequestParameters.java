package com.picavi.json_rpc_server.model;

import java.util.Map;

public class PicklistRequestParameters {
	
	private String sessionId;
	private String pickingListIdent;
	
	public PicklistRequestParameters() {
		//default constructor for JSON serialization
	}
	
	@Override
	public String toString() {
		return "PicklistRequestParameters [sessionId=" + sessionId + ", pickingListIdent=" + pickingListIdent + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pickingListIdent == null) ? 0 : pickingListIdent.hashCode());
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
		PicklistRequestParameters other = (PicklistRequestParameters) obj;
		if (pickingListIdent == null) {
			if (other.pickingListIdent != null)
				return false;
		}
		else if (!pickingListIdent.equals(other.pickingListIdent))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		}
		else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
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