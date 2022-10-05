package com.brownfield.pss.book.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brownfield.pss.book.component.Fare;

@FeignClient(value = "fare-service",url = "http://localhost:8080")
public interface FareService {


    @GetMapping("/fares/get?{flightNumber}&{flightDate}")
    Fare getFare(@RequestParam String flightNumber, @RequestParam String flightDate);
    
}
