package com.uberApplication.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "Driver")
@ApiModel(description = "Driver information model")
public class Driver {

	@Id
	@ApiModelProperty(notes = "Unique driver id")
	private int driverId;
	@ApiModelProperty(notes = "Driver first name")
	private String firstName;
	@ApiModelProperty(notes = "Driver last name")
	private String lastName;
	@ApiModelProperty(notes = "latitude, longitude and location name of driver")
	private String []location;
	@ApiModelProperty(notes = "Driver status")
	private String status;
	
	@Transient
	private Double distance;
	
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Driver(int driverId, String firstName, String lastName, String[] location, String status) {
		
		this.driverId = driverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
		this.status = status;
		
	}
	public Driver() {
		
	}
	
	
	
}
