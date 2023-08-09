package com.uday.busreservation.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String travelsName;
	private String boards;
	private String stops;
	private int seats;
	private int price;
	
	public Bus() {
		super();
	}
	public Bus(int id, String travelsName, String boards, String stops, int seats, int price) {
		super();
		this.id = id;
		this.travelsName = travelsName;
		this.boards = boards;
		this.stops = stops;
		this.seats = seats;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTravelsName() {
		return travelsName;
	}
	public void setTravelsName(String travelsName) {
		this.travelsName = travelsName;
	}
	public String getBoards() {
		return boards;
	}
	public void setBoards(String boards) {
		this.boards = boards;
	}
	public String getStops() {
		return stops;
	}
	public void setStops(String stops) {
		this.stops = stops;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}

