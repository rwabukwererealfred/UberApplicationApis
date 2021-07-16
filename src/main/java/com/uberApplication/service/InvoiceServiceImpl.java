package com.uberApplication.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.uberApplication.model.Invoice;
import com.uberApplication.model.Trip;
import com.uberApplication.repository.InvoiceRepository;
import com.uberApplication.repository.TripRepository;

@Service
public class InvoiceServiceImpl implements IInvoice {
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
	private TripRepository tripRepo;

	@Override
	public ByteArrayInputStream printInvoice(Invoice invoice) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			PdfWriter.getInstance(document, out);
			document.open();
			LineSeparator ls = new LineSeparator();
			String start = invoice.getTrip().getStartTime().getHour()+":"+invoice.getTrip().getStartTime().getMinute()+":"+invoice.getTrip().getStartTime().getSecond();

			String end = invoice.getTrip().getEndTime().getHour()+":"+invoice.getTrip().getEndTime().getMinute()+":"+invoice.getTrip().getEndTime().getSecond();
			document.add(new Paragraph(
					"Uber Application Apis \nEmail : UberApp@gmail.com \nTime Started :" +
			start+"\nEnd Time :"+ end));
			document.add(new Paragraph("                                          "));
			
			Paragraph p = new Paragraph("TRIP INVOICE",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.NORMAL, BaseColor.DARK_GRAY));
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);
			document.add(new Chunk(ls));
			document.add(new Paragraph("                                          "));
			PdfPTable table1 = new PdfPTable(4);
			table1.setWidthPercentage(100);
			document.add(table1);
			
			PdfPTable table = new PdfPTable(5);

			table.setWidthPercentage(100);
			
			

			BaseColor color = new BaseColor(10, 113, 110);
			Font font0 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.WHITE);

			PdfPCell driverName = new PdfPCell(new Phrase("Driver Name\n", font0));
			driverName.setBackgroundColor(color);
			table.addCell(driverName);
			
			
			PdfPCell startPlace = new PdfPCell(new Phrase("Start Place\n", font0));
			startPlace.setBackgroundColor(color);
			table.addCell(startPlace);
			
			PdfPCell endPlace = new PdfPCell(new Phrase("End Place\n", font0));
			endPlace.setBackgroundColor(color);
			table.addCell(endPlace);
			
			PdfPCell kilo = new PdfPCell(new Phrase("Kilometer\n", font0));
			kilo.setBackgroundColor(color);
			table.addCell(kilo);
			
			PdfPCell tripStatus = new PdfPCell(new Phrase("Status\n", font0));
			tripStatus.setBackgroundColor(color);
			table.addCell(tripStatus);
			DecimalFormat d = new DecimalFormat("#.##");
			
				PdfPCell cell;

				cell = new PdfPCell(new Phrase(invoice.getTrip().getDriver().getFirstName()+" "+ invoice.getTrip().getDriver().getLastName()));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);


				cell = new PdfPCell(new Phrase(String.valueOf(invoice.getTrip().getStartPoint()[2])));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(invoice.getTrip().getEndPoint()[2])));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(d.format(invoice.getDistanceKilometer())+" KM")));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(invoice.getTrip().getStatus())));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				//new ligne
				
				
				cell = new PdfPCell(new Phrase());
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase());
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase());
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);
				
				
				cell = new PdfPCell(new Phrase("Total Cost:",
						FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.DARK_GRAY)));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(d.format(invoice.getCost())+" Frw"),
						FontFactory.getFont(FontFactory.TIMES_BOLD, 14, Font.BOLD, BaseColor.DARK_GRAY)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);
			
				document.add(table);
				
				document.add(new Paragraph("                                          "));
				document.add(new Paragraph("                                          "));
				String dd = new SimpleDateFormat("dd/MMM/yyyy HH:mm").format(new Date());
				Paragraph printedOn = new Paragraph("Printed On:" + dd);
				printedOn.setAlignment(Element.ALIGN_RIGHT);
				document.add(printedOn);
				document.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	@Override
	public Optional<Invoice> findInvoiceId(int tripID) {
		Trip trip = tripRepo.findById(tripID).get();
		return invoiceRepo.findByTrip(trip);
	}
}

