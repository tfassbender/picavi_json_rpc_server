package com.picavi.json_rpc_server.backend;

public class PickingException extends Exception {
	
	private static final long serialVersionUID = 406404220847410667L;
	
	public PickingException() {
		super();
	}
	
	public PickingException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PickingException(String message) {
		super(message);
	}
}