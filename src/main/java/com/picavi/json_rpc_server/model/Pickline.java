package com.picavi.json_rpc_server.model;

import java.util.Map;

public class Pickline {
	
	private String ident;
	private String location;
	private String item;
	private double quantity;
	private String unit;
	private String status;
	
	public Pickline() {
		//default constructor for JSON serialization
	}
	
	public Pickline(String ident, String location, String item, double quantity, String unit, String status) {
		this.ident = ident;
		this.location = location;
		this.item = item;
		this.quantity = quantity;
		this.unit = unit;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Pickline [ident=" + ident + ", location=" + location + ", item=" + item + ", quantity=" + quantity + ", unit=" + unit + ", status="
				+ status + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ident == null) ? 0 : ident.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		Pickline other = (Pickline) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		}
		else if (!ident.equals(other.ident))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		}
		else if (!item.equals(other.item))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		}
		else if (!location.equals(other.location))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		}
		else if (!status.equals(other.status))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		}
		else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static Pickline fromParameters(Object params) {
		//assume the params Object is a Map, because it's deserialized this way
		@SuppressWarnings("unchecked")
		Map<String, Object> parameterMap = (Map<String, Object>) params;

		Pickline line = new Pickline();
		line.setIdent((String) parameterMap.get("ident"));
		line.setLocation((String) parameterMap.get("location"));
		line.setItem((String) parameterMap.get("item"));
		line.setQuantity((Double) parameterMap.get("quantity"));
		line.setStatus((String) parameterMap.get("status")); 
		
		return line;
	}
}