package com.picavi.json_rpc_server.model;

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
}