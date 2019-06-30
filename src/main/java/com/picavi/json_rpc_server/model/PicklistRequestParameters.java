package com.picavi.json_rpc_server.model;

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
}