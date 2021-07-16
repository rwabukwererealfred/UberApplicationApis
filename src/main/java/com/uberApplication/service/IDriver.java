package com.uberApplication.service;

import java.util.List;

import com.uberApplication.model.Driver;

public interface IDriver {

	public List<Driver>findDriversByStatus(String driverStatus);
	public void createDriver(Driver driver);
	public Driver findDriverById(int driverId);
	public List<Driver>findClosestDriver(String latitude, String longitude,double kilometer);
	public List<Driver>findNearestDriver(String latitude, String longitude);
}
