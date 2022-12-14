package com.brownfield.pss.book.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.book.component.BookingComponent;
import com.brownfield.pss.book.entity.BookingRecord;

@RefreshScope
@RestController
@CrossOrigin
@RequestMapping("/booking")
public class BookingController {
	
	@Value("${application}")
	private String applicationName;
	
	BookingComponent bookingComponent;
	
	@Autowired
	BookingController(BookingComponent bookingComponent){
		this.bookingComponent = bookingComponent;
	}

	@RequestMapping(value="/create" , method = RequestMethod.POST)
	long book(@RequestBody BookingRecord record){
		System.out.println("Booking Request" + record); 
		return bookingComponent.book(record);
	}
	
	@RequestMapping("/get/{id}")
	Optional<BookingRecord> getBooking(@PathVariable long id){
		System.out.println("applicationName : "+ applicationName);
		return bookingComponent.getBooking(id);
	}	
}
