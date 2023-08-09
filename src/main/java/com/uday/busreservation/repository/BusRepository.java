package com.uday.busreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uday.busreservation.domain.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{

}
