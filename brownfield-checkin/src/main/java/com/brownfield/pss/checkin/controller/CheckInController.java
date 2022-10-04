package com.brownfield.pss.checkin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brownfield.pss.checkin.component.CheckinComponent;
import com.brownfield.pss.checkin.entity.CheckInRecord;

@RestController
@CrossOrigin
@RequestMapping("/checkin")
public class CheckInController {

	private final CheckinComponent checkInComponent;
	
	CheckInController(CheckinComponent checkInComponent){
		this.checkInComponent = checkInComponent;
	}
	
	@GetMapping("/get/{id}")
	CheckInRecord getCheckIn(@PathVariable long id ){
		return checkInComponent.getCheckInRecord(id);
	}

	@PostMapping(value ="/create")
	long checkIn(@RequestBody CheckInRecord checkIn){
		return checkInComponent.checkIn(checkIn);
	}
	
}
