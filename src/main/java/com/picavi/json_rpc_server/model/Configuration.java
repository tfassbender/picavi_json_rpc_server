package com.picavi.json_rpc_server.model;

public class Configuration {
	
	private String language;
	private String handedness;
	
	public Configuration() {
		
	}
	
	@Override
	public String toString() {
		return "Configuration [language=" + language + ", handedness=" + handedness + "]";
	}
	
	public Configuration(String language, String handedness) {
		this.language = language;
		this.handedness = handedness;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getHandedness() {
		return handedness;
	}
	public void setHandedness(String handedness) {
		this.handedness = handedness;
	}
}