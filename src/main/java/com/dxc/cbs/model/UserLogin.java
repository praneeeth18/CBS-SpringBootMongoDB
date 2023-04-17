package com.dxc.cbs.model;


import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UserLogin {
	@Id
	@Column(columnDefinition = "varchar(40)")
	private String email;
	private String password;
	public UserLogin() {
		
	}
	public UserLogin(String email, String password) {
		this.email = email;
		this.password = password;
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
	@Override
	public String toString() {
		return "UserLogin [email=" + email + ", password=" + password + "]";
	}
}
