package com.picavi.json_rpc_server.server;

import java.io.IOException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.common.annotations.VisibleForTesting;
import com.sun.net.httpserver.HttpServer;

public abstract class JsonRpcServer {
	
	private static final Logger LOGGER = LogManager.getLogger(JsonRpcServer.class);
	
	public static final String BASE_URI = "http://localhost:4711/";
	
	private static final String BASE_PACKAGE = "com.picavi.json_rpc_server.service";
	
	public static void main(String[] args) {
		HttpServer server = startServer();
		
		System.out.println("Press <Enter> to stop the server");
		try {
			System.in.read();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		//stop the server after the user has made an input
		LOGGER.info("Stopping server\n\n\n");
		server.stop(0);
		
		System.exit(0);
	}
	
	/**
	 * Start the server and wait for a user input to end the server again.
	 */
	private static HttpServer startServer() {
		ResourceConfig config = new ResourceConfig().packages(BASE_PACKAGE);
		HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
		//server.start() is called as side effect
		
		LOGGER.info("Starting server at {}", BASE_URI);
		return server;
	}
	
	/**
	 * Starts a server in a new thread so it can be stopped by interrupting the thread (for testing purposes)
	 * @return 
	 */
	@VisibleForTesting
	/*private*/ static HttpServer startTestServer() {
		HttpServer server = startServer();
		return server;
	}
}