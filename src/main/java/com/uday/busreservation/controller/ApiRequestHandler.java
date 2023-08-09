package com.uday.busreservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uday.busreservation.domain.Booking;
import com.uday.busreservation.domain.Bus;
import com.uday.busreservation.domain.User;
import com.uday.busreservation.service.BookingService;
import com.uday.busreservation.service.BusService;
import com.uday.busreservation.service.UserService;

@RequestMapping("api")
@RestController
@CrossOrigin
public class ApiRequestHandler {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("saveUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		System.out.println("requestbody" + user);
		userService.save(user);
		return new ResponseEntity<>("user Saved",HttpStatus.OK);
	}
	@GetMapping("getByName")
	public ResponseEntity<User> getByName(@RequestParam String name) {
		User user = userService.findByName(name);
		if(user!=null)
			return new ResponseEntity<>(user,HttpStatus.OK);
		else
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	@GetMapping("getAllBuses")
	public ResponseEntity<List> getAllBuses() {
		List<Bus> buses = busService.listAll();
		return new ResponseEntity<>(buses,HttpStatus.OK);
	}
	@PostMapping("saveBus")
	public ResponseEntity<String> saveBus(@RequestBody Bus bus){
		busService.save(bus);
		return new ResponseEntity<>("Bus Details saved",HttpStatus.OK);
	}
	@GetMapping("deleteBus")
	public ResponseEntity<String> deleteBus(@RequestParam int id){
		busService.delete(id);
		return new ResponseEntity<>("Bus Details deleted",HttpStatus.OK);
	}
	@GetMapping("confirmBooking")
	public ResponseEntity<Booking> confirmBooking(@RequestParam int uid,@RequestParam int bid,@RequestParam String seats){
//		System.out.println(uid + " " + bid + " " + seats);
		Booking booking = new Booking();
		User user = userService.get(uid);
		Bus bus = busService.get(bid);
		booking.setUser(user);
		booking.setBus(bus);
		String seatnos = "";
		int ct = 1;
		for(int i=0;i<seats.length();i++) {
			if(seats.charAt(i) == ',') {
				seatnos = seatnos + ' ';
				ct += 1;
			}else {
				seatnos += seats.charAt(i);
			}
		}
		booking.setSeatnos(seatnos);
		bus.setSeats(bus.getSeats()-ct);
		int price = ct * bus.getPrice();
		booking.setPrice(price);
		booking = bookingService.save(booking);
		System.out.println(booking);
		return new ResponseEntity<>(booking,HttpStatus.OK);
	}
	@PostMapping("getBookings")
	public ResponseEntity<List<Booking>> getBookings(@RequestBody Bus bus) {
		List<Booking> bookings = bookingService.getByBusId(bus.getId());
		String seats = "";
		for(Booking b:bookings) {
			System.out.println(b);
			seats = seats + b.getSeatnos() + " ";
		}
		System.out.println(seats);
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
	@PostMapping("getBookingsByUser")
	public ResponseEntity<List<Booking>> getBookingsByUser(@RequestBody User user) {
		List<Booking> bookings = bookingService.getByUserId(user.getId());
		return new ResponseEntity<>(bookings,HttpStatus.OK);
	}
}
