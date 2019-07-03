package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PicklistRequestParametersTest {
	
	@Test
	public void testFromParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("sessionId", "aSessionId");
		parameters.put("pickingListIdent", "42");
		
		PicklistRequestParameters requestParameters = PicklistRequestParameters.fromParameters(parameters);
		PicklistRequestParameters expected = new PicklistRequestParameters(parameters.get("sessionId"), parameters.get("pickingListIdent"));
		
		assertEquals(expected, requestParameters);
	}
}