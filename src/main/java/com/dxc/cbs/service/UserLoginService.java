package com.dxc.cbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.UserLoginDao;
import com.dxc.cbs.model.UserLogin;

@Service
public class UserLoginService {
	@Autowired
	UserLoginDao dao;
	
	public boolean addUser(UserLogin u) {
		UserLogin user = dao.save(u);
		if(user!=null) {
			return true;
		}
		return false;
	}
	
	public boolean validate(UserLogin user) {
		Optional<UserLogin> optUser = dao.findById(user.getEmail());
		Optional<UserLogin> optPassword = dao.findByPassword(user.getPassword());
 		if(optUser.isPresent() && optPassword.isPresent()) {
			return true;
		}
		return false;
	}
	
	public List<UserLogin> getAllUsers() {
		return dao.findAll();
	}
}
