package com.dxc.cbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.UserDao;
import com.dxc.cbs.model.User;

@Service
public class UserService {
	@Autowired
	UserDao dao;
	public boolean addUser(User u) {
		User user = dao.insert(u);
		if(user != null) {
			return true;
		}
		return false;
	}
	public List<User> getUser() {
		return dao.findAll();
	}
}
