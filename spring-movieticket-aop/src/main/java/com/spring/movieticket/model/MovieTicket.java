package com.spring.movieticket.model;

public class MovieTicket {
	private long id;
	private String movieName;
	private float price;
	private int totalTicketsCount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getTotalTicketsCount() {
		return totalTicketsCount;
	}
	public void setTotalTicketsCount(int totalTicketsCount) {
		this.totalTicketsCount = totalTicketsCount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
