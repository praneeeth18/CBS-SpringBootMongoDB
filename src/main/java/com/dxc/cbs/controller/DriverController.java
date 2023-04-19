package com.dxc.cbs.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.cbs.dao.DriverDao;
import com.dxc.cbs.model.Driver;
import com.dxc.cbs.service.DriverService;


@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class DriverController {
	@Autowired
	private DriverDao dao;
	@Autowired
	DriverService service;
	ResponseEntity response;
	boolean flag;
//	@Autowired
//	private SequenceGeneratorService sequenceService;
	
	@PostMapping("/addDriver") 
	public ResponseEntity<?> addDriver(@RequestBody Driver driver) {
//		driver.setBookingId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
		flag = service.addDriver(driver);
		if(flag) {
//			driver.setBookingId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
			return new ResponseEntity<>(driver, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Booking object creation failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/driverDetails")
	public ResponseEntity<?> getDriver() {
		List<Driver> driverList = service.getDriver();
		if(driverList!=null) {
			response = new ResponseEntity<List<Driver>>(driverList, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@DeleteMapping("/driverDetails/{id}")
	public ResponseEntity<?> deleteDriver(@PathVariable int id) {
		boolean isDeleted = service.deleteDriver(id);
		if(isDeleted) {
			return new ResponseEntity<>("Booking object with ID " + id + " has been deleted", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Booking object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/driverDetails/{email}/{password}")
	public ResponseEntity<?> getDriverByEP(@PathVariable String email, @PathVariable String password) {
    	boolean driverExists = service.getDriverByEp(email, password);
        if (driverExists) {
        	return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
}
