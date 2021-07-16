package com.uberApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.model.Trip;

@Repository
public interface TripRepository extends MongoRepository<Trip, Integer> {

}
