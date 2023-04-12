package com.dxc.cbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.cbs.model.User;
import com.dxc.cbs.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserController {
	@Autowired
	UserService service;
	ResponseEntity response;
	boolean flag;
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		flag = service.addUser(user);
		if(flag) {
			response = new ResponseEntity<String>("User object is created", HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<String>("User object creation is failed", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	@GetMapping("/users")
	public ResponseEntity<?> getUser() {
		List<User> userList = service.getUser();
		if(userList!=null) {
			response = new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
}
