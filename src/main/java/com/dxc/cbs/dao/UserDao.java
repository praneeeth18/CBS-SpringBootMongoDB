package com.dxc.cbs.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.User;

public interface UserDao extends MongoRepository<User, Integer> {

}
