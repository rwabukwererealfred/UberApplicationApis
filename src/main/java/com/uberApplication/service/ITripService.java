package com.uberApplication.service;

import java.util.List;

import com.uberApplication.dto.ResponseDto;
import com.uberApplication.model.Trip;

public interface ITripService {

	public List<Trip>allTrip();
	public void tripRequested(int driverId);
	public void tripStarted(int tripId, String latitude, String longitude, String locationName);
	public ResponseDto tripCompleted(int tripId,String latitude, String longitude, String locationName);
	
}
