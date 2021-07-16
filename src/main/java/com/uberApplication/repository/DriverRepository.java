package com.uberApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.model.Driver;

@Repository
public interface DriverRepository extends MongoRepository<Driver, Integer> {

}
