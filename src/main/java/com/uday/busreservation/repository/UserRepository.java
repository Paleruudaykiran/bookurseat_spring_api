package com.uday.busreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.busreservation.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByName(String name);
}
