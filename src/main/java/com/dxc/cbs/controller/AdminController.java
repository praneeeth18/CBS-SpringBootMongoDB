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

import com.dxc.cbs.model.Admin;
import com.dxc.cbs.service.AdminService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class AdminController {
	@Autowired
	AdminService adminService;
	ResponseEntity response;
	boolean flag;
	
	@PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin) {
//		admin.setAdminId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
		flag = adminService.addAdmin(admin);
		if (flag) {
//	        admin.setAdminId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
	        return new ResponseEntity<>(admin, HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("User object creation failed", HttpStatus.BAD_REQUEST);
	    }
	}
	
	@GetMapping("/adminDetails")
	public ResponseEntity<?> getAdmin() {
		List<Admin> adminList = adminService.getAdmin();
		if(adminList!=null) {
			response = new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/adminDetails/{email}/{password}")
    public ResponseEntity<?> getUserByEP(@PathVariable String email, @PathVariable String password) {
        boolean adminExists = adminService.getUserByEp(email, password);
        if (adminExists) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	 @DeleteMapping("/adminDetails/{id}")
	    public ResponseEntity<?> deleteAdmin(@PathVariable int id) {
	        boolean isDeleted = adminService.deleteAdmin(id);
	        if (isDeleted) {
	            return new ResponseEntity<>("User object with ID " + id + " has been deleted", HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>("User object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
	        }
	    }
}
