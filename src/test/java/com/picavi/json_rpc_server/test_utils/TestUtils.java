package com.picavi.json_rpc_server.test_utils;

import java.io.IOException;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.picavi.json_rpc_server.model.Credentials;
import com.picavi.json_rpc_server.model.JsonRpcLoginAnswer;
import com.picavi.json_rpc_server.model.JsonRpcRequest;
import com.picavi.json_rpc_server.model.JsonRpcResponse;
import com.picavi.json_rpc_server.model.PicklistRequestParameters;

public class TestUtils {
	
	/**
	 * Parse the response to a JsonRpcResponse object or a JsonRpcErrorResponse object (if it is an error; in this case the error will be logged and
	 * an exception will be thrown)
	 */
	public static JsonRpcResponse parseResponse(Response response) throws IllegalStateException {
		JsonRpcResponse resp = getJsonRpcResponse(response);
		//parse the left objects
		JsonRpcLoginAnswer loginAnswer = JsonRpcLoginAnswer.fromParameters(resp.getResult());
		resp.setResult(loginAnswer);
		return resp;
	}
	
	/**
	 * Get a JsonRpcResponse from a Response object. (Deserializes JSON)
	 */
	public static JsonRpcResponse getJsonRpcResponse(Response response) throws IllegalStateException {
		String responseText = response.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		try {
			//"manually" parse JSON to Object
			JsonRpcResponse resp = mapper.readValue(responseText, JsonRpcResponse.class);
			return resp;
		}
		catch (IOException e) {
			throw new IllegalStateException("The response could not be read or parsed: " + responseText);
		}
	}
	
	/**
	 * Create a random id for the request to test whether the returned id is always correct.
	 */
	public static String getRandomId() {
		return Integer.toString((int) (Math.random() * 42));
	}
	
	/**
	 * Create a request for a login using a given id for the request.
	 */
	public static JsonRpcRequest createLoginRequest(String id) throws IOException {
		JsonRpcRequest loginRequest = new JsonRpcRequest();
		loginRequest.setId(id);
		loginRequest.setMethod("system.login");
		loginRequest.setJsonRpc("2.0");
		loginRequest.setParams(new Credentials("player1", "secure", "", ""));
		
		return toJsonSerializedRequest(loginRequest);
	}
	
	/**
	 * Create a request to logout a session
	 */
	public static JsonRpcRequest createLogoutRequest(String id) throws IOException {
		JsonRpcRequest logoutRequest = new JsonRpcRequest();
		logoutRequest.setId(id);
		logoutRequest.setMethod("system.logout");
		logoutRequest.setJsonRpc("2.0");
		logoutRequest.setParams("aSessionId");
		
		return toJsonSerializedRequest(logoutRequest);
	}
	
	/**
	 * Create a request to get a picklist from the server
	 */
	public static JsonRpcRequest createGetPicklistRequest(String id) throws IOException {
		JsonRpcRequest picklistRequest = new JsonRpcRequest();
		picklistRequest.setId(id);
		picklistRequest.setMethod("orderPicking.getPickingList");
		picklistRequest.setJsonRpc("2.0");
		PicklistRequestParameters parameters = new PicklistRequestParameters("aSessionId", "42");
		picklistRequest.setParams(parameters);
		
		return toJsonSerializedRequest(picklistRequest);
	}
	
	/**
	 * Serialize and deserialize the request to build a request like it's received from the server.
	 */
	public static JsonRpcRequest toJsonSerializedRequest(JsonRpcRequest request) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer();
		String json = ow.writeValueAsString(request);
		JsonRpcRequest jsonRequest = mapper.readValue(json, JsonRpcRequest.class);
		return jsonRequest;
	}
}