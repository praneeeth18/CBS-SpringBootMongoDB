package com.dxc.cbs.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.Admin;

public interface AdminDao extends MongoRepository<Admin, Integer> {
	
}
