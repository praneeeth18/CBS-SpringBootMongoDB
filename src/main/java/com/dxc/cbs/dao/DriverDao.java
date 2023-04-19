package com.dxc.cbs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.Driver;


public interface DriverDao extends MongoRepository<Driver, Integer> {
	List<Driver> findByEmail(String email);
	Optional<Driver> findByEmailAndPassword(String email, String password);
}
