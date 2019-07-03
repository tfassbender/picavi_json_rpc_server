package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PicklineTest {
	
	@Test
	public void testFromParameters() {
		Map<String, Object> parameters = createPicklineParameters();
		
		Pickline line = Pickline.fromParameters(parameters);
		Pickline expected = new Pickline((String) parameters.get("ident"), (String) parameters.get("location"), (String) parameters.get("item"),
				(Double) parameters.get("quantity"), (String) parameters.get("unit"), (String) parameters.get("status"));
		
		assertEquals(expected, line);
	}
	
	public static Map<String, Object> createPicklineParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ident", "42");
		parameters.put("location", "somewhere");
		parameters.put("item", "unicorn");
		parameters.put("quantity", Double.valueOf(42));
		parameters.put("status", "new");
		
		return parameters;
	}
}