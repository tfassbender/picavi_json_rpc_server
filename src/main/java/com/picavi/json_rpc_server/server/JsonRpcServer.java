package com.picavi.json_rpc_server.server;

import java.io.IOException;
import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class JsonRpcServer {
	
	private static final Logger LOGGER = LogManager.getLogger(JsonRpcServer.class);
	
	private static final String BASE_URI = "http://localhost:4711/";
	private static final String BASE_PACKAGE = "rest.jersey.server.basics";
	
	public static void main(String[] args) {
		ResourceConfig config = new ResourceConfig().packages(BASE_PACKAGE);
		HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
		
		LOGGER.info("Starting server at {}", BASE_URI);
		//server.start() is called as side effect
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
	}
}