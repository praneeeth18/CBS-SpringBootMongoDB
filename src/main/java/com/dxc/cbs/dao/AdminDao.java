package com.dxc.cbs.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.Admin;


public interface AdminDao extends MongoRepository<Admin, Integer> {
	Optional<Admin> findByEmailAndPassword(String email, String password);
}
