package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ConfigurationTest {
	
	@Test
	public void testFromParameters() {
		Map<String, String> parameters = getConfigurationParameterMap();
		
		Configuration config = Configuration.fromParameters(parameters);
		Configuration expected = new Configuration(parameters.get("language"), parameters.get("handedness"));
		
		assertEquals(expected, config);
	}
	
	public static Map<String, String> getConfigurationParameterMap() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("language", "DE");
		parameters.put("handedness", "right");
		
		return parameters;
	}
}