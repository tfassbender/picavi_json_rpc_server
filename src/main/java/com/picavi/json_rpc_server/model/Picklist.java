package com.picavi.json_rpc_server.model;

import java.util.List;

public class Picklist {
	
	private String ident;
	private List<Pickline> lines;
	
	public Picklist() {
		
	}
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	public List<Pickline> getLines() {
		return lines;
	}
	public void setLines(List<Pickline> lines) {
		this.lines = lines;
	}
}