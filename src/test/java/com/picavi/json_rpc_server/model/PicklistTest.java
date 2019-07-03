package com.picavi.json_rpc_server.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PicklistTest {
	
	@Test
	public void testFromParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ident", "42");
		
		List<Map<String, Object>> lines = new ArrayList<Map<String, Object>>(3);
		lines.add(PicklineTest.createPicklineParameters());
		lines.add(PicklineTest.createPicklineParameters());
		lines.add(PicklineTest.createPicklineParameters());
		parameters.put("lines", lines);
		
		Pickline line = Pickline.fromParameters(PicklineTest.createPicklineParameters());
		Picklist picklist = Picklist.fromParameters(parameters);
		Picklist expected = new Picklist();
		expected.setIdent((String) parameters.get("ident"));
		expected.addLine(line);
		expected.addLine(line);
		expected.addLine(line);
		
		assertEquals(expected, picklist);
	}
	
	@Test
	public void testFromParameters_emptyPicklist() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ident", "42");
		
		List<Map<String, Object>> lines = new ArrayList<Map<String, Object>>(0);
		parameters.put("lines", lines);
		
		Picklist picklist = Picklist.fromParameters(parameters);
		Picklist expected = new Picklist();
		expected.setIdent((String) parameters.get("ident"));
		
		assertEquals(expected, picklist);
	}
}