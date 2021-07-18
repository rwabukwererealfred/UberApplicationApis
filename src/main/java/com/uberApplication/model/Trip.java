package com.uberApplication.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



@Document(collection = "Trip")
@ApiModel(description = "Trip information model")
public class Trip {
	
	@Transient
	public static final String SEQUENCE_NAME = "user_sequence";

	@Id
	@ApiModelProperty(notes = "Unique Trip id will Generated automatically")
	private int tripId;
	@ApiModelProperty(notes = "Start movement time should generated automatically")
	private LocalDateTime startTime;
	@ApiModelProperty(notes = " movement Ending time should alse generated automatically after trip is completed")
	private LocalDateTime endTime;
	@ApiModelProperty(notes = "Location start point(latitude, longitude and location name)")
	private String[] startPoint;
	@ApiModelProperty(notes = "Location end point(latitude, longitude and location name)")
	private String[] endPoint;
	@ApiModelProperty(notes = "Trip Status")
	private String status;
	@ApiModelProperty(notes = "Url for invoice")
	private String url;
	
	@Transient
	@ApiModelProperty(notes = "Kilometer used after trip is completed")
	private Double usedKilometer;
	
	@ApiModelProperty(notes = "Requested Driver")
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
