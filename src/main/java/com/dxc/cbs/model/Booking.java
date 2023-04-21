package com.dxc.cbs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bookings")
public class Booking {
	@Id
	private int bookingId;
	private String email;
	private String source;
	private String destination;
	private String cabModel;
	private double price;
	private String status;
//	private String driverName;
//	private String vehicleno;
//	private String phone;
	public Booking() {
		
	}
	public Booking(int bookingId,String email, String source, String destination, double price, String cabModel, String status) {
		this.bookingId = bookingId;
		this.email = email;
		this.source = source;
		this.destination = destination;
		this.cabModel = cabModel;
		this.price = price;
		this.status = status;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCabModel() {
		return cabModel;
	}
	public void setCabModel(String cabModel) {
		this.cabModel = cabModel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", email=" + email + ", source=" + source + ", destination=" + destination + ", cabModel="
				+ cabModel + ", price=" + price + "]";
	}
	
	
	
	
	
	
}
