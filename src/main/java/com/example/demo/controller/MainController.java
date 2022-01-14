package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.GenerateBill;
import com.example.demo.models.Invoice;
import com.example.demo.models.LastBillModel;
import com.example.demo.models.Meters;
import com.example.demo.services.InvoiceService;
import com.example.demo.services.MetersService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;



@RestController
public class MainController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private MetersService metersService;
	
	@GetMapping("/")
	public String getname() {
		
		return "welcome to my app";
	}
	
	/**************     User       *****************/
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	@GetMapping("/user/{id}")
	public  Optional<User> getuserdeatails(@PathVariable int id) {
		
		
		
		return userService.getUserDetails(id);
	}
	@PostMapping("/")
	public String regsterUser(@RequestBody User user) {
		
		userService.saveUser(user);
		System.out.println(user.getUsername());
		
		return "registered";
		
	}
	
	@GetMapping("/meter/get-bill/{userId}/{month}")
	public LastBillModel getLastBill(@PathVariable(name = "userId") int userId,@PathVariable(name = "month") int month) {
		
		Invoice inv=invoiceService.getLastBill(userId,month);
		System.out.println(inv.getBill_amount());
		
//		return null;
		return new LastBillModel(inv.getStart_reading(),inv.getEnd_reading(),inv.getDate_of_reading(),inv.getReading(),inv.getStart_date(),inv.getEnd_date(),inv.getBill_amount());
	}
	/*************           invoice       ********************* ****/
	
	@SuppressWarnings("removal")
	@PostMapping("/meter/add-readings")
	public GenerateBill makeInvoice(@RequestBody GenerateBill gb) {
		
		SimpleDateFormat sdf =new SimpleDateFormat("YYYY-MM-DD");
		LocalDate now=LocalDate.now();
		
		String currentdate=sdf.format(new Date());
		String status="Not Paid";
		String start_date=now.minusMonths(1).minusDays(now.getDayOfMonth()-1).toString();
		String end_date=now.minusMonths(1).minusDays(now.getDayOfMonth()-1).plusDays(29).toString();
		Invoice cc=invoiceService.getInvoice();
		Optional<Meters> mm= metersService.getMeterReading(gb.getUserId());
	int start_reading=mm.get().getStart_reading();
	int end_reading =mm.get().getEnd_reading();
	int meterId=mm.get().getMeterId();
//		System.out.println(currentdate);
		int reading=gb.getMeterReading();
		int exs_rd=reading-100;
		int exs_amt=0;
		double total=0;
		if(exs_rd>0) {
			exs_amt=exs_rd*10;
		}
		if(reading>100 && exs_rd>0) {
			total=exs_amt+(100*5);
		}
		else {
			total=exs_amt+(reading*5);
		}
		System.out.println(total);
	
		System.out.println(end_date);

		invoiceService.saveInvoice(new Invoice(0,gb.getUserId(),total,status,currentdate,reading,start_date,end_date,start_reading,end_reading));
		
		metersService.upDateMeterDetails(meterId,start_reading,end_reading,reading);
		return gb;
		
	}

}
