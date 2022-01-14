package com.example.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Invoice;
import com.example.demo.repo.InvoiceRepo;



@Service
public class InvoiceService {

	@Autowired
	InvoiceRepo invoiceRepo;
	public Invoice getInvoice() {
		
		return null;
		
	}
	public void saveInvoice(Invoice invoice) {
		
		invoiceRepo.save(invoice);
		
	}
	public Invoice getLastBill(int userId, int month) {
		// TODO Auto-generated method stub
		int dayOfMonth= 30;
		if(month==2) {dayOfMonth=28;}
		LocalDate date=LocalDate.now();
		
		return invoiceRepo.getLastBill(userId,month,date.getYear());
		
		
	}
	
}
