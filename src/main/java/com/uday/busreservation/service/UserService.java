package com.uday.busreservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uday.busreservation.domain.User;
import com.uday.busreservation.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> listAll() {
		return repo.findAll();
	}
	public void save(User user) {
		repo.save(user);
	}
	public User get(int id) {
		return repo.findById(id).get();
	}
	public void delete(int id) {
		repo.deleteById(id);
	}
	public User findByName(String name) {
		return repo.findByName(name);
	}
}
