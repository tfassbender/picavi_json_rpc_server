package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CredentialsTest {
	
	@Test
	public void testFromParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("user", "player1");
		parameters.put("password", "secret");
		parameters.put("deviceIdent", "42");
		parameters.put("station", "");
		
		Credentials credentials = Credentials.fromParameters(parameters);
		Credentials expected = new Credentials(parameters.get("user"), parameters.get("password"), parameters.get("station"),
				parameters.get("deviceIdent"));
		
		assertEquals(expected, credentials);
	}
}