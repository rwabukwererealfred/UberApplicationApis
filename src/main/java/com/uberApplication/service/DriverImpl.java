package com.uberApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uberApplication.model.Driver;
import com.uberApplication.repository.DriverRepository;

@Service
public class DriverImpl implements IDriver {

	@Autowired
	private DriverRepository driverRepo;

	@Override
	public List<Driver> findDriversByStatus(String driverStatus) {
		List<Driver> list = new ArrayList<>();
		if (driverStatus == null || driverStatus.equals(" ")) {
			driverRepo.findAll().forEach(list::add);
		} else {
			driverRepo.findAll().stream().filter(i -> i.getStatus().equals(driverStatus)).forEach(list::add);
		}
		return list;
	}

	@Override
	public void createDriver(Driver driver) {
		Optional<Driver> d = driverRepo.findById(driver.getDriverId());
		driver.setStatus("Available");
		if (d.isPresent()) {
			throw new RuntimeException("Driver id is arleady exist");
		} else {
			driverRepo.save(driver);
		}
	}

	@Override
	public Driver findDriverById(int driverId) {
		Driver driver = driverRepo.findById(driverId).orElseThrow(() -> new RuntimeException("Driver id is not found"));
		return driver;
	}

	@Override
	public List<Driver> findClosestDriver(String latitude, String longitude, double kilometer) {
		String startP = latitude + "," + longitude;
		List<Driver> availableDriver = new ArrayList<>();
		driverRepo.findAll().stream().filter(i -> i.getStatus().equals("Available")).forEach(i -> {
			String[] location = i.getLocation();
			String endP = location[0] + "," + location[1];
			Double distance = new Calculation().CalculationByDistance(startP, endP);
			i.setDistance(distance);
			if (i.getDistance() <= kilometer) {
				availableDriver.add(i);
			}
		});
		return availableDriver.stream().sorted((x,y)->x.getDistance().compareTo(y.getDistance())).collect(Collectors.toList());
	}

	@Override
	public List<Driver> findNearestDriver(String latitude, String longitude) {
		String startP = latitude + "," + longitude;
		List<Driver> availableDriver = new ArrayList<>();
		driverRepo.findAll().stream().filter(i -> i.getStatus().equals("Available")).forEach(i -> {
			String[] location = i.getLocation();
			String endP = location[0] + "," + location[1];
			Double distance = new Calculation().CalculationByDistance(startP, endP);
			i.setDistance(distance);
			availableDriver.add(i);

		});
		return availableDriver.stream().sorted((x,y)->x.getDistance().compareTo(y.getDistance())).collect(Collectors.toList());
	}

}
