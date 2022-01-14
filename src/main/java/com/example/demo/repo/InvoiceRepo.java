package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Invoice;



@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

	@Transactional
	@Query(value = " SELECT * FROM invoice WHERE user_id=:user_id AND MONTH(date_of_reading)=:month AND YEAR(date_of_reading)=:year order by end_reading desc LIMIT 1 ",nativeQuery = true)
	Invoice getLastBill(@Param(value ="user_id" )int user_id, @Param(value="month") int month,@Param(value = "year") int year);

	
}
