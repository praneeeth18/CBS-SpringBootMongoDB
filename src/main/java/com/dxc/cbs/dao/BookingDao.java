package com.dxc.cbs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.cbs.model.Booking;

public interface BookingDao extends MongoRepository<Booking, Integer> {
	List<Booking> findByEmail(String email);
}
