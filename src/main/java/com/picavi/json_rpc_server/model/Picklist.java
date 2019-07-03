package com.picavi.json_rpc_server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Picklist {
	
	private String ident;
	private List<Pickline> lines;
	
	public Picklist() {
		//default constructor for JSON serialization
	}
	
	@Override
	public String toString() {
		return "Picklist [ident=" + ident + ", lines=" + lines + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		result = prime * result + ((lines == null) ? 0 : lines.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Picklist other = (Picklist) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		}
		else if (!ident.equals(other.ident))
			return false;
		if (lines == null) {
			if (other.lines != null)
				return false;
		}
		else if (!lines.equals(other.lines))
			return false;
		return true;
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
	
	public static Picklist fromParameters(Object params) {
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = (Map<String, Object>) params;
		
		Picklist list = new Picklist();
		list.setIdent((String) parameterMap.get("ident"));
		
		@SuppressWarnings("unchecked")
		List<Object> lineList = (List<Object>) parameterMap.get("lines");
		List<Pickline> lines = lineList.stream().map(l -> Pickline.fromParameters(l)).collect(Collectors.toList());
		list.setLines(lines);
		
		return list;
	}
}