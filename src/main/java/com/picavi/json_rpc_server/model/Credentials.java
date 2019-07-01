package com.picavi.json_rpc_server.model;

import java.util.Map;

public class Credentials {
	
	private String user;
	private String password;
	private String station;
	private String deviceIdent;
	
	public Credentials() {
		
	}
	
	public Credentials(String user, String password, String station, String deviceIdent) {
		this.user = user;
		this.password = password;
		this.station = station;
		this.deviceIdent = deviceIdent;
	}
	
	@Override
	public String toString() {
		return "Credentials [user=" + user + ", password=" + password + ", station=" + station + ", deviceIdent=" + deviceIdent + "]";
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	
	public String getDeviceIdent() {
		return deviceIdent;
	}
	public void setDeviceIdent(String deviceIdent) {
		this.deviceIdent = deviceIdent;
	}
	
	public static Credentials fromParameters(Object params) {
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, String> parameterMap = (Map<String, String>) params;
		
		//check whether the required fields are included
		if (!parameterMap.containsKey("user")) {
			throw new IllegalArgumentException("The required field \"user\" is not found in the parameters");
		}
		
		Credentials credentials = new Credentials();
		credentials.setUser(parameterMap.get("user"));
		credentials.setPassword(parameterMap.get("password"));
		credentials.setDeviceIdent(parameterMap.get("deviceIdent"));
		credentials.setStation(parameterMap.get("station"));
		
		return credentials;
	}
}