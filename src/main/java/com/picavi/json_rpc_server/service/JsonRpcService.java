package com.picavi.json_rpc_server.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.picavi.json_rpc_server.backend.LoginException;
import com.picavi.json_rpc_server.backend.OrderPicking;
import com.picavi.json_rpc_server.backend.PickingException;
import com.picavi.json_rpc_server.backend.SystemAutentification;
import com.picavi.json_rpc_server.model.Configuration;
import com.picavi.json_rpc_server.model.Credentials;
import com.picavi.json_rpc_server.model.JsonRpcLoginAnswer;
import com.picavi.json_rpc_server.model.JsonRpcRequest;
import com.picavi.json_rpc_server.model.JsonRpcResponse;
import com.picavi.json_rpc_server.model.Picklist;
import com.picavi.json_rpc_server.model.PicklistRequestParameters;
import com.picavi.json_rpc_server.util.JsonRpcErrorUtil;

@Path("/")
public class JsonRpcService {
	
	private static final Logger LOGGER = LogManager.getLogger(JsonRpcService.class);
	
	public static final String JSON_RPC = "2.0";
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(JsonRpcRequest request) {
		LOGGER.info("received (synchrone) request: {}", request);
		
		//execute the requested method
		switch (request.getMethod()) {
			case "system.login":
				return processLogin(request);
			case "system.logout":
				return processLogout(request);
			case "orderPicking.getPickingList":
				return processGetPickingList(request);
		}
		
		//if the method is none of the above return an error
		LOGGER.warn("the request could not be processed, because the method name is unknown: {}", request.getMethod());
		return JsonRpcErrorUtil.createMethodNotFoundErrorResponse(request.getId(), request.getMethod());
	}
	
	@POST
	@Path("/async")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequestAsync(JsonRpcRequest request) {
		LOGGER.info("received (asynchrone) request: {}", request);
		
		//execute the requested method
		try {
			switch (request.getMethod()) {
				case "system.login":
					return CompletableFuture.supplyAsync(() -> processLogin(request)).get();
				case "system.logout":
					return CompletableFuture.supplyAsync(() -> processLogout(request)).get();
				case "orderPicking.getPickingList":
					return CompletableFuture.supplyAsync(() -> processGetPickingList(request)).get();
			}
		}
		catch (InterruptedException | ExecutionException e) {
			LOGGER.error("The asynchrone execution of the request failed", e);
			
			//return an unknown error because this exceptions should never occur
			return JsonRpcErrorUtil.createErrorResponse(request.getId());
		}
		
		//if the method is none of the above return an error
		LOGGER.warn("the request could not be processed, because the method name is unknown: {}", request.getMethod());
		return JsonRpcErrorUtil.createMethodNotFoundErrorResponse(request.getId(), request.getMethod());
	}
	
	private Response processLogin(JsonRpcRequest request) {
		LOGGER.info("processing login request");
		//get the login information
		Credentials credentials;
		try {
			credentials = Credentials.fromParameters(request.getParams());
		}
		catch (ClassCastException | IllegalArgumentException e) {
			LOGGER.warn("Parameters could not be parsed: {}; Type: {}; caused by: {}", request.getParams(),
					request.getParams().getClass().getSimpleName(), e.getMessage());
			return JsonRpcErrorUtil.createIllegalParameterErrorResponse(request.getId(), request.getParams());
		}
		
		String user = credentials.getUser();
		String password = credentials.getPassword();
		
		//login the user
		SystemAutentification systemProcessor = SystemAutentification.getInstance();
		String sessionId;
		try {
			sessionId = systemProcessor.login(user, password);
		}
		catch (LoginException le) {
			return JsonRpcErrorUtil.createLoginErrorResponse(request.getId());
		}
		
		//create the response
		JsonRpcResponse response = createEmptyResponse(request.getId());
		response.setResult(new JsonRpcLoginAnswer(sessionId, new Configuration("DE", "right")));
		
		LOGGER.info("Sending response to login request: {}", response);
		
		//return the response
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response processLogout(JsonRpcRequest request) {
		LOGGER.info("Processing logout request");
		
		//get the information form the request
		String sessionId;
		try {
			sessionId = (String) request.getParams();
		}
		catch (ClassCastException | IllegalArgumentException e) {
			LOGGER.warn("Parameters could not be parsed: {}; Type: {}; caused by: {}", request.getParams(),
					request.getParams().getClass().getSimpleName(), e.getMessage());
			return JsonRpcErrorUtil.createIllegalParameterErrorResponse(request.getId(), request.getParams());
		}
		
		//logout the user
		SystemAutentification systemProcessor = SystemAutentification.getInstance();
		boolean logoutSucessful;
		try {
			logoutSucessful = systemProcessor.logout(sessionId);
		}
		catch (LoginException le) {
			//don't create an error here; just set the result to false
			logoutSucessful = false;
		}
		
		//create the response
		JsonRpcResponse response = createEmptyResponse(request.getId());
		response.setResult(Boolean.valueOf(logoutSucessful));
		
		LOGGER.info("Sending response to logout request: {}", response);
		
		//return the response
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response processGetPickingList(JsonRpcRequest request) {
		LOGGER.info("Processing picklist request");
		
		//get the information from the request
		PicklistRequestParameters parameters;
		try {
			parameters = PicklistRequestParameters.fromParameters(request.getParams());
		}
		catch (ClassCastException | IllegalArgumentException e) {
			LOGGER.warn("Parameters could not be parsed: {}; Type: {}; caused by: {}", request.getParams(),
					request.getParams().getClass().getSimpleName(), e.getMessage());
			return JsonRpcErrorUtil.createIllegalParameterErrorResponse(request.getId(), request.getParams());
		}
		String sessionId = parameters.getSessionId();
		String pickingListIdent = parameters.getPickingListIdent();
		
		//get the picklist from the database (or wherever it is)
		OrderPicking orderPicking = OrderPicking.getInstance();
		Picklist picklist;
		try {
			picklist = orderPicking.getPickList(sessionId, pickingListIdent);
		}
		catch (PickingException pe) {
			return JsonRpcErrorUtil.createPickingErrorResponse(request.getId());
		}
		
		//create the response
		JsonRpcResponse response = createEmptyResponse(request.getId());
		response.setResult(picklist);
		
		LOGGER.info("Sending response to getPickList request: {}", response);
		
		//return the picklist
		return Response.status(Status.OK).entity(response).build();
	}
	
	/**
	 * Creates a positive response with a given id, but without content
	 */
	private JsonRpcResponse createEmptyResponse(String id) {
		JsonRpcResponse response = new JsonRpcResponse();
		response.setId(id);
		response.setJsonRpc(JSON_RPC);
		return response;
	}
}