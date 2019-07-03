package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JsonRpcErrorTest {
	
	@Test
	public void testFromParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("code", Integer.valueOf(42));
		parameters.put("message", "an error message");
		parameters.put("data", null);
		
		JsonRpcError error = JsonRpcError.fromParameters(parameters);
		JsonRpcError expected = new JsonRpcError((Integer) parameters.get("code"), (String) parameters.get("message"), parameters.get("data"));
		
		assertEquals(expected, error);
	}
}