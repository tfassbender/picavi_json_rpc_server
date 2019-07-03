package com.picavi.json_rpc_server.backend;

/**
 * An exception that is thrown if the login was not successful
 */
public class LoginException extends Exception {
	
	private static final long serialVersionUID = -387594122030224875L;
	
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}
	public LoginException(String message) {
		super(message);
	}
	public LoginException(Throwable cause) {
		super(cause);
	}
}