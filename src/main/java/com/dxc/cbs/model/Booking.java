package com.dxc.cbs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Booking {
	@Transient
	public static final String SEQUENCE_NAME = "booking_sequence";
	@Id
	private int bookingId;
	private String source;
	private String destination;
	private double price;
	private String carModel;
	public Booking() {
		
	}
	public Booking(String source, String destination, double price, String carModel) {
		this.source = source;
		this.destination = destination;
		this.price = price;
		this.carModel = carModel;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", source=" + source + ", destination=" + destination + ", price="
				+ price + ", carModel=" + carModel + "]";
	}
}
