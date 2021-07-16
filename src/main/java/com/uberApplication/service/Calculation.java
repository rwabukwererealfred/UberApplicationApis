package com.uberApplication.service;

public class Calculation {

	public double CalculationByDistance(String StartP, String EndP) {
		int Radius = 6371;// radius of earth in Km
		double lat1 = Double.parseDouble(StartP.split(",")[0]);
		double lat2 = Double.parseDouble(EndP.split(",")[0]);
		double lon1 = Double.parseDouble(StartP.split(",")[1]);
		double lon2 = Double.parseDouble(EndP.split(",")[1]);
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.asin(Math.sqrt(a));

		return Radius * c;
	}
}
