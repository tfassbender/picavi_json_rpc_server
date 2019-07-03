package com.picavi.json_rpc_server.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.picavi.json_rpc_server.model.Configuration;
import com.picavi.json_rpc_server.model.JsonRpcLoginAnswer;
import com.picavi.json_rpc_server.model.JsonRpcRequest;
import com.picavi.json_rpc_server.model.JsonRpcResponse;
import com.picavi.json_rpc_server.test_utils.TestUtils;
import com.sun.net.httpserver.HttpServer;

class JsonRpcServerTest {
	
	private static HttpServer server;
	
	@BeforeAll
	public static void startServer() throws InterruptedException {
		server = JsonRpcServer.startTestServer();
	}
	
	@AfterAll
	public static void stopServer() {
		server.stop(0);
	}
	
	@Test
	void test() throws IOException {
		// CREATE
		
		// create a login request
		final String id = TestUtils.getRandomId();
		JsonRpcRequest loginRequest = TestUtils.createLoginRequest(id);
		
		// create the HTTP request from the login request
		ObjectWriter ow = new ObjectMapper().writer();
		String json = null;
		try {
			json = ow.writeValueAsString(loginRequest);
		}
		catch (JsonProcessingException e) {
			fail("Couldn't parse request object");
		}
		
		// send the request to the server via POST
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(JsonRpcServer.BASE_URI).path("/");
		
		// ACT
		Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(json, MediaType.APPLICATION_JSON));
		int responseCode = response.getStatus();
		
		// check whether the response was OK or an error code
		JsonRpcResponse rpcResponse = null;
		if (responseCode != Response.Status.OK.getStatusCode()) {
			fail("HTTP response code was not 200 but " + responseCode);
		}
		else if (response.hasEntity()) {
			rpcResponse = TestUtils.parseResponse(response);
		}
		
		// ASSERT
		JsonRpcLoginAnswer expectedResponse = new JsonRpcLoginAnswer("unknown session", new Configuration("DE", "right"));
		
		assertNotNull(rpcResponse);
		assertTrue(rpcResponse.getResult() instanceof JsonRpcLoginAnswer);
		// only compare the configuration because the sessionId is randomly generated
		assertEquals(expectedResponse.getConfiguration(), ((JsonRpcLoginAnswer) rpcResponse.getResult()).getConfiguration());
	}
}