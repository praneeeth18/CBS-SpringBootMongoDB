package com.dxc.cbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.DriverDao;
import com.dxc.cbs.model.Driver;

@Service
public class DriverService {
	@Autowired
	DriverDao dao;
	
	public boolean addDriver(Driver d) {
		Driver driver = dao.insert(d);
		if(driver != null) {
			return true;
		}
		return false;
	}
	
	public List<Driver> getDriver() {
		return dao.findAll();
	}
	
	public boolean deleteDriver(int id) {
		Optional<Driver> optionalDriver = dao.findById(id);
		if(optionalDriver.isPresent()) {
			dao.delete(optionalDriver.get());
			return true;
		}
		return false;
	}
	
	public Optional<Driver> getBookingById(int id) {
		return dao.findById(id);
	}
	
	public List<Driver> getUserByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public boolean getDriverByEp(String email, String password) {
		Optional<Driver> optionalDriver = dao.findByEmailAndPassword(email, password);
		return optionalDriver.isPresent();
	}

}
