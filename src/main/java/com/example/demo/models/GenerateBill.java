package com.example.demo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class GenerateBill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int billId;
	private int userId;
	private int meterReading;
	private String date;
	
	
}
