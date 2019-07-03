package com.picavi.json_rpc_server.model;

import java.util.Map;

public class Configuration {
	
	private String language;
	private String handedness;
	
	public Configuration() {
		//default constructor for JSON serialization
	}
	
	@Override
	public String toString() {
		return "Configuration [language=" + language + ", handedness=" + handedness + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handedness == null) ? 0 : handedness.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
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
		Configuration other = (Configuration) obj;
		if (handedness == null) {
			if (other.handedness != null)
				return false;
		}
		else if (!handedness.equals(other.handedness))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		}
		else if (!language.equals(other.language))
			return false;
		return true;
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
	
	public static Configuration fromParameters(Map<String, String> params) {
		Configuration configuration = new Configuration();
		configuration.setLanguage(params.get("language"));
		configuration.setHandedness(params.get("handedness"));
		
		return configuration;
	}
}