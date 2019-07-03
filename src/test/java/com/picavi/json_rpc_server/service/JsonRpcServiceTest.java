package com.picavi.json_rpc_server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.picavi.json_rpc_server.model.Configuration;
import com.picavi.json_rpc_server.model.JsonRpcErrorResponse;
import com.picavi.json_rpc_server.model.JsonRpcLoginAnswer;
import com.picavi.json_rpc_server.model.JsonRpcRequest;
import com.picavi.json_rpc_server.model.JsonRpcResponse;
import com.picavi.json_rpc_server.model.Picklist;
import com.picavi.json_rpc_server.test_utils.TestUtils;

class JsonRpcServiceTest {
	
	@Test
	public void testLogin() throws IOException {
		JsonRpcService service = new JsonRpcService();
		
		final String id = TestUtils.getRandomId();
		JsonRpcRequest loginRequest = TestUtils.createLoginRequest(id);
		
		Response response = service.processRequestSynchrone(loginRequest);
		
		JsonRpcLoginAnswer expectedResponse = new JsonRpcLoginAnswer("unknown session", new Configuration("DE", "right"));
		JsonRpcResponse rpcResponse = (JsonRpcResponse) response.getEntity();
		JsonRpcLoginAnswer actualAnswer = (JsonRpcLoginAnswer) rpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		//only compare the configuration because the sessionId is randomly generated
		assertEquals(expectedResponse.getConfiguration(), actualAnswer.getConfiguration());
		assertEquals(id, rpcResponse.getId());
	}
	
	@Test
	public void testLoginAsynchrone() throws IOException {
		JsonRpcService service = new JsonRpcService();
		
		final String id = TestUtils.getRandomId();
		JsonRpcRequest loginRequest = TestUtils.createLoginRequest(id);
		
		Response asynchroneResponse = service.processRequestAsynchrone(loginRequest);
		
		JsonRpcLoginAnswer expectedResponse = new JsonRpcLoginAnswer("unknown session", new Configuration("DE", "right"));
		JsonRpcResponse asynchroneRpcResponse = (JsonRpcResponse) asynchroneResponse.getEntity();
		JsonRpcLoginAnswer actualAsynchroneAnswer = (JsonRpcLoginAnswer) asynchroneRpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), asynchroneResponse.getStatus());
		//only compare the configuration because the sessionId is randomly generated
		assertEquals(expectedResponse.getConfiguration(), actualAsynchroneAnswer.getConfiguration());
		assertEquals(id, asynchroneRpcResponse.getId());
	}
	
	@Test
	public void testLogout() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		
		JsonRpcRequest logoutRequest = TestUtils.createLogoutRequest(id);
		
		Response response = service.processRequestSynchrone(logoutRequest);
		
		JsonRpcResponse rpcResponse = (JsonRpcResponse) response.getEntity();
		Boolean answer = (Boolean) rpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(true, answer);
		assertEquals(id, rpcResponse.getId());
	}
	
	@Test
	public void testLogoutAsynchrone() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		
		JsonRpcRequest logoutRequest = TestUtils.createLogoutRequest(id);
		
		Response response = service.processRequestAsynchrone(logoutRequest);
		
		JsonRpcResponse rpcResponse = (JsonRpcResponse) response.getEntity();
		Boolean answer = (Boolean) rpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(true, answer);
		assertEquals(id, rpcResponse.getId());
	}
	
	@Test
	public void testGetPicklist() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		
		JsonRpcRequest picklistRequest = TestUtils.createGetPicklistRequest(id);
		
		Response response = service.processRequestSynchrone(picklistRequest);
		
		JsonRpcResponse rpcResponse = (JsonRpcResponse) response.getEntity();
		Picklist picklist = (Picklist) rpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(picklist);
		assertFalse(picklist.getLines().isEmpty());
		assertEquals(id, rpcResponse.getId());
	}
	
	@Test
	public void testGetPicklistAsynchrone() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		
		JsonRpcRequest picklistRequest = TestUtils.createGetPicklistRequest(id);
		
		Response response = service.processRequestAsynchrone(picklistRequest);
		
		JsonRpcResponse rpcResponse = (JsonRpcResponse) response.getEntity();
		Picklist picklist = (Picklist) rpcResponse.getResult();
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(picklist);
		assertFalse(picklist.getLines().isEmpty());
		assertEquals(id, rpcResponse.getId());
	}
	
	@Test
	public void testProcessUnknownMethod() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		JsonRpcRequest loginRequest = TestUtils.createLoginRequest(id);
		
		//change the method name
		loginRequest.setMethod("system.false_login_method");
		
		Response response = service.processRequestSynchrone(loginRequest);
		
		assertTrue(response.getEntity() instanceof JsonRpcErrorResponse);
	}
	
	@Test
	public void testProcessWrongInputs() throws IOException {
		JsonRpcService service = new JsonRpcService();
		final String id = TestUtils.getRandomId();
		
		JsonRpcRequest loginRequest = TestUtils.createLoginRequest(id);
		JsonRpcRequest logoutRequest = TestUtils.createLogoutRequest(id);
		JsonRpcRequest picklistRequest = TestUtils.createGetPicklistRequest(id);
		
		//change parameters to wrong types
		loginRequest.setParams("user=player1");
		logoutRequest.setParams(new Configuration("DE", "right"));
		picklistRequest.setParams("identifier");
		
		Response loginResponse = service.processRequestSynchrone(loginRequest);
		Response logoutResponse = service.processRequestSynchrone(logoutRequest);
		Response picklistResponse = service.processRequestSynchrone(picklistRequest);
		
		assertTrue(loginResponse.getEntity() instanceof JsonRpcErrorResponse);
		assertTrue(logoutResponse.getEntity() instanceof JsonRpcErrorResponse);
		assertTrue(picklistResponse.getEntity() instanceof JsonRpcErrorResponse);
	}
}