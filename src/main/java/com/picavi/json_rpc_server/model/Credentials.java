package com.picavi.json_rpc_server.model;

import java.util.Map;

public class Credentials {
	
	private String user;
	private String password;
	private String station;
	private String deviceIdent;
	
	public Credentials() {
		//default constructor for JSON serialization
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceIdent == null) ? 0 : deviceIdent.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Credentials other = (Credentials) obj;
		if (deviceIdent == null) {
			if (other.deviceIdent != null)
				return false;
		}
		else if (!deviceIdent.equals(other.deviceIdent))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (station == null) {
			if (other.station != null)
				return false;
		}
		else if (!station.equals(other.station))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		}
		else if (!user.equals(other.user))
			return false;
		return true;
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