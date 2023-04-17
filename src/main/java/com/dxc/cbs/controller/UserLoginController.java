package com.dxc.cbs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.cbs.dao.UserLoginDao;
import com.dxc.cbs.model.UserLogin;
import com.dxc.cbs.service.UserLoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserLoginController {
	
	@Autowired
	UserLoginService loginService;
	
	ResponseEntity response;
	boolean flag;
	
	String jwtToken;
	
	@PostMapping("/addUserLogin")
	public ResponseEntity<?> addUser(@RequestBody UserLogin user) {
		flag = loginService.addUser(user);
		if(flag) {
			response = new ResponseEntity<String>("customer object created", HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<String>("customer object failed to create", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	 public String generateToken(String email) {
			String token = Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+200000))
					       .signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			System.out.println("Token: "+token);
			return token;
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody UserLogin user, HttpSession session) {
	    	flag = loginService.validate(user);
	    	if(flag) {
	    		session.setAttribute("email", user.getEmail());
	    		jwtToken = generateToken((user.getEmail()));
	    		response = new ResponseEntity<String>(jwtToken, HttpStatus.OK);
	    	}
	    	else {
	    		response = new ResponseEntity<String>("Failure ", HttpStatus.CONFLICT);
	    	}
	    	return response;
	    }
	    
	    @GetMapping("/logout")
	    public ResponseEntity<?> logout(HttpSession session) {
	    	if((session!=null) && (session.getAttribute("email") != null)) {
	    		session.invalidate();
	    		response = new ResponseEntity<String>("Logout Successfully!", HttpStatus.OK);
	    	}
	    	else {
	    		response = new ResponseEntity<String>("Failure ", HttpStatus.CONFLICT);
			}
			return response;
	    }
}
