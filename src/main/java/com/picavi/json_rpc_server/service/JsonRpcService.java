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

import com.picavi.json_rpc_server.backend.LoginException;
import com.picavi.json_rpc_server.backend.OrderPicking;
import com.picavi.json_rpc_server.backend.SystemAutentification;
import com.picavi.json_rpc_server.model.Configuration;
import com.picavi.json_rpc_server.model.Credentials;
import com.picavi.json_rpc_server.model.JsonRpcError;
import com.picavi.json_rpc_server.model.JsonRpcLoginAnswer;
import com.picavi.json_rpc_server.model.JsonRpcRequest;
import com.picavi.json_rpc_server.model.JsonRpcResponse;
import com.picavi.json_rpc_server.model.Picklist;
import com.picavi.json_rpc_server.model.PicklistRequestParameters;

@Path("/")
public class JsonRpcService {
	
	private static final String jsonRPC = "2.0";
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequest(JsonRpcRequest request) {
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
		return createMethodNotFoundErrorResponse(request.getId(), request.getMethod());
	}
	
	@POST
	@Path("async")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response processRequestAsync(JsonRpcRequest request) {
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
			e.printStackTrace();
			//return an unknown error because this exceptions should never occur
			return createErrorResponse(request.getId());
		}
		//if the method is none of the above return an error
		return createMethodNotFoundErrorResponse(request.getId(), request.getMethod());
	}
	
	private Response processLogin(JsonRpcRequest request) {
		//get the login information
		Credentials credentials;
		try {
			credentials = (Credentials) request.getParams();
		}
		catch (ClassCastException cce) {
			return createIllegalParameterErrorResponse(request.getId(), request.getParams());
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
			return createLoginErrorResponse(request.getId());
		}
		
		//create the response
		JsonRpcResponse response = new JsonRpcResponse();
		response.setId(request.getId());
		response.setJsonRpc(jsonRPC);
		response.setError(JsonRpcError.OK);
		response.setResult(new JsonRpcLoginAnswer(sessionId, new Configuration("DE", "right")));
		
		//return the response
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response processLogout(JsonRpcRequest request) {
		//get the information form the request
		String sessionId;
		try {
			sessionId = (String) request.getParams();
		}
		catch (ClassCastException cce) {
			return createIllegalParameterErrorResponse(request.getId(), request.getParams());
		}
		
		//logout the user
		SystemAutentification systemProcessor = SystemAutentification.getInstance();
		boolean logoutSucessful = systemProcessor.logout(sessionId);
		
		//create the response
		JsonRpcResponse response = new JsonRpcResponse();
		response.setId(request.getId());
		response.setJsonRpc(jsonRPC);
		response.setError(JsonRpcError.OK);
		response.setResult(Boolean.valueOf(logoutSucessful));
		
		//return the response
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response processGetPickingList(JsonRpcRequest request) {
		//get the information from the request
		PicklistRequestParameters parameters;
		try {
			parameters = (PicklistRequestParameters) request.getParams();
		}
		catch (ClassCastException cce) {
			return createIllegalParameterErrorResponse(request.getId(), request.getParams());
		}
		String sessionId = parameters.getSessionId();
		String pickingListIdent = parameters.getPickingListIdent();
		
		//get the picklist from the database (or wherever it is)
		OrderPicking orderPicking = OrderPicking.getInstance();
		Picklist picklist = orderPicking.getPickList(sessionId, pickingListIdent);
		
		//create the response
		JsonRpcResponse response = new JsonRpcResponse();
		response.setId(request.getId());
		response.setJsonRpc(jsonRPC);
		response.setError(JsonRpcError.OK);
		response.setResult(picklist);
		
		//return the picklist
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response createErrorResponse(String id) {
		JsonRpcResponse response = createEmptyErrorResponse(id);
		response.setError(new JsonRpcError(-10000, "Unknown error occured", null));
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response createLoginErrorResponse(String id) {
		JsonRpcResponse response = createEmptyErrorResponse(id);
		response.setError(new JsonRpcError(-10100, "Login was not successful", null));
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response createIllegalParameterErrorResponse(String id, Object parameters) {
		JsonRpcResponse response = createEmptyErrorResponse(id);
		response.setError(new JsonRpcError(-10200, "Unexpected parameters in request", parameters));
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	private Response createMethodNotFoundErrorResponse(String id, String methodName) {
		JsonRpcResponse response = createEmptyErrorResponse(id);
		response.setError(new JsonRpcError(-10300, "Unknown method", methodName));
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	private JsonRpcResponse createEmptyErrorResponse(String id) {
		JsonRpcResponse response = new JsonRpcResponse();
		response.setId(id);
		response.setJsonRpc(jsonRPC);
		return response;
	}
}