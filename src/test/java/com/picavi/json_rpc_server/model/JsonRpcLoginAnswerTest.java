package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JsonRpcLoginAnswerTest {
	
	@Test
	public void testFromParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("sessionId", "aSessionId");
		parameters.put("configuration", ConfigurationTest.getConfigurationParameterMap());
		
		JsonRpcLoginAnswer loginAnswer = JsonRpcLoginAnswer.fromParameters(parameters);
		JsonRpcLoginAnswer expected = new JsonRpcLoginAnswer((String) parameters.get("sessionId"),
				Configuration.fromParameters(parameters.get("configuration")));
		
		assertEquals(expected, loginAnswer);
	}
}