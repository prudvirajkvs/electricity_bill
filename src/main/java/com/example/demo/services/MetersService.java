package com.example.demo.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.models.Meters;
import com.example.demo.repo.MeterRepo;

@Service
public class MetersService {

	@Autowired
	MeterRepo meterRepo;

	public Optional<Meters> getMeterReading(int userId) {
		
		return meterRepo.findById(userId);
	}

	
	public void upDateMeterDetails(int meterId, int start_reading, int end_reading,int reading) {
		
	start_reading=start_reading+end_reading;
	end_reading=end_reading+reading;
	LocalDate date=LocalDate.now();
	String last_updated=date.toString();
	try {
		
	
		meterRepo.updateById(meterId, start_reading, end_reading,last_updated);
	}
	catch(Exception e) {
	System.out.println(e.getMessage());
	
	}
	}

	
}
