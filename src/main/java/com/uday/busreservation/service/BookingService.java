package com.uday.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.busreservation.domain.Booking;
import com.uday.busreservation.domain.Bus;
import com.uday.busreservation.repository.BookingRepository;
import com.uday.busreservation.repository.BusRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository repo;
	
	public List<Booking> listAll() {
		return repo.findAll();
	}
	public Booking save(Booking booking) {
		return repo.save(booking);
	}
	public Booking get(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	public List<Booking> getByBusId(int id) {
		return repo.findByBusId(id);
	}
	public List<Booking> getByUserId(int id) {
		return repo.findByUserId(id);
	}
}
