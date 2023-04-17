package com.dxc.cbs.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.Booking;

public interface BookingDao extends MongoRepository<Booking, Integer> {

}
