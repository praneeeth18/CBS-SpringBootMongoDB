package com.dxc.cbs.controller;

import static com.dxc.cbs.model.User.SEQUENCE_NAME;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.cbs.model.Booking;
import com.dxc.cbs.service.BookingService;
import com.dxc.cbs.service.SequenceGeneratorService;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class BookingController {
	@Autowired
	BookingService service;
	ResponseEntity response;
	boolean flag;
	
	@Autowired
	private SequenceGeneratorService sequenceService;
	
	@PostMapping("/addBooking")
	public ResponseEntity<?> addBooking(@RequestBody Booking booking) {
		booking.setBookingId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
		flag = service.addBooking(booking);
		if(flag) {
			booking.setBookingId(sequenceService.getSequenceNumber(SEQUENCE_NAME));
			return new ResponseEntity<>(booking, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Booking object creation failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/bookings")
	public ResponseEntity<?> getBooking() {
		List<Booking> bookingList = service.getBooking();
		if(bookingList!=null) {
			response = new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<?> getBookingById(@PathVariable int id) {
		Optional<Booking> optionalBooking = service.getBookingById(id);
		if(optionalBooking.isPresent()) {
			return new ResponseEntity<>(optionalBooking.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Booking object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		boolean isDeleted = service.deleteBooking(id);
		if(isDeleted) {
			return new ResponseEntity<>("Booking object with ID " + id + " has been deleted", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>("Booking object with ID " + id + " does not exist", HttpStatus.NOT_FOUND);
		}
	}
	
}
