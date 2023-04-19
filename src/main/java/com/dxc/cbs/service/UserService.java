package com.dxc.cbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.UserDao;
import com.dxc.cbs.dao.UserLoginDao;
import com.dxc.cbs.model.Booking;
import com.dxc.cbs.model.User;
import com.dxc.cbs.model.UserLogin;

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
//	public User addUser(User u) {
//		User user = dao.insert(u);
//		return user;
//	}
	public List<User> getUser() {
		return dao.findAll();
	}
	
	public boolean deleteUser(int id) {
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            dao.delete(optionalUser.get());
            return true;
        }
        return false;
    }
	
	public boolean updateUser(int id, User updatedUser) {
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            dao.save(user);
            return true;
        }
        return false;
    }
	
	
	public Optional<User> getUserById(int id) {
	    return dao.findById(id);
	}
	
	public Optional<User> getUserByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public Optional<User> getUserByPassword(String password) {
		return dao.findByPassword(password);
	}
	
	public boolean getUserByEp(String email, String password) {
		Optional<User> optionalUser = dao.findByEmailAndPassword(email, password);
	    return optionalUser.isPresent();
    }
	
	public boolean createBooking(int id, Booking booking) {
	    Optional<User> optionalUser = dao.findById(id);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        List<Booking> bookings = user.getBookings();
	        bookings.add(booking);
	        user.setBookings(bookings);
	        dao.save(user);
	        return true;
	    }
	    return false;
	}
	
	public boolean validate(User user) {
		Optional<User> optUser = dao.findByEmail(user.getEmail());
		Optional<User> optPassword = dao.findByPassword(user.getPassword());
		if(optUser.isPresent() && optPassword.isPresent()) {
			return true;
		}
		return false;
	}
	
	
}
