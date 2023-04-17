package com.dxc.cbs.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.cbs.dao.UserDao;
import com.dxc.cbs.dao.UserLoginDao;
import com.dxc.cbs.model.Booking;
import com.dxc.cbs.model.User;
import com.dxc.cbs.model.UserLogin;
import com.dxc.cbs.service.SequenceGeneratorService;
import com.dxc.cbs.service.UserLoginService;
import com.dxc.cbs.service.UserService;



import static com.dxc.cbs.model.User.*;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserController {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserLoginDao loginDao;
	
	@Autowired
	UserService service;
	@Autowired
	UserLoginService loginService;
	ResponseEntity response;
	boolean flag;
	
	
	@Autowired
	private SequenceGeneratorService sequenceService;
	
//	@PostMapping("/addUser")
//	public ResponseEntity<?> addUser(@RequestBody User user) {
//		user.setUserId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
//		flag = service.addUser(user);
//		if(flag) {
//			response = new ResponseEntity<String>("User object is created", HttpStatus.CREATED);
//		}
//		else {
//			response = new ResponseEntity<String>("User object creation is failed", HttpStatus.BAD_REQUEST);
//		}
//		return response;
//	}
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		user.setUserId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
		flag = service.addUser(user);
		if (flag) {
	        user.setUserId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
	        UserLogin userLogin = new UserLogin();
	        userLogin.setEmail(user.getEmail());
	        userLogin.setPassword(user.getPassword());
	        loginService.addUser(userLogin);
	        return new ResponseEntity<>(user, HttpStatus.CREATED);
	    } else {
	        return new ResponseEntity<>("User object creation failed", HttpStatus.BAD_REQUEST);
	    }
	}
	
	
	
//	@PostMapping("/addUser")
//	public User addUser(@RequestBody User user) {
//		user.setUserId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
//		return service.addUser(user);
//	}
	
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
	
	@DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean isDeleted = service.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<>("User object with ID " + id + " has been deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("User object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
	
//	@DeleteMapping("/users/{id}")
//	public ResponseEntity<?> deleteUser(@PathVariable int id) {
//	    try {
//	        boolean isDeleted = service.deleteUser(id);
//	        if (isDeleted) {
//	            return ResponseEntity.ok("User object with ID " + id + " has been deleted");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User object with ID " + id + " does not exist");
//	        }
//	    } catch (Exception e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user object with ID " + id);
//	    }
//	}

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        boolean isUpdated = service.updateUser(id, updatedUser);
        if (isUpdated) {
            return new ResponseEntity<>("User object with ID " + id + " has been updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<User> optionalUser = service.getUserById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/users/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
    	Optional<User> optionalUser = service.getUserByEmail(email);
    	if(optionalUser.isPresent()) {
    		return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    	}
    	else {
    		return new ResponseEntity<>("User object with email" + email + "does not exit", HttpStatus.NOT_FOUND);
    	}
    }
    
    @PostMapping("/users/{id}/bookings")
    public ResponseEntity<?> createBooking(@PathVariable int id, @RequestBody Booking booking) {
    	boolean success = service.createBooking(id, booking);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/users/{id}/bookings")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable int id) {
    	Optional<User> optionalUser = service.getUserById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        User user = optionalUser.get();
        List<Booking> bookings = user.getBookings();
        return ResponseEntity.ok(bookings);
    }
}
