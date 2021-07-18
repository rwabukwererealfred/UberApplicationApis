package com.uberApplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "Invoice")
public class Invoice {
	
	

	@Id
	@ApiModelProperty(notes = "Unique invoice id")
	private String invoiceId;
	@ApiModelProperty(notes = "Trip Cost will be calculated Automatically( 1kilometer = 1000 rwf)")
	private Double cost;
	@ApiModelProperty(notes = "Distance Kilometer are used")
	private double distanceKilometer;
	
	@ApiModelProperty(notes = "Trip information")
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
