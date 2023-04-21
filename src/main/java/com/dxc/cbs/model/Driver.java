package com.dxc.cbs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="Driver")
public class Driver {
	@Id
	public int driverId;
	public String name;
	public String email;
	public String password;
	public String vehicleno;
//	public String phone;
	public Driver() {
		
	}
	public Driver(int driverId, String name, String email, String password, String vehicleno) {
		this.driverId = driverId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.vehicleno = vehicleno;
	}


	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	
	
}
