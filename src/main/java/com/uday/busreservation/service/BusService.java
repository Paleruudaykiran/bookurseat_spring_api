package com.uday.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uday.busreservation.domain.Bus;
import com.uday.busreservation.repository.BusRepository;

@Service
public class BusService {

	@Autowired
	private BusRepository repo;
	
	public List<Bus> listAll() {
		return repo.findAll();
	}
	public void save(Bus bus) {
		repo.save(bus);
	}
	public Bus get(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	
}
