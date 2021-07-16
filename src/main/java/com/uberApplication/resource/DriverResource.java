package com.uberApplication.resource;

import java.util.List;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uberApplication.model.Driver;
import com.uberApplication.response.ResponseMessage;
import com.uberApplication.service.IDriver;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("uber/api/Driver/")
public class DriverResource {
	
	@Autowired
	private IDriver driverService;
	

	@ApiOperation("Listing all Drivers whether is requested or available")
	@GetMapping(value="drivers")
	public ResponseEntity<List<Driver>>findDriversByStatus(@PathParam("status")String status){
		try {
			List<Driver>driverList = driverService.findDriversByStatus(status);
			if(driverList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(driverList,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("return Driver by DriverId")
	@GetMapping(value="findDriverById")
	public ResponseEntity<?> findDriverById(@PathParam(value="driverId")int driverId){
		try {
			Driver driver = driverService.findDriverById(driverId);
			
			return new ResponseEntity<Driver>(driver,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation("return the Closest Driver are around you within any specific kilometers")
	@GetMapping(value="closestDriver")
	public ResponseEntity<?>findClosestDriverWithinKilometers(@PathParam(value="latitude")String latitude,@PathParam(value="longitude")String longitude,
			@PathParam(value="kilometer")double kilometer){
		try {
			List<Driver>list = driverService.findClosestDriver(latitude, longitude, kilometer);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation("Return all Drivers Sorted by kilometer Ascending so that you can pick the nearest you")
	@GetMapping(value="findNearestAvailableDriver")
	public ResponseEntity<?>findNearestDriver(@PathParam(value="latitude")String latitude,@PathParam(value="longitude")String longitude){
		try {
			List<Driver>list = driverService.findNearestDriver(latitude, longitude);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
