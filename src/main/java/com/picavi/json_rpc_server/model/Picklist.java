package com.picavi.json_rpc_server.model;

import java.util.ArrayList;
import java.util.List;

public class Picklist {
	
	private String ident;
	private List<Pickline> lines;
	
	public Picklist() {
		
	}
	
	@Override
	public String toString() {
		return "Picklist [ident=" + ident + ", lines=" + lines + "]";
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
	public void addLine(Pickline line) {
		if (lines == null) {
			lines = new ArrayList<Pickline>();
		}
		lines.add(line);
	}
	public void addLines(List<Pickline> lines) {
		if (this.lines == null) {
			this.lines = new ArrayList<Pickline>(lines.size());
		}
		this.lines.addAll(lines);
	}
}