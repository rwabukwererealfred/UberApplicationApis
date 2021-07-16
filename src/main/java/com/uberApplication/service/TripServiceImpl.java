package com.uberApplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberApplication.dto.ResponseDto;
import com.uberApplication.model.Driver;
import com.uberApplication.model.Invoice;
import com.uberApplication.model.Trip;
import com.uberApplication.repository.DriverRepository;
import com.uberApplication.repository.InvoiceRepository;
import com.uberApplication.repository.TripRepository;

@Service
public class TripServiceImpl implements ITripService {

	@Autowired
	private TripRepository tripRepo;

	@Autowired
	private DriverRepository driverRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Autowired
	private SequenceService service;

	@Override
	public List<Trip> allTrip() {
		return tripRepo.findAll();
	}

	@Override
	public void tripRequested(int driverId) {

		Driver driver = driverRepo.findById(driverId)
				.orElseThrow(() -> new RuntimeException("Driver Id does not exist"));
		if (driver.getStatus().equals("Available")) {
			Trip trip = new Trip();
			trip.setTripId(service.getSequenceNumber(trip.SEQUENCE_NAME));
			trip.setDriver(driver);
			trip.setStartTime(LocalDateTime.now());
			trip.setStatus("Requested");
			trip.setUsedKilometer(0.0);
			tripRepo.save(trip);
			driver.setStatus("Requested");
			driverRepo.save(driver);
		} else {
			throw new RuntimeException("Driver is arleady Requested please try another one");
		}
	}

	@Override
	public void tripStarted(int tripId, String latitude, String longitude, String locationName) {
		Trip trip = tripRepo.findById(tripId).orElseThrow(() -> new RuntimeException("Trip Id does not exist"));
		String[] location = { latitude, longitude, locationName };
		if(location[0] != null && location[1]!=null && location[2] != null) {
		trip.setStartPoint(location);
		trip.setStatus("Started");
		trip.setStartTime(LocalDateTime.now());
		trip.setUsedKilometer(0.0);
		tripRepo.save(trip);
		Driver driver = driverRepo.findById(trip.getDriver().getDriverId()).get();
		driver.setLocation(location);
		driverRepo.save(driver);
		}else {
			throw new RuntimeException("All information is required! (latitude, longitude and locationName)");
		}
	}

	@Override
	public ResponseDto tripCompleted(int tripId, String latitude, String longitude, String locationName) {
		Trip trip = tripRepo.findById(tripId).orElseThrow(() -> new RuntimeException("Trip Id does not exist"));
		String[] location = { latitude, longitude, locationName };

		trip.setEndPoint(location);
		trip.setStatus("Completed");
		String[] Start = trip.getStartPoint();

		String startPoint = Start[0] + "," + Start[1];
		String endPoint = latitude + "," + longitude;
		Double distance = new Calculation().CalculationByDistance(startPoint, endPoint);
		trip.setUsedKilometer(distance);
		trip.setEndTime(LocalDateTime.now());
		trip.setUrl("http://localhost:8080/uber/api/Trip/invoice/" + trip.getTripId());
		tripRepo.save(trip);
		Driver driver = driverRepo.findById(trip.getDriver().getDriverId()).get();
		driver.setLocation(location);
		driver.setStatus("Available");
		driverRepo.save(driver);

		Invoice invoice = new Invoice();
		invoice.setInvoiceId(UUID.randomUUID().toString());
		invoice.setCost(distance * 1000);
		invoice.setTrip(trip);
		invoice.setDistanceKilometer(distance);
		invoiceRepo.save(invoice);
		
		ResponseDto response = new ResponseDto();
		response.setMessage("Trip is well successfull Completed, please open url on browser to print invoice");
		response.setUrl(trip.getUrl());
		return response;

	}

}
