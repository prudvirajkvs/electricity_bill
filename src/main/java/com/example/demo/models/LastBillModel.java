package com.example.demo.models;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastBillModel {

	private int start_reading;
	private int end_reading;
	private String date_of_reading;
	private int reading;
	private String start_date;
	private String end_date;
	
	private double bill_amount;
}
