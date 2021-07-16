package com.uberApplication.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.uberApplication.model.Invoice;
import com.uberApplication.model.Trip;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {

	Optional<Invoice> findByTrip(Trip trip);
}
