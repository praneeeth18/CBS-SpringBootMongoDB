package com.dxc.cbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.cbs.dao.BookingDao;
import com.dxc.cbs.model.Booking;

@Service
public class BookingService {
	@Autowired
	BookingDao dao;
	
	public boolean addBooking(Booking b) {
		Booking booking = dao.insert(b);
		if(booking != null) {
			return true;
		}
		return false;
	}
	
	public List<Booking> getBooking() {
		return dao.findAll();
	}
	
	public boolean deleteBooking(int id) {
		Optional<Booking> optionalBooking = dao.findById(id);
		if(optionalBooking.isPresent()) {
			dao.delete(optionalBooking.get());
			return true;
		}
		return false;
	}
	
	public Optional<Booking> getBookingById(int id) {
		return dao.findById(id);
	}
}
