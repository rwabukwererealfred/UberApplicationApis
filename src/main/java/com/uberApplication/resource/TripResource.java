package com.uberApplication.resource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uberApplication.dto.ResponseDto;
import com.uberApplication.model.Invoice;
import com.uberApplication.model.Trip;
import com.uberApplication.response.ResponseMessage;
import com.uberApplication.service.IInvoice;
import com.uberApplication.service.ITripService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "uber/api/Trip/")
public class TripResource {

	@Autowired
	private ITripService tripService;

	@Autowired
	private IInvoice invoiceService;

	@ApiOperation("Request a driver using Driver Id")
	@PostMapping(value = "createTrip")
	public ResponseEntity<?> createTrip(@RequestParam("driverId") int driverId) {
		try {
			tripService.tripRequested(driverId);
			return new ResponseEntity<>(new ResponseMessage("Well successfull Requested"), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation("Return all Trip are Pending")
	@GetMapping(value = "requestedTrip")
	public ResponseEntity<List<Trip>> requestedTrip() {
		try {
			List<Trip> list = tripService.allTrip().stream().filter(i -> i.getStatus().equals("Requested"))
					.collect(Collectors.toList());
			if (list.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation("Here is to start trip by provinding tripId, startPointLatitude,startPointLongitude and Location Name")
	@PutMapping(value = "startTrip")
	public ResponseEntity<?> startTrip(@RequestParam("tripId") int tripId, @RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude, @RequestParam("locationNameStartPoint") String locationNameStartPoint) {

		try {
			tripService.tripStarted(tripId, latitude, longitude, locationNameStartPoint);
			return new ResponseEntity<>(new ResponseMessage("Well successfull Started"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation("Return all active Trip")
	@GetMapping(value = "startedTrip")
	public ResponseEntity<List<Trip>> startedTrip() {
		try {
			List<Trip> list = tripService.allTrip().stream().filter(i -> i.getStatus().equals("Started"))
					.collect(Collectors.toList());
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation("Here is to Complete a Trip by provinding Trip Id, endPointLatitude, endPointLongitude and LocationNameEndPoint"
			+ "And it will return success message and the URL to print Invoice( Copy url and paste it in the Browser to see invoice)")
	@PutMapping(value = "completeTrip")
	public ResponseEntity<?> completeTrip(@RequestParam("tripId") int tripId, @RequestParam("latitude") String latitude,
			@RequestParam("longitude") String longitude, @RequestParam("locationNameEndPoint") String locationNameEndPoint) {

		try {
			if(latitude!= null && longitude!=null && locationNameEndPoint != null) {
			ResponseDto dto=tripService.tripCompleted(tripId, latitude, longitude, locationNameEndPoint);
			return new ResponseEntity<>(dto, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(new ResponseMessage("All information are required (latitude, longitude and locationName)"), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation("Return all Completed Trip")
	@GetMapping(value = "completedTrip")
	public ResponseEntity<List<Trip>> completedTrip() {
		try {
			List<Trip> list = tripService.allTrip().stream().filter(i -> i.getStatus().equals("Completed"))
					.collect(Collectors.toList());
			if (list.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Print invoice by providing tripId")
	@GetMapping(value = "invoice/{tripId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> invoice(@PathVariable("tripId") int tripId) {

		try {

			Optional<Invoice> invoice = invoiceService.findInvoiceId(tripId);

			ByteArrayInputStream bis = invoiceService.printInvoice(invoice.get());
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=invoice.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
