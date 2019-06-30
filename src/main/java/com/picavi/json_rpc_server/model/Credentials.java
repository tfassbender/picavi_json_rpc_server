package com.picavi.json_rpc_server.model;

public class Credentials {
	
	private String user;
	private String password;
	private String station;
	private String deviceIdent;
	
	public Credentials() {
		
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
}