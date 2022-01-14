package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity

@Getter
@Table(name="meters")
@AllArgsConstructor
@NoArgsConstructor
public class Meters {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int meterId;
	private int userId;
	private int start_reading;
	 private int end_reading;
	 private String last_updated;
}
