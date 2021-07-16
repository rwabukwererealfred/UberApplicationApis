package com.uberApplication.service;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import com.uberApplication.model.Invoice;

public interface IInvoice {

	public ByteArrayInputStream printInvoice(Invoice invoice);
	public Optional<Invoice>findInvoiceId(int tripID);
}
