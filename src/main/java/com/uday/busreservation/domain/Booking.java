package com.uday.busreservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "Bus_id")
	private Bus bus;
	
	@ManyToOne()
	@JoinColumn(name = "User_id")
	private User user;
	private String seatnos;
	private int price;
	
	
	public Booking() {
		super();
	}


	public Booking(int id, Bus bus, User user, String seatnos, int price) {
		super();
		this.id = id;
		this.bus = bus;
		this.user = user;
		this.seatnos = seatnos;
		this.price = price;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Bus getBus() {
		return bus;
	}


	public void setBus(Bus bus) {
		this.bus = bus;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getSeatnos() {
		return seatnos;
	}


	public void setSeatnos(String seatnos) {
		this.seatnos = seatnos;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
