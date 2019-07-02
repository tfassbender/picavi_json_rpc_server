package com.picavi.json_rpc_server.backend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemAutentification {
	
	private Logger LOGGER = LogManager.getLogger(SystemAutentification.class);
	
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
	 *         If the user can't be logged in an {@link LoginException} is thrown.
	 */
	public String login(String user, String password) throws LoginException {
		LOGGER.info("Trying to login user: {}", user);
		//do some secure login stuff here
		
		//return the session id to the logged in user
		String sessionId = generateSessionId();
		LOGGER.info("User {} was logged in; sessionId: {}", user, sessionId);
		return sessionId;
	}
	
	/**
	 * Logout a user
	 */
	public boolean logout(String sessionId) throws LoginException {
		LOGGER.info("Logging out user; sessionId: {}", sessionId);
		//do some secure logout stuff here
		return true;
	}
	
	/**
	 * Create a session id for a user that is logging in
	 */
	private String generateSessionId() {
		//create a secure and well chosen session id here
		
		//or just create a random session id...
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		final int length = 20;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chars.charAt((int) (Math.random() * chars.length())));
		}
		return sb.toString();
	}
}