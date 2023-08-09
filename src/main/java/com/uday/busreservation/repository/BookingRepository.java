package com.uday.busreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uday.busreservation.domain.Booking;
import com.uday.busreservation.domain.Bus;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	List<Booking> findByBusId(int id);
	List<Booking> findByUserId(int id);
}
