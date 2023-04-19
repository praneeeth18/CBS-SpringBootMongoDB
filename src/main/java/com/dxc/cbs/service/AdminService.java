package com.dxc.cbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.AdminDao;
import com.dxc.cbs.model.Admin;

@Service
public class AdminService {
	@Autowired
	AdminDao dao;
	
	public boolean addAdmin(Admin a) {
		Admin admin = dao.insert(a);
		if(admin!=null) {
			return true;
		}
		return false;
	}
	
	public List<Admin> getAdmin() {
		return dao.findAll();
	}
	
	public boolean getUserByEp(String email, String password) {
		Optional<Admin> optionalUser = dao.findByEmailAndPassword(email, password);
	    return optionalUser.isPresent();
    }
	
	public boolean deleteAdmin(int id) {
        Optional<Admin> optionalAdmin = dao.findById(id);
        if (optionalAdmin.isPresent()) {
            dao.delete(optionalAdmin.get());
            return true;
        }
        return false;
    }
 }
