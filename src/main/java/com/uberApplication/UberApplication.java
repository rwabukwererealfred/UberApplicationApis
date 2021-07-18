package com.uberApplication;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uberApplication.model.Driver;
import com.uberApplication.repository.DriverRepository;


@SpringBootApplication
public class UberApplication implements CommandLineRunner {

	@Autowired
	private DriverRepository driverRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(UberApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	List<Driver>driverList = driverRepo.findAll();
	if(driverList.isEmpty()) {
		List<Driver>list = Arrays.asList(new Driver(1, "Rwabukwerere", "Alfred", new String[] {"-1.955415","30.103722","auca gishushu"}, "Available"),
				new Driver(2, "Iradukunda", "Jado", new String[] {"-1.943048","30.059218","kigali city tower"}, "Available"),
				new Driver(3, "Nishimwe", "Olivier", new String[] {"-1.946608","30.060645","hotel des milles collines"}, "Available"));
		driverRepo.saveAll(list);
	}
	
		
	}
	

}
