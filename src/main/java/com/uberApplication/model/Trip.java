package com.uberApplication.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Trip")
public class Trip {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";

	@Id
	private int tripId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	private String[] startPoint;
	private String[] endPoint;
	private String status;
	private String url;
	
	@Transient
	private Double usedKilometer;
	

	private Driver driver;
	
	
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	public String[] getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(String[] startPoint) {
		this.startPoint = startPoint;
	}
	public String[] getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String[] endPoint) {
		this.endPoint = endPoint;
	}
	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Double getUsedKilometer() {
		return usedKilometer;
	}
	public void setUsedKilometer(Double usedKilometer) {
		this.usedKilometer = usedKilometer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
