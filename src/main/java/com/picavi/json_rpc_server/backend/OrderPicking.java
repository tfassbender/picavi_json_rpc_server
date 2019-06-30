package com.picavi.json_rpc_server.backend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.picavi.json_rpc_server.model.Pickline;
import com.picavi.json_rpc_server.model.Picklist;

public class OrderPicking {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderPicking.class);
	
	private static OrderPicking instance;
	
	private OrderPicking() {
		//empty private constructor for the singleton pattern
	}
	
	/**
	 * Get the singleton instance of OrderPicking
	 */
	public static synchronized OrderPicking getInstance() {
		if (instance == null) {
			instance = new OrderPicking();
		}
		return instance;
	}
	
	/**
	 * Get a picklist from the database
	 */
	public Picklist getPickList(String sessionId, String pickingListIdent) {
		LOGGER.info("Creating picklist; sessionId: {}, ident: {}", sessionId, pickingListIdent);
		//get a picklist from a database or any other cool solution...
		
		//or return a dummy picklist because there is no database...
		Picklist picklist = new Picklist();
		picklist.addLine(new Pickline("1", "somewhere", "unicorn", 42, "a_unit", "fluffy"));
		picklist.addLine(new Pickline("2", "somewhere_else", "Beer", Double.POSITIVE_INFINITY, "Barels", "cold"));
		picklist.addLine(new Pickline("3", "Coruscant", "Lightsaber", 5, "picks", "cool"));
		
		//return the (completely serious) picklist
		return picklist;
	}
}