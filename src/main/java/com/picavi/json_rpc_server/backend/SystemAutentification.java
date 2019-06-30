package com.picavi.json_rpc_server.backend;

import java.nio.charset.Charset;
import java.util.Random;

public class SystemAutentification {
	
	private static SystemAutentification instance;
	
	private SystemAutentification() {
		//empty private constructor for the singleton pattern
	}
	
	/**
	 * Get a singleton instance of the SystemProcessor
	 */
	public static synchronized SystemAutentification getInstance() {
		if (instance == null) {
			instance = new SystemAutentification();
		}
		return instance;
	}
	
	/**
	 * Login a user and return the session id
	 * 
	 * @throws LoginException
	 * 		If the user can't be logged in an {@link LoginException} is thrown.
	 */
	public String login(String user, String password) throws LoginException {
		//do some secure login stuff here
		
		//return the session id to the logged in user
		String sessionId = generateSessionId();
		return sessionId;
	}
	
	/**
	 * Logout a user
	 */
	public boolean logout(String sessionId) {
		//do some secure logout stuff here
		return true;
	}
	
	/**
	 * Create a session id for a user that is logging in
	 */
	private String generateSessionId() {
		//create a secure and well chosen session id here
		
		//or just create a random session id...
		byte[] chars = new byte[20];
		new Random().nextBytes(chars);
		String generatedString = new String(chars, Charset.forName("UTF-8"));
		return generatedString;
	}
}