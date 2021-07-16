package com.uberApplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Invoice")
public class Invoice {
	
	

	@Id
	private String invoiceId;
	private Double cost;
	private double distanceKilometer;
	
	private Trip trip;

	

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(Double cost, double distanceKilometer, Trip trip) {
		super();
		this.cost = cost;
		this.distanceKilometer = distanceKilometer;
		this.trip = trip;
	}

	public double getDistanceKilometer() {
		return distanceKilometer;
	}

	public void setDistanceKilometer(double distanceKilometer) {
		this.distanceKilometer = distanceKilometer;
	}

	
	
	
	
}
